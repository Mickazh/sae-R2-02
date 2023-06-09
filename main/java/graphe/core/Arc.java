package graphe.core;

public class Arc {
    private String source, destination;
    private int valeur;

    public Arc(String source, String destination, int valeur) {
        this.source = source;
        this.destination = destination;
        this.valeur = valeur;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Integer getValuation() {
        return valeur;
    }

    public void setValeur(int valeur) {
        this.valeur = valeur;
    }
}