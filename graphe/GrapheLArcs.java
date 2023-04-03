package graphe;

import java.util.ArrayList;
import java.util.List;

public class GrapheLArcs implements IGraphe {
    private List<Arc> arcs;


    public GrapheLArcs() {
        arcs = new ArrayList<>();
    }

    @Override
    public List<String> getSommets() {
        List<String> sommet = new ArrayList<>();
        for (Arc i : arcs) {
            if (!sommet.contains(i.getSource())) {
                sommet.add(i.getSource());
            }
            if (!sommet.contains(i.getDestination()) && !i.getDestination().equals("")) {
                sommet.add(i.getDestination());
            }
        }
        return sommet;
    }

    @Override
    public List<String> getSucc(String sommet) {
        List<String> successeurs = new ArrayList<>();
        for (Arc i : arcs) {
            if (i.getSource().equals(sommet) && !i.getDestination().equals("")) {
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
        return -1;
    }

    @Override
    public boolean contientSommet(String sommet) {
        for (Arc i : arcs) {
            if (i.getSource().equals(sommet))
                return true;
        }
        return false;
    }

    @Override
    public boolean contientArc(String src, String dest) {
        for (Arc arc : arcs) {
            if (arc.getSource().equals(src) && arc.getDestination().equals(dest)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void ajouterSommet(String noeud) {
        if (!contientSommet(noeud)) {
            arcs.add(new Arc(noeud, "", 0));
        }
    }

    @Override
    public void ajouterArc(String source, String destination, Integer valeur) {
        if (!contientArc(source, destination)) {
            arcs.add(new Arc(source, destination, valeur));
            return;
        }
        throw new IllegalArgumentException("l'arc est déjà présent");
    }

    @Override
    public void oterSommet(String noeud) {
        arcs.removeIf(i -> contientSommet(noeud));
    }

    @Override
    public void oterArc(String source, String destination) {
        for (Arc arc : arcs)
            if (arc.getSource().equals(source) && arc.getDestination().equals(destination)) {
                arcs.remove(arc);
                return;
            }
        throw new IllegalArgumentException("l'arc n'est pas présent");
    }
}
