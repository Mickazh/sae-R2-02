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
        if(contientArc(src,dest)) {
            return hhadj.get(src).get(dest);
        }
        else {
            return -1;
        }
    }

    @Override
    public boolean contientSommet(String sommet) {
        return hhadj.containsKey(sommet);
    }

    @Override
    public boolean contientArc(String src, String dest) {
        return hhadj.containsKey(src) && hhadj.get(src).containsKey(dest);
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
        if (hhadj.containsKey(noeud)) {
            // Supprime les arcs sortants du sommet
            for (String successeur : hhadj.get(noeud).keySet()) {
                hhadj.get(successeur).remove(noeud);
            }
            // Supprime le sommet lui-m�me
            hhadj.remove(noeud);
        }
    }

    @Override
    public void oterArc(String source, String destination) {
        if (contientArc(source, destination)) {
            hhadj.get(source).remove(destination);
        }
    }
}
