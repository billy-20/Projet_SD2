public class Troncon {

    private String depart;

    private String arrivee;

    private int duree;

    private Ligne ligne;



    public Troncon(String depart, String arrivee, int duree, Ligne ligne) {
        this.depart = depart;
        this.arrivee = arrivee;
        this.duree = duree;
        this.ligne = ligne;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public void setArrivee(String arrivee) {
        this.arrivee = arrivee;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public void setLigne(Ligne ligne) {
        this.ligne = ligne;
    }

    public String getDepart() {
        return depart;
    }

    public String getArrivee() {
        return arrivee;
    }

    public int getDuree() {
        return duree;
    }

    public Ligne getLigne() {
        return ligne;
    }

    @Override
    public String toString() {
        return "Troncon[" +
                "depart='" + depart + '\'' +
                ", arrivee='" + arrivee + '\'' +
                ", duree=" + duree +
                ", ligne=" + ligne +
                ']';
    }
}
