package Java;

public class Coordonnees {
    private int x;
    private int y;

    /**
     * Crée des coordonnées avec une position donnée
     * @param x La position en abscisse
     * @param y La position en ordonnée
     * @param obstacleRencontre True si un obstacle a été rencontré, false sinon
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
