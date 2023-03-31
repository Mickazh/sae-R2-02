package graphe;

import java.util.ArrayList;
import java.util.List;

public class GrapheLArcs implements IGraphe{
    private List<Arc> arcs;



    public GrapheLArcs(){
         arcs = new ArrayList<>();
    }

    @Override
    public List<String> getSommets() {
        assert(!arcs.isEmpty());//faudra penser à enlever cet assert, pour que pas ça bloque le programme
        List<String> sommet = new ArrayList<>();
        for (Arc i : arcs) {
            if (!sommet.contains(i.getSource())) {
                sommet.add(i.getSource());
            }
            if (!sommet.contains(i.getDestination())) {
                sommet.add(i.getDestination());
            }
        }
        return sommet;
    }

    @Override
    public List<String> getSucc(String sommet) {
        List<String> successeurs = new ArrayList<>();
        for (Arc i : arcs) {
            if (i.getSource().equals(sommet)) {
                successeurs.add(i.getDestination());
            }
        }
        return successeurs;
    }

    @Override
    public int getValuation(String src, String dest) {
        for (Arc i : arcs) {
            if (i.getSource().equals(src) && i.getDestination().equals(dest)) {
                return i.getValeur();
            }
        }
        return 0;
    }

    @Override
    public boolean contientSommet(String sommet) {
        for (Arc i :arcs){
            if (i.getSource().equals(sommet))
                return true;
        }
        return false;
    }

    @Override
    public boolean contientArc(String src, String dest) {
        for (Arc i :arcs){
            if (i.getSource().equals(src) || i.getSource().equals(dest))
                return true;
        }
        return false;
    }

    @Override
    public void ajouterSommet(String noeud) {
        arcs.add(new Arc(noeud, "", 0));
    }

    @Override
    public void ajouterArc(String source, String destination, Integer valeur) {
        arcs.add(new Arc(source, destination, valeur));
    }

    @Override
    public void oterSommet(String noeud) {
        arcs.removeIf(arc -> arc.getSource().equals(noeud) || arc.getDestination().equals(noeud));
    }

    @Override
    public void oterArc(String source, String destination) {
        arcs.removeIf(arc -> arc.getSource().equals(source) && arc.getDestination().equals(destination));
    }
}
