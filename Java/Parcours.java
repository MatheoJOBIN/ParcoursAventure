package Java;

import java.util.ArrayList;
import java.util.List;

public class Parcours {
    private List<Coordonnees> etapes;

    /**
     * Instancie un parcours avec une position initiale donnée
     * @param xStart La position initiale en abscisse
     * @param yStart La position initiale en ordonnée
     */
    public Parcours(int xStart, int yStart) {
        this.etapes = new ArrayList<>();
        this.etapes.add(new Coordonnees(xStart, yStart));
    }

    /**
     * Ajoute une étape au parcours
     * @param coordonnees Les coordonnées de l'étape
     */
    public void ajouterEtape(String direction) {
        int xPosition = this.etapes.get(this.etapes.size() - 1).getX();
        int yPosition = this.etapes.get(this.etapes.size() - 1).getY();

        switch (direction) {
            case "N":
                this.etapes.add(new Coordonnees(xPosition, yPosition - 1));
                break;
            case "S":
                this.etapes.add(new Coordonnees(xPosition, yPosition + 1));
                break;
            case "E":
                this.etapes.add(new Coordonnees(xPosition + 1, yPosition));
                break;
            case "O":
                this.etapes.add(new Coordonnees(xPosition - 1, yPosition));
                break;
            default:
                break;
        }
    }

    /**
     * Retourne la liste des étapes
     * @return List<Coordonnees> : La liste des étapes
     */
    protected List<Coordonnees> getEtapes() {
        return etapes;
    }

    /**
     * Retourne la dernière étape du parcours
     * @return Coordonnees : La dernière étape du parcours
     */
    public Coordonnees getEtapeActuelle() {
        return this.etapes.get(this.etapes.size() - 1);
    }
}
