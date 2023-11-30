package Java;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String cheminLabyrinthe = "./Ressources/carte.txt";
        String cheminAventuriers = "./Ressources/Aventuriers";

        try {
            // Récupération du labyrinthe
            Labyrinthe labyrinthe = new Labyrinthe(cheminLabyrinthe);

            // Affichage du labyrinthe
            labyrinthe.afficher();

            // Récupération de tous les aventuriers
            File dossierAventuriers = new File(cheminAventuriers);
            File[] fichiersAventuriers = dossierAventuriers.listFiles();

            if (fichiersAventuriers != null) {
                for (File fichierAventurier : fichiersAventuriers) {
                    if (fichierAventurier.isFile()) {
                        // Création de l'aventurier
                        Aventurier aventurier = nouvelAventurier(fichierAventurier.getAbsolutePath(), labyrinthe);

                        // Résolution des déplacements
                        deplacerAventurier(labyrinthe, aventurier,
                                lireDeplacements(fichierAventurier.getAbsolutePath()));

                        // Position finale de 'aventurier
                        System.out.println("Position finale de l'aventurier " + aventurier.getNom() + " : ("
                                + aventurier.getXPosition() + ", "
                                + aventurier.getYPosition() + ")");
                    }
                }
            } else {
                System.out.println("Le dossier d'aventuriers est vide ou n'existe pas.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Crée un aventurier avec une position initiale donnée
     * 
     * @param cheminFichier Le chemin vers le fichier contenant les coordonnées initiales
     * @param labyrinthe Le labyrinthe dans lequel l'aventurier se trouve
     * @return Aventurier : L'aventurier créé
     * @throws IOException Exception levée si le fichier n'existe pas
     */
    private static Aventurier nouvelAventurier(String cheminFichier, Labyrinthe labyrinthe) throws IOException {
        BufferedReader lecteurFichier = new BufferedReader(new FileReader(cheminFichier));
        // Lisez la première ligne qui contient les coordonnées initiales
        String[] coordonnees = lecteurFichier.readLine().split(",");
        String nomAventurier = cheminFichier.substring(cheminFichier.lastIndexOf(File.separator) + 1,
                cheminFichier.lastIndexOf("."));
        lecteurFichier.close();

        // Création de l'aventurier
        Aventurier aventurier = new Aventurier(nomAventurier, Integer.parseInt(coordonnees[0]),
                Integer.parseInt(coordonnees[1]),
                labyrinthe);

        return aventurier;
    }

    /**
     * Lit les déplacements depuis le fichier
     * 
     * @param cheminFichier Le chemin vers le fichier contenant les déplacements
     * @return String : Les déplacements
     * @throws IOException Exception levée si le fichier n'existe pas
     */
    private static String lireDeplacements(String cheminFichier) throws IOException {
        BufferedReader lecteurFichier = new BufferedReader(new FileReader(cheminFichier));
        // Lisez la deuxième ligne qui contient les déplacements
        lecteurFichier.readLine(); // Ignorez la première ligne (coordonnées initiales)
        String deplacements = lecteurFichier.readLine();
        lecteurFichier.close();
        return deplacements;
    }

    /**
     * Déplace l'aventurier dans le labyrinthe
     * 
     * @param labyrinthe   Le labyrinthe dans lequel l'aventurier se trouve
     * @param aventurier   L'aventurier à déplacer
     * @param deplacements Les déplacements à effectuer
     */
    private static void deplacerAventurier(Labyrinthe labyrinthe, Aventurier aventurier, String deplacements) {
        for (char deplacement : deplacements.toCharArray()) {

            int x = aventurier.getXPosition();
            int y = aventurier.getYPosition();

            switch (deplacement) {
                case 'N':
                    if (labyrinthe.checkFree(x, y - 1) && labyrinthe.checkInbound(x, y - 1)) {
                        aventurier.deplacer("N");
                    }
                    break;
                case 'S':
                    if (labyrinthe.checkFree(x, y + 1) && labyrinthe.checkInbound(x, y + 1)) {
                        aventurier.deplacer("S");
                    }
                    break;
                case 'E':
                    if (labyrinthe.checkFree(x + 1, y) && labyrinthe.checkInbound(x + 1, y)) {
                        aventurier.deplacer("E");
                    }
                    break;
                case 'O':
                    if (labyrinthe.checkFree(x - 1, y) && labyrinthe.checkInbound(x - 1, y)) {
                        aventurier.deplacer("O");
                    }
                    break;
                default:
                    System.out.println("Déplacement invalide : " + deplacement);
            }
        }
    }
}
