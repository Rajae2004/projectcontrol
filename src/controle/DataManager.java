package controle;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataManager {

    private static final String STAGIAIRES_FILE = "stagiaires.dat";
    private static final String STAGES_FILE = "stages.dat";


    public static List<Stagiaire> stagiaires = new ArrayList<>();
    public static List<Stage> stages = new ArrayList<>();


    public static void ajouterStagiaire(Stagiaire stagiaire) {
        stagiaires.add(stagiaire);
    }


    public static void supprimerStagiaire(String nom) {
        stagiaires.removeIf(stagiaire -> stagiaire.getNom().equalsIgnoreCase(nom));
    }


    public static List<String> afficherStagiaires() {
        List<String> details = new ArrayList<>();
        for (Stagiaire stagiaire : stagiaires) {
            details.add(stagiaire.afficherDetails());
        }
        return details;
    }


    public static void sauvegarderStagiaires(List<Stagiaire> stagiaires) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(STAGIAIRES_FILE))) {
            oos.writeObject(stagiaires);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static List<Stagiaire> chargerStagiaires() {
        List<Stagiaire> stagiaires = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(STAGIAIRES_FILE))) {
            stagiaires = (List<Stagiaire>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            // Si le fichier est vide ou non trouvé, retourner une liste vide
        }
        return stagiaires;
    }


    public static void ajouterStage(Stage stage) {
        stages.add(stage);
    }


    public static void associerStagiaireAStage(String stagiaireNom, String stageTitre) {
        Stagiaire stagiaire = stagiaires.stream()
                .filter(s -> s.getNom().equalsIgnoreCase(stagiaireNom))
                .findFirst().orElse(null);

        Stage stage = stages.stream()
                .filter(s -> s.getTitre().equalsIgnoreCase(stageTitre))
                .findFirst().orElse(null);

        if (stagiaire != null && stage != null) {
            stage.ajouterStagiaire(stagiaire);
        } else {
            System.out.println("Stagiaire ou Stage non trouvé.");
        }
    }



    public static void sauvegarderStages(List<Stage> stages) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(STAGES_FILE))) {
            oos.writeObject(stages);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static List<Stage> chargerStages() {
        List<Stage> stages = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(STAGES_FILE))) {
            stages = (List<Stage>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            // Si le fichier est vide ou non trouvé, retourner une liste vide
        }
        return stages;
    }
}







