package graphe;

import java.util.List;

public class GrapheLArcs implements IGraphe{
    private List<Arc> arcs;



    public GrapheLArcs(){
         arcs = new ArraysList<Arc>();
    }

    @Override
    public List<String> getSommets() {
        List<String> sommet = new ArrayList<String>();
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
        List<String> successeurs = new ArrayList<String>();
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
            if (i.getSource().equals(sommet) && i.getDestination().equals(successeur)) {
                return i.getValeur();
            }
        }
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

    }

    @Override
    public void ajouterSommet(String noeud) {

    }

    @Override
    public void ajouterArc(String source, String destination, Integer valeur) {

    }

    @Override
    public void oterSommet(String noeud) {

    }

    @Override
    public void oterArc(String source, String destination) {

    }
}
