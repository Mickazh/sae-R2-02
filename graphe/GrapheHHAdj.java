package graphe;

public class GrapheHHAdj extends Arc{
    public GrapheHHAdj(String source, String destination, int valeur) {
        super(source, destination, valeur);
        this.getDestination();
    }
}
