public class Deplacement {


    private String ligne;
    private String depart;
    private String arrivee;
    private int cout;
    private int dureeTotale;
    private int nbrTroncons;
    private String type;
    private String direction;

    public Deplacement(String ligne, String depart, String arrivee, int cout, int dureeTotale, int nbrTroncons, String type, String direction) {
        this.ligne = ligne;
        this.depart = depart;
        this.arrivee = arrivee;
        this.cout = cout;
        this.dureeTotale = dureeTotale;
        this.nbrTroncons = nbrTroncons;
        this.type = type;
        this.direction = direction;
    }

    public String getLigne() {
        return ligne;
    }

    public String getDepart() {
        return depart;
    }

    public String getArrivee() {
        return arrivee;
    }

    public int getCout() {
        return cout;
    }

    public int getDureeTotale() {
        return dureeTotale;
    }

    public int getNbrTroncons() {
        return nbrTroncons;
    }

    public String getType() {
        return type;
    }

    public String getDirection() {
        return direction;
    }



    @Override
    public String toString() {
        return "Deplacement[" +
                "ligne=" + ligne +
                ", depart='" + depart + '\'' +
                ", arrivee='" + arrivee + '\'' +
                ", attenteMoyenne =" + cout +
                ", duree=" + dureeTotale +
                ", nbrTroncons=" + nbrTroncons +
                ", type='" + type + '\'' +
                ", direction='" + direction + '\'' +
                ']';
    }
}
