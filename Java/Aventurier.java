package Java;

public class Aventurier {
    private String nom = "John Doe"; 
    private int xPosition = 0;
    private int yPosition = 0;

    /**
     * Crée un aventurier avec une position initiale donnée
     * @param xPosition La position initiale en abscisse
     * @param yPosition La position initiale en ordonnée
     * @param labyrinthe Le labyrinthe dans lequel l'aventurier se trouve
     */
    public Aventurier(String nom, int xPosition, int yPosition, Labyrinthe labyrinthe) {
        if(xPosition < 0 || yPosition < 0 || !labyrinthe.checkInbound(xPosition, yPosition) || !labyrinthe.checkFree(xPosition, yPosition)) {
            throw new IllegalArgumentException("La position initiale de l'aventurier est invalide.");
        }else{
            this.nom = nom;
            this.xPosition = xPosition;
            this.yPosition = yPosition;
        }
    }

    /**
     * Déplace l'aventurier dans la direction donnée
     * @param direction La direction dans laquelle l'aventurier doit se déplacer (N, S, E ou O)
     */
    public void deplacer(String direction) {
        switch (direction) {
            case "N":
                this.yPosition--;
                break;
            case "S":
                this.yPosition++;
                break;
            case "E":
                this.xPosition++;
                break;
            case "O":
                this.xPosition--;
                break;
            default:
                break;
        }
    }

    /**
     * Retourne la position en abscisse de l'aventurier
     * @return int : La position en abscisse de l'aventurier
     */
    public int getXPosition() {
        return this.xPosition;
    }

    /**
     * Retourne la position en ordonnée de l'aventurier
     * @return int : La position en ordonnée de l'aventurier
     */
    public int getYPosition() {
        return this.yPosition;
    }

    /**
     * Retourne le nom de l'aventurier
     * @return String : Le nom de l'aventurier
     */
    public String getNom() {
        return this.nom;
    }
}
