package controle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Stage implements Serializable {
    private String titre;
    private int duree;
    private List<Stagiaire> stagiairesAssocies;

    public Stage(String titre, int duree) {
        this.titre = titre;
        this.duree = duree;
        this.stagiairesAssocies = new ArrayList<>();
    }


    public void ajouterStagiaire(Stagiaire stagiaire) {
        if (!stagiairesAssocies.contains(stagiaire)) {
            stagiairesAssocies.add(stagiaire);
        } else {
            System.out.println("Le stagiaire est déjà associé à ce stage.");
        }
    }

    public String afficherDetails() {
        StringBuilder details = new StringBuilder("Titre: " + titre + ", Durée: " + duree + " jours\n");
        details.append("Stagiaires associés:\n");
        for (Stagiaire s : stagiairesAssocies) {
            details.append("- ").append(s.afficherDetails()).append("\n");
        }
        return details.toString();
    }

    public String getTitre() {
        return titre;
    }

    public int getDuree() {
        return duree;
    }

    public List<Stagiaire> getStagiairesAssocies() {
        return stagiairesAssocies;
    }
}






