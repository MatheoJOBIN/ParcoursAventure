package Java;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Labyrinthe {
    private char[][] carte;
    private int nbLignes;
    private int nbColonnes;

    /**
     * Crée un labyrinthe à partir d'un fichier
     * 
     * @param cheminFichier Le chemin vers le fichier contenant le labyrinthe
     * @throws IOException Exception levée si le fichier n'existe pas
     */
    public Labyrinthe(String cheminFichier) throws IOException {
        this.carte = chargerLabyrinthe(cheminFichier);
        this.nbLignes = carte.length;
        this.nbColonnes = (carte.length > 0) ? carte[0].length : 0;
    }

    /**
     * Charge le labyrinthe depuis le fichier
     * 
     * @param cheminFichier Le chemin vers le fichier contenant le labyrinthe
     * @return char[][] : La matrice représentant le labyrinthe
     * @throws IOException Exception levée si le fichier n'existe pas
     */
    private char[][] chargerLabyrinthe(String cheminFichier) throws IOException {
        BufferedReader lecteurFichier = new BufferedReader(new FileReader(cheminFichier));

        // Vérifie la taille du labyrinthe
        String ligne;
        while ((ligne = lecteurFichier.readLine()) != null) {
            nbLignes++;
            nbColonnes = Math.max(nbColonnes, ligne.length());
        }

        lecteurFichier.close();

        // Initialisation de la matrice représentant le labyrinthe
        char[][] labyrinthe = new char[nbLignes][nbColonnes];

        // Chargement du labyrinthe
        lecteurFichier = new BufferedReader(new FileReader(cheminFichier));
        for (int i = 0; i < nbLignes; i++) {
            ligne = lecteurFichier.readLine();
            for (int j = 0; j < nbColonnes; j++) {
                if (j < ligne.length()) {
                    labyrinthe[i][j] = ligne.charAt(j);
                } else {
                    labyrinthe[i][j] = ' ';
                }
            }
        }

        lecteurFichier.close();

        return labyrinthe;
    }

    /**
     * Affiche le labyrinthe
     */
    public void afficher() {
        for (char[] ligne : carte) {
            for (char caseLabyrinthe : ligne) {
                System.out.print(caseLabyrinthe);
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Vérifie si les coordonnées sont dans le labyrinthe
     * 
     * @param x Coordonnées en abscisse
     * @param y Coordonnées en ordonnée
     * @return boolean : True si les coordonnées sont dans le labyrinthe, false sinon
     */
    public boolean checkInbound(int x, int y) {
        return x >= 0 && y >= 0 && x < nbColonnes && y < nbLignes;
    }

    /**
     * Vérifie si la case est libre
     * 
     * @param x Coordonnées en abscisse
     * @param y Coordonnées en ordonnée
     * @return boolean : True si la case est libre, false sinon
     */
    public boolean checkFree(int x, int y) {
        if (carte[y][x] == '#') {
            return false;
        } else {
            return true;
        }
    }
}
