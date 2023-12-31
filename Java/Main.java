package Java;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        String cheminLabyrinthe = "./Ressources/carte.txt";
        String cheminAventuriers = "./Ressources/Aventuriers";

        Map<String, Aventurier> aventuriers = new HashMap<>();

        try {
            // Récupération du labyrinthe
            Labyrinthe labyrinthe = new Labyrinthe(cheminLabyrinthe);

            // Affichage du labyrinthe
            labyrinthe.afficher();

            // Récupération de tous les aventuriers
            File dossierAventuriers = new File(cheminAventuriers);
            // Vérification de l'existence du dossier Aventuriers
            if (dossierAventuriers.exists() && dossierAventuriers.isDirectory()) {
                File[] fichiersAventuriers = dossierAventuriers.listFiles();

                // Vérification de l'existence d'aventuriers dans le dossier
                if (fichiersAventuriers != null) {
                    for (File fichierAventurier : fichiersAventuriers) {
                        if (fichierAventurier.isFile()) {
                            // Création de l'aventurier, et ajout de celui-ci à la liste des aventuriers
                            Aventurier aventurier = nouvelAventurier(fichierAventurier.getAbsolutePath(), labyrinthe);
                            aventuriers.put(aventurier.getNom(), aventurier);

                            // Résolution des déplacements
                            deplacerAventurier(labyrinthe, aventurier, lireDeplacements(fichierAventurier.getAbsolutePath()));

                            // Position finale de 'aventurier
                            int positionFinaleX = aventurier.getActualPosition().getX();
                            int positionFinaleY = aventurier.getActualPosition().getY();

                            aventurier.afficherParcours();
                            System.out.println("\n Position finale de l'aventurier " + aventurier.getNom() + " : ("
                                    + positionFinaleX + ", "
                                    + positionFinaleY + ")\n");
                        }
                    }
                } else {
                    System.out.println("Le dossier des aventuriers est vide.");
                }
            } else {
                System.out.println("Le dossier des aventuriers n'existe pas.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Crée un aventurier avec une position initiale donnée
     * 
     * @param cheminFichier Le chemin vers le fichier contenant les coordonnées initiales
     * @param labyrinthe    Le labyrinthe dans lequel l'aventurier se trouve
     * @return Aventurier : L'aventurier créé
     * @throws IOException Exception levée si le fichier n'existe pas
     */
    private static Aventurier nouvelAventurier(String cheminFichier, Labyrinthe labyrinthe) throws IOException {
        BufferedReader lecteurFichier = new BufferedReader(new FileReader(cheminFichier));
        // Lisez la première ligne qui contient les coordonnées initiales
        String[] coordonnees = lecteurFichier.readLine().split(",");
        String nomAventurier = cheminFichier.substring(cheminFichier.lastIndexOf(File.separator) + 1, cheminFichier.lastIndexOf("."));
        lecteurFichier.close();

        // Création de l'aventurier
        Aventurier aventurier = new Aventurier(nomAventurier, Integer.parseInt(coordonnees[0]), Integer.parseInt(coordonnees[1]), labyrinthe);

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
        try (BufferedReader lecteurFichier = new BufferedReader(new FileReader(cheminFichier))) {
            lecteurFichier.readLine(); // La première ligne contient les coordonnées de départ, on ne la lit donc pas
            String deplacements = lecteurFichier.readLine();
            return deplacements;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;    // Ne devrait jamais arriver        
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

            int x = aventurier.getActualPosition().getX();
            int y = aventurier.getActualPosition().getY();

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
