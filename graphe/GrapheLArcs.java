package graphe;

import java.util.List;

public class GrapheLArcs implements IGraphe{
    private List<Arc> arcs;



    public GrapheLArcs(){
         arcs = new ArraysList<Arc>();
    }

    @Override
    public List<String> getSommets() {
        return new ArrayList<Arc>();
    }

    @Override
    public List<String> getSucc(String sommet) {
        return new ArrayList<Arc>(arcs.get(sommet));
    }

    @Override
    public int getValuation(String src, String dest) {
        if(contientArc(src,dest))
            return arcs.get(src) && arcs.get(dest);
    }

    @Override
    public boolean contientSommet(String sommet) {

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
