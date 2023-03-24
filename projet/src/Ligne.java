

public class Ligne {

    private  int id;
    private  String nom;
    private String source;
    private  String destination;

    private String type;
    private  int attenteMoyenne;

    public Ligne(int id, String nom, String source,String destination,String type, int attenteMoyenne) {
        this.id = id;
        this.nom = nom;
        this.source = source;
        this.destination = destination;
        this.attenteMoyenne = attenteMoyenne;
        this.type=type;


    }

    public String getType() {
        return type;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getSource() {
        return this.source;
    }

    public String getDestination() {
        return destination;
    }

    public int getAttenteMoyenne() {
        return attenteMoyenne;
    }



    @Override
    public String toString() {
        return "Ligne[" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", source='" + source + '\'' +
                ", destination='" + destination + '\'' +
                ", type= " +type+
                ", attenteMoyenne=" + attenteMoyenne +
                ']';
    }
}
