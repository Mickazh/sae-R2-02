package graphe;

import java.util.List;

public class GrapheLArcs implements IGraphe{
    private List<Arc> arcs;
    private Arc a;


    public GrapheLArcs(){
         arcs = new ArraysList<Arc>();
    }

    @Override
    public List<String> getSommets() {
        for (Arc i : arcs) {
            this.arcs.add(a.getSource());
        return this.arcs;
        throw new UnsupportedOperationException("Unimplemented method 'getSommets'");
    }

    @Override
    public List<String> getSucc(String sommet) {
            for (Arc i : arcs) {
                this.arcs.add(a.getDestination());
                return this.arcs;
        throw new UnsupportedOperationException("Unimplemented method 'getSucc'");
    }

    @Override
    public int getValuation(String src, String dest) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getValuation'");
    }

    @Override
    public boolean contientSommet(String sommet) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'contientSommet'");
    }

    @Override
    public boolean contientArc(String src, String dest) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'contientArc'");
    }

    @Override
    public void ajouterSommet(String noeud) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'ajouterSommet'");
    }

    @Override
    public void ajouterArc(String source, String destination, Integer valeur) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'ajouterArc'");
    }

    @Override
    public void oterSommet(String noeud) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'oterSommet'");
    }

    @Override
    public void oterArc(String source, String destination) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'oterArc'");
    }
}
