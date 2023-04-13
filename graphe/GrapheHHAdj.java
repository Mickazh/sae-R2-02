package graphe;

import java.util.*;

public class GrapheHHAdj extends Graphe implements IGraphe{

    private Map<String, Map<String, Integer>> hhadj;

    public GrapheHHAdj(){
        hhadj = new HashMap<>();
    }
    /*public GrapheHHAdj(String grapheTxt){
        this();
        peupler(grapheTxt);
    }*/

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
        if (contientArc(src, dest)) {
            return hhadj.get(src).get(dest);
        } else {
            throw new RuntimeException("Aucun arc trouve entre " + src + " et " + dest);
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
        if (!contientArc(source,destination) && valeur > 0){
            ajouterSommet(source);
            ajouterSommet(destination);
            hhadj.get(source).put(destination, valeur);
        }
        else {
            throw new IllegalArgumentException();
        }

    }

    @Override
    public void oterSommet(String noeud) {
        if (hhadj.containsKey(noeud)) {
            // Supprime les arcs sortants du sommet
            for (String successeur : hhadj.get(noeud).keySet()) {
                hhadj.get(successeur).remove(noeud);
            }
            // Supprime le sommet de la liste des successeurs des autres sommets
            for (String sommet : hhadj.keySet()) {
                hhadj.get(sommet).remove(noeud);
            }
            // Supprime le sommet lui-meme
            hhadj.remove(noeud);
        }
    }

    @Override
    public void oterArc(String source, String destination) {
        if (contientArc(source, destination)) {
            hhadj.get(source).remove(destination);
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    /*public String toString() {
        StringBuilder sb = new StringBuilder();
        Set<String> triSommet = new TreeSet<>(hhadj.keySet()); // trie les sommets par ordre alphab�tique
        for (String sommet : triSommet) {
            Map<String, Integer> succ = hhadj.get(sommet);
            Set<String> triSucc = new TreeSet<>(succ.keySet()); // trie les successeurs d'un sommet par ordre alphab�tique
            if (triSucc.isEmpty()) {
                sb.append(sommet).append(":, ");
            }
            else {
                for (String successeur : triSucc) {
                    sb.append(sommet).append("-").append(successeur).append("(").append(succ.get(successeur)).append("), ");
                }
            }
        }

        if (sb.length() > 0) {
            sb.setLength(sb.length() - 2); // Retire le dernier ", "
        }
        return sb.toString();
    }*/
}
