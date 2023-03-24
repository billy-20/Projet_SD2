import java.io.*;
import java.util.*;


public class Graph {


    private HashMap<String, Ligne> ligneTroncon;

    private Map<String, HashSet<Troncon>> mapTroncons; // String = station


    public Graph(File lignes, File troncons) {
        String str;


        this.ligneTroncon = new HashMap<String, Ligne>();

        this.mapTroncons = new HashMap<String, HashSet<Troncon>>();


        // lignes
        try (FileReader aer = new FileReader(
                lignes); BufferedReader objLigne = new BufferedReader(aer)) {

            String[] tabLigne;

            while ((str = objLigne.readLine()) != null) {
                tabLigne = str.split(",");

                String source = tabLigne[0];
                Ligne l2 = new Ligne(Integer.parseInt(tabLigne[0]), tabLigne[1], tabLigne[2], tabLigne[3], tabLigne[4], Integer.parseInt(tabLigne[5]));


                ligneTroncon.put(source, l2);
                mapTroncons.put(source, new HashSet<Troncon>());

            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        // troncons
        remplirGrapheAvecTroncons(troncons);

    }

    private void remplirGrapheAvecTroncons(File troncons) {
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

                HashSet<Troncon> troncons2 = mapTroncons.get(stationDepart);

                if (troncons2 != null) {
                    troncons2.add(troncon);
                } else {
                    HashSet<Troncon> troncon3 = new HashSet<Troncon>();
                    troncon3.add(troncon);
                    mapTroncons.put(stationDepart, troncon3);
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public Deque<Troncon> bfs(String source, String destination) {


        Deque<String> queue = new ArrayDeque<String>();
        Set<String> stationsVisites = new HashSet<String>();
        Map<String, Troncon> trajet = new HashMap<String, Troncon>();

        queue.addFirst(source);
        String sourceActuelle = source;
        stationsVisites.add(sourceActuelle);


        while (!queue.isEmpty() && !sourceActuelle.equals(destination)) {
            sourceActuelle = queue.removeFirst();
            Set<Troncon> troncons2 = mapTroncons.get(sourceActuelle);

            if (troncons2 != null) {
                for (Troncon t : troncons2) {
                    String dest = t.getArrivee();
                    if (!stationsVisites.contains(dest)) {
                        stationsVisites.add(dest);
                        trajet.put(dest, t);
                        queue.add(dest);
                    }
                }
            }

        }
        /*
        CHECK POINT 2 :
         */

        String depart = destination;
        Deque<Troncon> troncons = new ArrayDeque<Troncon>();


        boolean b = false;
        while (!b) {
            Troncon troncon = trajet.get(depart);
            troncons.addFirst(troncon);
            depart = troncon.getDepart();
            if (depart.equals(source)) {
                b = true;
                break;
            }
        }


        return troncons;
    }


    public void calculerCheminMinimisantNombreTroncons(String source, String destination) {

        if (source.equals(destination)) {
            throw new IllegalArgumentException("tu te trouve deja dans cette station");
        }

        Deque<Troncon> troncons = bfs(source, destination);

        int duree = 0;
        int dureeTotale = 0;
        String nomLigne = " ";

        for (Troncon v : troncons) {
            if (!nomLigne.equals(v.getLigne().getNom())) {
                dureeTotale += v.getLigne().getAttenteMoyenne();
            }
            nomLigne = v.getLigne().getNom();
            duree += v.getDuree();
            System.out.println(v.toString());
        }
        System.out.println("nombreTroncons = " + troncons.size());
        System.out.println("dureeTransport = " + duree + " , " + "dureeTotale = " + (dureeTotale + duree));
    }

    public void calculerCheminMinimisantTempsTransport(String source, String destination) {
        if (source.equals(destination)) {
            throw new IllegalArgumentException("tu te trouve deja dans cette station");
        }
        Deque<String> queue = new ArrayDeque<String>();
        Map<String, Integer> durees = new HashMap<String, Integer>();
        Map<String, Troncon> trajet = new HashMap<String, Troncon>();

        queue.add(source);
        durees.put(source, 0);

        while (!queue.isEmpty()) {
            String station = queue.remove();

            Set<Troncon> troncons = mapTroncons.get(station);
            if (troncons != null) {
                for (Troncon troncon : troncons) {
                    String destinationActuelle = troncon.getArrivee();
                    int dureeDepuisSource = durees.get(station) + troncon.getDuree() + troncon.getLigne().getAttenteMoyenne();

                    if (!durees.containsKey(destinationActuelle) || dureeDepuisSource < durees.get(destinationActuelle)) {
                        durees.put(destinationActuelle, dureeDepuisSource);
                        trajet.put(destinationActuelle, troncon);
                        if (!destinationActuelle.equals(destination)) {
                            queue.add(destinationActuelle);
                        }
                    }
                }
            }
        }

        int dureeTransport = 0;
        String stationActuelle = destination;
        Deque<Troncon> chemin = new ArrayDeque<Troncon>();
        String nomLigne = "";

        while (trajet.containsKey(stationActuelle)) {
            Troncon troncon = trajet.get(stationActuelle);
            chemin.addFirst(troncon);
            dureeTransport += troncon.getDuree();
            stationActuelle = troncon.getDepart();
        }

        int dureeTotale = 0;

        for (Troncon v : chemin) {
            if (!nomLigne.equals(v.getLigne().getNom())) {
                dureeTotale += v.getLigne().getAttenteMoyenne();
            }
            nomLigne = v.getLigne().getNom();
            System.out.println(v.toString());
        }
        System.out.println("nombreTroncons = " + chemin.size());
        System.out.println("duree transport = " + dureeTransport + " dureeTtotale = " + (dureeTotale + dureeTransport));
    }

    public void fusionertroncons(String source, String destination) {
        Deque<Troncon> troncons = bfs(source, destination);
        Deque<Deplacement> deplacements = new ArrayDeque<>();

        // first troncon
        String nomLigne = troncons.getFirst().getLigne().getNom();
        int dureeTransportTotal = 0;
        int nbTronconsTotal = 0;
        String depart = troncons.getFirst().getDepart();
        int attenteMoyenne = troncons.getFirst().getLigne().getAttenteMoyenne();
        String type = troncons.getFirst().getLigne().getType();
        String direction = troncons.getFirst().getLigne().getDestination();

        for (Troncon t : troncons) {
            if (!nomLigne.equals(t.getLigne().getNom())) {

                Deplacement dFirst = new Deplacement(nomLigne, depart, t.getDepart(),
                        attenteMoyenne, dureeTransportTotal,
                        nbTronconsTotal, type, direction);

                deplacements.add(dFirst);

                direction = t.getLigne().getDestination();
                depart = t.getDepart();
                attenteMoyenne = t.getLigne().getAttenteMoyenne();
                type = t.getLigne().getType();
                nomLigne = t.getLigne().getNom();

                dureeTransportTotal = t.getDuree();


            } else {
                dureeTransportTotal += t.getDuree();
                nbTronconsTotal++;

            }

        }

        // dernier troncon
        Deplacement d = new Deplacement(nomLigne, troncons.getLast().getDepart(), troncons.getLast().getArrivee(), troncons.getLast().getLigne().getAttenteMoyenne(), dureeTransportTotal,
                nbTronconsTotal, troncons.getLast().getLigne().getType(), troncons.getLast().getLigne().getDestination());
        deplacements.add(d);


        int dureeTransport = 0;
        int dureeTotale = 0;
        for (Deplacement de : deplacements) {
            dureeTransport += de.getDureeTotale();
            dureeTotale += de.getCout();
            System.out.println(de);
        }

        System.out.println("nbTroncons=" + troncons.size());
        System.out.println("dureeTransport=" + dureeTransport + " dureeTotale = " + (dureeTotale + dureeTransport));


    }
}
