package Java;

public class Coordonnees {
    private final int x;
    private final int y;

    /**
     * Crée des coordonnées avec une position donnée
     * @param x La position en abscisse
     * @param y La position en ordonnée
     */
    public Coordonnees(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Retourne la position en abscisse
     * @return int : La position en abscisse
     */
    public int getX() {
        return x;
    }

    /**
     * Retourne la position en ordonnée
     * @return int : La position en ordonnée
     */
    public int getY() {
        return y;
    }
}
