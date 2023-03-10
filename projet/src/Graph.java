import java.io.*;
import java.util.*;


public class Graph {

    private HashMap<String, Station> stations;

    private HashMap<String, Ligne> ligneTroncon;
    private Map<String, HashSet<Troncon>> test2;

    public Graph(File lignes, File troncons) {
        String str;

        this.stations = new HashMap<String, Station>();

        this.ligneTroncon = new HashMap<String, Ligne>();

        this.test2 = new HashMap<String, HashSet<Troncon>>();


        // lignes
        try (FileReader aer = new FileReader(
                lignes); BufferedReader objLigne = new BufferedReader(aer)) {

            String[] tabLigne;

            while ((str = objLigne.readLine()) != null) {
                tabLigne = str.split(",");

                Ligne l2 = new Ligne(Integer.parseInt(tabLigne[0]), tabLigne[1], tabLigne[2], tabLigne[3], tabLigne[4], Integer.parseInt(tabLigne[5]));


                ligneTroncon.put(tabLigne[0], l2);
                test2.put(tabLigne[0], new HashSet<Troncon>());
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // troncons
        /*try (FileReader aer = new FileReader(troncons); BufferedReader objTroncon = new BufferedReader(aer)) {
            String[] tabTroncon;

            while ((str = objTroncon.readLine()) != null) {
                tabTroncon = str.split(",");

                String ligneId = tabTroncon[0];
                Ligne ligne = ligneTroncon.get(ligneId);

                if (ligne == null) {
                    throw new IllegalArgumentException("Ligne inconnue : " + ligneId);
                }

                Troncon troncon = new Troncon(tabTroncon[1], tabTroncon[2], Integer.parseInt(tabTroncon[3]), ligne);

                HashSet<Troncon> troncons2 = test2.get(ligneId);
                troncons2.add(troncon);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);

        }


         */
        remplirGrapheAvecTroncons(troncons);

    }
    public void remplirGrapheAvecTroncons(File troncons) {
        try (FileReader fr = new FileReader(troncons); BufferedReader br = new BufferedReader(fr)) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] tronconInfo = line.split(",");
                String ligneId = tronconInfo[0];
                Ligne ligne = ligneTroncon.get(ligneId);
                if (ligne == null) {
                    throw new IllegalArgumentException("Ligne inconnue : " + ligneId);
                }
                String stationDepart = tronconInfo[1];
                String stationArrivee = tronconInfo[2];
                int duree = Integer.parseInt(tronconInfo[3]);
                Troncon troncon = new Troncon(stationDepart, stationArrivee, duree, ligne);
                HashSet<Troncon> troncons2 = test2.get(ligneId);
                troncons2.add(troncon);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


            public void calculerCheminMinimisantNombreTroncons(String source, String destination) {
                // Vérifier que les stations source et destination existent dans le graphe
            }



}
