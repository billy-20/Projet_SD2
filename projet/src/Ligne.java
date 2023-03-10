

public class Ligne {

    private  int id;
    private  String nom;
    private String source;
    private  String destination;
    //public static final String[] TYPE = {"metro" , "tram" , "bus"};

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
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public int getAttenteMoyenne() {
        return attenteMoyenne;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setAttenteMoyenne(int attenteMoyenne) {
        this.attenteMoyenne = attenteMoyenne;
    }

    @Override
    public String toString() {
        return "Ligne[" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", source='" + source + '\'' +
                ", destination='" + destination + '\'' +
                ", attenteMoyenne=" + attenteMoyenne +
                ']';
    }
}
