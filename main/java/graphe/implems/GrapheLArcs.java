package graphe.implems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import graphe.core.Arc;

public class GrapheLArcs extends Graphe{
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
                return i.getValuation();
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
        if (!contientArc(source, destination) && valeur>0) {
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

    /*public String toString() {
        StringBuilder sb = new StringBuilder();
        List<String> sommets = getSommets();
        Collections.sort(sommets);

        for (String sommet : sommets) {
            List<String> succ = getSucc(sommet);
            if (succ.isEmpty()) {
                sb.append(sommet).append(":, ");
            }
            else {
                Collections.sort(succ);
                for (String successeur : succ) {
                    int valeur = getValuation(sommet, successeur);
                    sb.append(sommet).append("-").append(successeur);
                    sb.append("(").append(valeur).append("), ");
                }
            }
        }

        // Remove the last comma and space
        if (sb.length() > 2) {
            sb.setLength(sb.length() - 2);
        }

        return sb.toString();
    }*/
}
