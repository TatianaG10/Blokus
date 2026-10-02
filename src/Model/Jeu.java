package Model;

import Controller.Configuration;
import Controller.Joueurs.Humain;
import Controller.Joueurs.IA.IA_intermediare;
import Controller.Joueurs.Joueur;
import Structures.*;

import java.io.*;
import java.util.*;

public class Jeu implements Serializable {
    private static final long serialVersionUID = 1L;

    private final Plateau plateau;
    private final Joueur joueur1;
    private final Joueur joueur2;
    private final Joueur joueur3;
    private final Joueur joueur4;
    private final Historique historique;

    protected List<Piece> allPieces;

    private final Joueur[] joueurs;
    private int indexJoueurCourrant;
    private Joueur joueurCourrant;
    private int nbCoups;

    private boolean isFinished;
    private boolean isRejoue;


    public Jeu(Joueur joueur1, Joueur joueur2, Joueur joueur3, Joueur joueur4, int taille) {
        this.joueur1 = joueur1;
        this.joueur2 = joueur2;
        this.joueur3 = joueur3;
        this.joueur4 = joueur4;

        joueurCourrant = joueur1;
        indexJoueurCourrant = 0;
        nbCoups = 0;
        isFinished = false;

        plateau = new Plateau(taille);
        historique = new Historique();
        joueurs = new Joueur[4];
        allPieces = new ArrayList<Piece>();

        for (int i = 0; i< PiecesBlokus.NOMBREDEPIECES; i++) {
            allPieces.add(i,new Piece(i));
        }

        initJoueurs();

        int indexMax = plateau.getNbCases()-1;

        this.joueur1.initCasesJouables();
        this.joueur2.initCasesJouables();
        this.joueur3.initCasesJouables();
        this.joueur4.initCasesJouables();

        plateau.getCase(0,0).setVersion();
        plateau.getCase(0,indexMax).setVersion();
        plateau.getCase(indexMax,0).setVersion();
        plateau.getCase(indexMax,indexMax).setVersion();
    }

    //DEEP COPY
    public Jeu(Jeu jeu){
        this.joueur1 = jeu.joueur1 instanceof Humain ? new Humain(jeu.joueur1) : new IA_intermediare(jeu.joueur1,2);
        this.joueur2 = jeu.joueur2 instanceof Humain ? new Humain(jeu.joueur2) : new IA_intermediare(jeu.joueur2,2);
        this.joueur3 = jeu.joueur3 instanceof Humain ? new Humain(jeu.joueur3) : new IA_intermediare(jeu.joueur3, 2);
        this.joueur4 = jeu.joueur4 instanceof Humain ? new Humain(jeu.joueur4) : new IA_intermediare(jeu.joueur4, 2);

        joueurCourrant = jeu.joueurCourrant;
        indexJoueurCourrant = jeu.indexJoueurCourrant;
        isFinished = jeu.isFinished;
        nbCoups = jeu.nbCoups;
        isRejoue = jeu.isRejoue;
        historique = new Historique(jeu.historique);

        plateau = new Plateau(jeu.plateau);
        joueurs = new Joueur[4];
        allPieces = new ArrayList<Piece>();

        for (int i = 0; i< PiecesBlokus.NOMBREDEPIECES; i++) {
            allPieces.add(i,new Piece(i));
        }

        initJoueurs();
    }

    private void initJoueurs() {
        joueurs[0] = joueur1;
        joueurs[1] = joueur2;
        joueurs[2] = joueur3;
        joueurs[3] = joueur4;

        joueur1.setPieces(allPieces);
        joueur2.setPieces(allPieces);
        joueur3.setPieces(allPieces);
        joueur4.setPieces(allPieces);

        joueur1.setGame(this);
        joueur2.setGame(this);
        joueur3.setGame(this);
        joueur4.setGame(this);

        for (Joueur j : joueurs){
            if(j instanceof IA_intermediare){
                ((IA_intermediare) j).setPlayers(joueurs, j.getIDJoueur());
            }
        }
    }

    private void setNextValidJoueur(){
        int i=0;
        do {
            if (i>0) historique.ajouterCoup(null,joueurCourrant);
            indexJoueurCourrant = (indexJoueurCourrant + 1) % 4;
            joueurCourrant = joueurs[indexJoueurCourrant];
            i++;
        } while (i<=4 && joueurCourrant.estBloque());
        if (i>4) isFinished = true; //tous les joueurs sont bloquees
    }

    public Coup jouerCoup(Coup coup) {
        if (coup==null){
            isRejoue = false;
            return null;
        }

        Piece p = coup.getPiece();
        Position refPiece = coup.getReferencePiece();
        Position refPlateau = coup.getReferencePlateau();

        int deltaL = refPlateau.getLigne()-refPiece.getLigne();
        int deltaC = refPlateau.getColonne()-refPiece.getColonne();

        for (int i=0;i<Piece.hauteurMax;i++){
            for (int j=0;j<Piece.largeurMax;j++){
                int val = p.getMatrice()[i][j];
                if (val != Case.VIDE) {
                    int l = deltaL + i;
                    int c = deltaC + j;
                    if (!plateau.estDansPlateau(l, c)) continue;

                    Case casePlateau = plateau.getCase(l, c);
                    casePlateau.setVersion();

                    if (val == Case.CASE){
                        casePlateau.setJoueur(indexJoueurCourrant);
                    }else{
                        if (casePlateau.getType(indexJoueurCourrant) == Case.CASE || casePlateau.getType(indexJoueurCourrant) == Case.BLOQUE ) continue; //si on a une case sur le plateau on garde la piece
                        if (casePlateau.getType(indexJoueurCourrant) ==  Case.BORD && val==Case.COIN) continue; //Un Ancien a la priorite sur un COIN
                        casePlateau.setType(val, indexJoueurCourrant);
                        casePlateau.ajouterJoueur(indexJoueurCourrant);
                    }
                }
            }
        }
        joueurCourrant.setScore(joueurCourrant.getScore()+p.getValeur()); //update le score
        joueurCourrant.getPieces().removeIf(piece -> piece.getId() == p.getId());

        if (!isRejoue) {
            historique.ajouterCoup(coup,joueurCourrant);
        }
        setNextValidJoueur();
        isRejoue = false;
        nbCoups++;
        //System.out.println(this);
        return coup;
    }

    public boolean annulerCoup(){
        boolean res=false;
        if (historique.peutAnnuler()){
            Coup coup = historique.annulerCoup();
            if (coup!=null){
                Piece p = coup.getPiece();
                Position refPiece = coup.getReferencePiece();
                Position refPlateau = coup.getReferencePlateau();

                int deltaL = refPlateau.getLigne()-refPiece.getLigne();
                int deltaC = refPlateau.getColonne()-refPiece.getColonne();

                for (int i=0;i<Piece.hauteurMax;i++){
                    for (int j=0;j<Piece.largeurMax;j++){
                        int val = p.getMatrice()[i][j];
                        if (val != Case.VIDE) {
                            int l = deltaL + i;
                            int c = deltaC + j;
                            if (!plateau.estDansPlateau(l, c)) continue;

                            Case casePlateau = plateau.getCase(l, c);
                            casePlateau.rebaseOldVersion(plateau.estCorner(l,c));
                        }
                    }
                }
                setJoueurCourrant(getJoueurPrecedent());

                joueurCourrant.setScore(joueurCourrant.getScore()-p.getValeur()); //update le score
                joueurCourrant.getPieces().add(allPieces.get(p.getId()));

            }else{
                setJoueurCourrant(getJoueurPrecedent());
                res=true;
            }
        }
        nbCoups--;
        return res;
    }

    public boolean rejouerCoup() {
        if (historique.peutRefaire()){
            Coup coup = historique.refaireCoup();
            isRejoue=true;
            return jouerCoup(coup)!=null;
        }
        return false;
    }

    public boolean coupValid(Coup coup, Joueur joueur) {
        Piece p = coup.getPiece();
        if (joueur.getPieces().stream().allMatch(piece -> piece.getId() != p.getId())) {
            return false; //no piece was found
        }

        Position refPiece = coup.getReferencePiece();
        Position refPlateau = coup.getReferencePlateau();

        int deltaL = refPlateau.getLigne() - refPiece.getLigne();
        int deltaC = refPlateau.getColonne() - refPiece.getColonne();

        int counterCoin = 0;
        for (int i = 0; i < Piece.hauteurMax; i++) {
            for (int j = 0; j < Piece.largeurMax; j++) {
                int val = p.getMatrice()[i][j];
                if (val != Case.VIDE) {
                    int l = deltaL + i;
                    int c = deltaC + j;
                    if (!plateau.estDansPlateau(l, c) && val == Case.CASE){
                        return false;
                    }
                    if (!plateau.estDansPlateau(l, c) && val != Case.CASE) continue; //pas dans le tableau mais n'est pas une case on skip

                    if (val==Case.CASE) {
                        Case casePlateau = plateau.getCase(l, c);
                        // Case appartient au joueur -> Pas de Case ou BORD !!
                        if (casePlateau.appartientAuJoueur(joueur.getIDJoueur()) && (casePlateau.getType(joueur.getIDJoueur()) == Case.BORD || casePlateau.getType(joueur.getIDJoueur()) == Case.CASE)){ /** Case m'appartient mais contient un Bord ou un morceau piece donc peut pas jouer */
                            return false;
                        }
                        // Case n'appartient pas au joueur -> est Bloquee ??
                        if (!casePlateau.appartientAuJoueur(joueur.getIDJoueur()) && casePlateau.getType(joueur.getIDJoueur()) == Case.BLOQUE){ /** Case partage avec un adversaire mais est bloquee par lui*/
                            return false;
                        }
                        // Demander la garantie d'au moins un coin appartenant au Joueur
                        if (casePlateau.appartientAuJoueur(joueur.getIDJoueur()) && casePlateau.getType(joueur.getIDJoueur()) == Case.COIN){
                            counterCoin++;
                        }
                    }
                }
            }
        }
        return counterCoin>0; //check au moins un coin sur la pièce
    }

    /// SETTERS

    public void setJoueurCourrant(Joueur joueurCourrant) {
        this.joueurCourrant = joueurCourrant;
        for (int i=0;i<4;i++) {
            if (joueurs[i] == joueurCourrant) {
                indexJoueurCourrant=i;
                break;
            }
        }
    }

    /// GETTERS
    public HashSet<Position> getPositionsJouables(int joueur) {
        return joueurs[joueur].getPositionsJouables();
    }
    public HashSet<Piece> getPiecesJouables(int joueur) {
        return joueurs[joueur].getPiecesJouables();
    }
    public HashSet<Coup> getCoupsJouables(int joueur) {
        return joueurs[joueur].getCoupsJouables();
    }

    public Joueur getJoueurPrecedent() {
        int index = (indexJoueurCourrant-1);
        if (index<0){
            return joueurs[3];
        }
        return joueurs[index];
    }
    public int getNbCoups(){return nbCoups;}
    
    public Joueur[] getJoueurs() {
        return joueurs;
    }

    public Joueur getJoueur(int index){
        return joueurs[index];
    }

    public Joueur getJoueurCourrant() {
        return joueurCourrant;
    }

    public int getIndexJoueurCourrant() {
        return indexJoueurCourrant;
    }

    public Plateau getPlateau() {
        return plateau;
    }

    /// STATUS BOOLEANS

    public boolean estPartieFinie() {
        return isFinished;
    }

    public void sauvegarderJeu() throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(
                new BufferedOutputStream(new FileOutputStream(Configuration.getSaveFileName())))) {
            out.writeObject(this);
            System.out.println("Jeu sauvegardé avec succès.");
        }
    }

    public static Jeu chargerJeu() throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(
                new BufferedInputStream(new FileInputStream(Configuration.getSaveFileName())))) {
            Jeu jeu = (Jeu) in.readObject();
            return jeu;
        }
    }


    public String toString(){
        String res="";
        for (Joueur joueur : joueurs){
            if (joueur==joueurCourrant){
                res+="> ";
            }
            res+=joueur.toString();
        }
        res+=plateau.toString(indexJoueurCourrant);
        res+= historique.toString();

        return res;
    }

    public String podium(){
        String res="";
        Joueur[] podiumFinish = Arrays.stream(joueurs).sorted(Comparator.comparingInt(Joueur::getScore).reversed()).toArray(Joueur[]::new);; // tri décroissant
        res += podiumFinish[0];
        res += podiumFinish[1];
        res += podiumFinish[2];
        res += podiumFinish[3];
        return res;
    }

    public Joueur n1(){
        Joueur top1 = joueurs[0];
        for(Joueur j : joueurs){
            if (j.getScore() > top1.getScore()){
                top1 = j;
            }
        }
        return top1;
    }

}
