package controle;

public class Stagiaire extends Personne {
    private String niveauEtude;

    public Stagiaire(String nom, String prenom, String niveauEtude) {
        super(nom, prenom);
        this.niveauEtude = niveauEtude;
    }

    @Override
    public String afficherDetails() {
        return "Nom: " + nom + ", Prénom: " + prenom + ", Niveau: " + niveauEtude;
    }

    public String getNiveauEtude() {
        return niveauEtude;
    }
}


