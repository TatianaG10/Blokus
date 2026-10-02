package View.Windows;

import Controller.Joueurs.Joueur;
import Model.Jeu;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public class FinDeJeu extends Window {

    public FinDeJeu(Jeu game) {
        super();
        setTitle("Fin de la partie");
        setSize(500, 350);
        setLocationRelativeTo(null); // Centre la fenêtre

        // Panel principal
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(20, 20));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        // === Message principal ===
        JLabel messageLabel = new JLabel(getMessageGagnants(game), SwingConstants.CENTER);
        messageLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        messageLabel.setForeground(new Color(239, 177, 7));
        panel.add(messageLabel, BorderLayout.NORTH);

        // === Affichage du podium ===
        JLabel podiumLabel = new JLabel(formatPodiumAsHTML(game.podium()), SwingConstants.CENTER);
        podiumLabel.setFont(new Font("Serif", Font.PLAIN, 16));
        podiumLabel.setVerticalAlignment(SwingConstants.TOP);

        panel.add(podiumLabel, BorderLayout.CENTER);

        // === Bouton quitter ===
        JButton quitter = new JButton("Fermer");
        quitter.setFont(new Font("SansSerif", Font.PLAIN, 14));
        quitter.setFocusable(false);
        quitter.addActionListener(e -> dispose());
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(quitter);
        panel.add(bottomPanel, BorderLayout.SOUTH);

        setContentPane(panel);
        setVisible(true);
    }

    private String formatPodiumAsHTML(String podiumText) {
        StringBuilder sb = new StringBuilder("<html><div style='text-align: center;'>");
        String[] lines = podiumText.split("\n");
        for (String line : lines) {
            sb.append(line).append("<br>");
        }
        sb.append("</div></html>");
        return sb.toString();
    }


    private String getMessageGagnants(Jeu jeu) {
        List<Joueur> joueurs = List.of(jeu.getJoueurs());
        int maxScore = joueurs.stream().mapToInt(Joueur::getScore).max().orElse(0);
        List<String> gagnants = joueurs.stream()
                .filter(j -> j.getScore() == maxScore)
                .map(Joueur::getPseudo)
                .collect(Collectors.toList());

        if (gagnants.size() == 1) {
            return "🎉 Bravo au vainqueur : " + gagnants.get(0) + " ! 🎉";
        } else {
            return "🤝 Égalité ! Bravo aux gagnants : " + String.join(", ", gagnants);
        }
    }
}
