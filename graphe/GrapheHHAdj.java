package graphe;

import java.util.ArrayList;
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
        return new ArrayList<>(hhadj.keySet());
    }

    @Override
    public List<String> getSucc(String sommet) {
        return new ArrayList<>(hhadj.get(sommet).keySet());
    }

    @Override
    public int getValuation(String src, String dest) {
        return hhadj.get(src).get(dest);
    }

    @Override
    public boolean contientSommet(String sommet) {
        return hhadj.containsKey(sommet);
    }

    @Override
    public boolean contientArc(String src, String dest) {
        return true;
    }

    @Override
    public void ajouterSommet(String noeud) {
        hhadj.putIfAbsent(noeud,new HashMap<>());
    }

    @Override
    public void ajouterArc(String source, String destination, Integer valeur) {
        ajouterSommet(source);
        ajouterSommet(destination);
        hhadj.get(source).put(destination, valeur);
    }

    @Override
    public void oterSommet(String noeud) {
        int c = 5;
    }

    @Override
    public void oterArc(String source, String destination) {
        int c = 5;
    }
}
