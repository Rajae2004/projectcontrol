import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import controle.Personne;
import controle.Stagiaire;
import controle.Stage;
import controle.DataManager;

import java.awt.event.*;

public class GestionStagesApp {

    public GestionStagesApp() {
        creerPageAccueil();
    }

    private void creerPageAccueil() {
        JFrame accueilFrame = new JFrame("Bienvenue");
        accueilFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        accueilFrame.setSize(600, 400);
        accueilFrame.setLocationRelativeTo(null);

        JPanel accueilPanel = new JPanel();
        accueilPanel.setBackground(new Color(255, 182, 193)); // Fond rose clair


        JLabel bienvenueLabel = new JLabel("Bonjour, bienvenue !");
        bienvenueLabel.setFont(new Font("Arial", Font.BOLD, 24));
        bienvenueLabel.setForeground(new Color(255, 20, 147)); // Rose foncé
        bienvenueLabel.setHorizontalAlignment(SwingConstants.CENTER);


        accueilPanel.setLayout(new BorderLayout());
        accueilPanel.add(bienvenueLabel, BorderLayout.CENTER);


        Timer timer = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                accueilFrame.dispose();
                creerInterface();
            }
        });
        timer.setRepeats(false); //
        timer.start();

        accueilFrame.add(accueilPanel);
        accueilFrame.setVisible(true);
    }

    private void creerInterface() {

        JFrame frame = new JFrame("Gestion Simplifiée des Stages");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 600);
        frame.setLocationRelativeTo(null);


        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBackground(new Color(255, 182, 193)); // Fond rose pâle

        JTabbedPane tabbedPane = new JTabbedPane();


        JPanel panelStagiaires = new JPanel(new BorderLayout());
        panelStagiaires.setBackground(new Color(255, 182, 193)); // Fond rose pâle pour l'onglet

        DefaultTableModel stagiaireModel = new DefaultTableModel(new String[]{"Nom", "Prénom", "Niveau"}, 0);
        JTable tableStagiaires = new JTable(stagiaireModel);
        JScrollPane scrollPaneStagiaires = new JScrollPane(tableStagiaires);
        panelStagiaires.add(scrollPaneStagiaires, BorderLayout.CENTER);

        JButton btnAjouterStagiaire = new JButton("Ajouter Stagiaire");
        JButton btnSupprimerStagiaire = new JButton("Supprimer Stagiaire");
        JButton btnAfficherStagiaires = new JButton("Afficher Stagiaires");


        styleButton(btnAjouterStagiaire);
        styleButton(btnSupprimerStagiaire);
        styleButton(btnAfficherStagiaires);

        JPanel boutonsStagiaires = new JPanel();
        boutonsStagiaires.add(btnAjouterStagiaire);
        boutonsStagiaires.add(btnSupprimerStagiaire);
        boutonsStagiaires.add(btnAfficherStagiaires);

        panelStagiaires.add(boutonsStagiaires, BorderLayout.SOUTH);
        tabbedPane.addTab("Stagiaires", panelStagiaires);


        JPanel panelStages = new JPanel(new BorderLayout());
        panelStages.setBackground(new Color(255, 182, 193)); // Fond rose pâle pour l'onglet

        DefaultTableModel stageModel = new DefaultTableModel(new String[]{"Titre", "Durée", "Stagiaires"}, 0);
        JTable tableStages = new JTable(stageModel);
        JScrollPane scrollPaneStages = new JScrollPane(tableStages);
        panelStages.add(scrollPaneStages, BorderLayout.CENTER);

        JButton btnAjouterStage = new JButton("Ajouter Stage");
        JButton btnAssocierStagiaire = new JButton("Associer Stagiaire");


        styleButton(btnAjouterStage);
        styleButton(btnAssocierStagiaire);

        JPanel boutonsStages = new JPanel();
        boutonsStages.add(btnAjouterStage);
        boutonsStages.add(btnAssocierStagiaire);

        panelStages.add(boutonsStages, BorderLayout.SOUTH);
        tabbedPane.addTab("Stages", panelStages);


        panelPrincipal.add(tabbedPane, BorderLayout.CENTER);
        frame.add(panelPrincipal);
        frame.setVisible(true);


        btnAjouterStagiaire.addActionListener(e -> ajouterStagiaire(stagiaireModel));
        btnAjouterStage.addActionListener(e -> ajouterStage(stageModel));
        btnSupprimerStagiaire.addActionListener(e -> supprimerStagiaire(stagiaireModel));
        btnAssocierStagiaire.addActionListener(e -> associerStagiaireAStage());
        btnAfficherStagiaires.addActionListener(e -> afficherStagiaires(stagiaireModel));
    }


    private void styleButton(JButton button) {
        button.setBackground(new Color(255, 105, 180)); // Rose clair
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setPreferredSize(new Dimension(150, 40));

        // Animation du bouton
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(255, 20, 147)); // Changement de couleur au survol
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(255, 105, 180)); // Retour à la couleur initiale
            }
        });
    }

    private void ajouterStagiaire(DefaultTableModel model) {
        JTextField nomField = new JTextField();
        JTextField prenomField = new JTextField();
        JTextField niveauField = new JTextField();

        Object[] message = {
                "Nom:", nomField,
                "Prénom:", prenomField,
                "Niveau d'étude:", niveauField
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Ajouter un Stagiaire", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String nom = nomField.getText().trim();
            String prenom = prenomField.getText().trim();
            String niveau = niveauField.getText().trim();

            if (nom.isEmpty() || prenom.isEmpty() || niveau.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs !");
            } else {
                Stagiaire stagiaire = new Stagiaire(nom, prenom, niveau);
                DataManager.ajouterStagiaire(stagiaire);
                model.addRow(new Object[]{nom, prenom, niveau});
                DataManager.sauvegarderStagiaires(DataManager.stagiaires);  // Sauvegarder après ajout
                JOptionPane.showMessageDialog(null, "Stagiaire ajouté avec succès !");
            }
        }
    }

    private void ajouterStage(DefaultTableModel model) {
        JTextField titreField = new JTextField();
        JTextField dureeField = new JTextField();

        Object[] message = {
                "Titre:", titreField,
                "Durée (en jours):", dureeField
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Ajouter un Stage", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String titre = titreField.getText().trim();
            String dureeStr = dureeField.getText().trim();

            try {
                int duree = Integer.parseInt(dureeStr);
                if (titre.isEmpty() || duree <= 0) {
                    JOptionPane.showMessageDialog(null, "Veuillez entrer des données valides !");
                } else {
                    Stage stage = new Stage(titre, duree);
                    DataManager.ajouterStage(stage);
                    model.addRow(new Object[]{titre, duree, ""});
                    DataManager.sauvegarderStages(DataManager.stages);
                    JOptionPane.showMessageDialog(null, "Stage ajouté avec succès !");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Durée invalide !");
            }
        }
    }

    private void associerStagiaireAStage() {
        // Code d'association de stagiaire à un stage
    }

    private void supprimerStagiaire(DefaultTableModel model) {
        String nom = JOptionPane.showInputDialog("Entrez le nom du stagiaire à supprimer :");

        if (nom != null && !nom.isEmpty()) {
            DataManager.supprimerStagiaire(nom);
            model.setRowCount(0);  // Réinitialise la table
            afficherStagiaires(model);  // Affiche les stagiaires restants
        } else {
            JOptionPane.showMessageDialog(null, "Nom invalide.");
        }
    }

    private void afficherStagiaires(DefaultTableModel model) {
        model.setRowCount(0);  // Réinitialise la table

        for (String details : DataManager.afficherStagiaires()) {
            String[] parts = details.split(",");
            model.addRow(parts);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GestionStagesApp::new);
    }
}






