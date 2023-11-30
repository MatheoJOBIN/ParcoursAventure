package Java;

public class Aventurier {
    private String nom = "John Doe";
    private Parcours parcours;

    /**
     * Crée un aventurier avec une position initiale donnée
     * 
     * @param xPosition  La position initiale en abscisse
     * @param yPosition  La position initiale en ordonnée
     * @param labyrinthe Le labyrinthe dans lequel l'aventurier se trouve
     */
    public Aventurier(String nom, int xPosition, int yPosition, Labyrinthe labyrinthe) {
        if (xPosition < 0 || yPosition < 0 || !labyrinthe.checkInbound(xPosition, yPosition)
                || !labyrinthe.checkFree(xPosition, yPosition)) {
            throw new IllegalArgumentException("La position initiale de l'aventurier est invalide.");
        } else {
            this.nom = nom;
            this.parcours = new Parcours(xPosition, yPosition);
        }
    }

    /**
     * Déplace l'aventurier dans la direction donnée
     * 
     * @param direction La direction dans laquelle l'aventurier doit se déplacer (N,
     *                  S, E ou O)
     */
    public void deplacer(String direction) {
        switch (direction) {
            case "N":
                this.parcours.ajouterEtape("N");
                break;
            case "S":
                this.parcours.ajouterEtape("S");
                break;
            case "E":
                this.parcours.ajouterEtape("E");
                break;
            case "O":
                this.parcours.ajouterEtape("O");
                break;
            default:
                break;
        }
    }

    /**
     * Retourne l'étape actuelle de l'aventurier
     * @return Coordonnees : L'étape actuelle de l'aventurier
     */
    public Coordonnees getActualPosition() {
        return parcours.getEtapeActuelle();
    }

    /**
     * Retourne le nom de l'aventurier
     * 
     * @return String : Le nom de l'aventurier
     */
    public String getNom() {
        return this.nom;
    }

    /**
     * Affiche le parcours de l'aventurier
     */
    public void afficherParcours() {
        System.out.println("Parcours de " + this.nom + " :\n");
        for (Coordonnees etape : parcours.getEtapes()) {
            System.out.println(etape.getX() + "," + etape.getY());
        }
    }
}
