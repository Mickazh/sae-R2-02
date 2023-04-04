package graphe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GrapheLAdj implements IGraphe {

    private Map<String, List<Arc>> ladj = new HashMap<>();

    @Override
    public List<String> getSommets() {
        return new ArrayList<>(this.ladj.keySet());
    }

    @Override
    public List<String> getSucc(String sommet) {
        List<String> succ = new ArrayList<>();
        if (this.ladj.containsKey(sommet)) {
            for (Arc arc : this.ladj.get(sommet)) {
                succ.add(arc.getDestination());
            }
        }
        return succ;
    }

    @Override
    public int getValuation(String src, String dest) {
        if (this.ladj.containsKey(src)) {
            for (Arc arc : this.ladj.get(src)) {
                if (arc.getDestination().equals(dest)) {
                    return arc.getValuation();
                }
            }
        }
        return -1;
    }

    @Override
    public boolean contientSommet(String sommet) {
        return this.ladj.containsKey(sommet);
    }

    @Override
    public boolean contientArc(String src, String dest) {
        if (this.ladj.containsKey(src)) {
            for (Arc arc : this.ladj.get(src)) {
                if (arc.getDestination().equals(dest)) {
                    return true;
                }
            }
        }
        return false;
    }
/**/
    @Override
    public void ajouterSommet(String noeud) {
        if (!this.ladj.containsKey(noeud)) {
            this.ladj.put(noeud, new ArrayList<>());
        }
    }

    @Override
    public void ajouterArc(String source, String destination, Integer valeur) {
        if (!this.ladj.containsKey(source)) {
            this.ladj.put(source, new ArrayList<>());
        }
        Arc arc = new Arc(source, destination, valeur);
        this.ladj.get(source).add(arc);
    }

    @Override
    public void oterSommet(String noeud) {
        if (this.ladj.containsKey(noeud)) {
            this.ladj.remove(noeud);
            for (String sommet : this.ladj.keySet()) {
                List<Arc> succ = this.ladj.get(sommet);
                succ.removeIf(arc -> arc.getDestination().equals(noeud));
            }
        }
    }

    @Override
    public void oterArc(String source, String destination) {
        if (this.ladj.containsKey(source)) {
            List<Arc> succ = this.ladj.get(source);
            succ.removeIf(arc -> arc.getDestination().equals(destination));
        }
    }
}
