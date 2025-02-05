package controle;

import java.io.Serializable;

public abstract class Personne implements Serializable {
    protected String nom;
    protected String prenom;

    public Personne(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }

    public abstract String afficherDetails();

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }
}


