package graphe;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GrapheHHAdj implements IGraphe{

    private Map<String, Map<String, Integer>> hhadj;

    public GrapheHHAdj() {
        hhadj = new HashMap<>();
    }

    @Override
    public List<String> getSommets() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSommets'");
    }

    @Override
    public List<String> getSucc(String sommet) {
        // TODO Auto-generated method stub
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
        //ajouterSommet(source);
        //ajouterSommet(destination);
        //hhadj.get(source).put(destination, valeur);
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
