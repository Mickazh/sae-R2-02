package graphe;

import java.util.List;

public class GrapheLArcs implements IGraphe{
    private List<Arc> arcs;



    public GrapheLArcs(){
         arcs = new ArraysList<Arc>();
    }

    @Override
    public List<String> getSommets() {
        return new ArrayList<String>();
    }

    @Override
    public List<String> getSucc(String sommet) {
        return new ArrayList<String>(arcs.get(sommet));
    }

    @Override
    public int getValuation(String src, String dest) {

    }

    @Override
    public boolean contientSommet(String sommet) {
        for (String i :arcs){
            if (i.getSommet() == sommet)
                return true;
        }
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
