package controle;

public interface Gestion<T> {
    void ajouter(T element);
    void supprimer(String identifiant);
    void afficher();
}

