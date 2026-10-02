package Structures;

public enum Coin {
    HAUT_GAUCHE(0,0),
    HAUT_DROITE(0,19),
    BAS_GAUCHE(19,0),
    BAS_DROITE(19,19);

    private Position position;

    Coin(int ligne, int colonne){
        this.position = new Position(ligne,colonne);
    }

    public Position getPosition(){
        return position;
    }

    public static Coin getCoin(Position position){
        for (Coin coin : Coin.values()){
            if (position.equals(coin.position)){
                return coin;
            }
        }
        throw new IllegalArgumentException("Aucun coin ne correspont à cette position :" + position);
    }

    public Coin getCoinOpps(){
        Coin opps = null;
        switch(this){
            case HAUT_DROITE :
                opps = BAS_GAUCHE;
                break;
            case HAUT_GAUCHE :
                opps = BAS_DROITE;
                break;
            case BAS_DROITE :
                opps = HAUT_GAUCHE;
                break;
            case BAS_GAUCHE :
                opps = HAUT_DROITE;
                break;
        };
        return opps;
    }
}
