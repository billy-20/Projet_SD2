public class Station {

    private String nom;

    private int distance;


    public Station(String nom , int distance) {
        this.nom = nom;
        this.distance=distance;
    }

    public int getDistance() {
        return distance;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

}
