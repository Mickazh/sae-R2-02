package main;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import graphe.IGrapheConst;

public class CheminCourt {

    public static void Dijkstra(String source, String destination, IGrapheConst graphe){
        ArrayList<String> sommets = new ArrayList<>(graphe.getSommets());
        HashMap<String, Double> dist = new HashMap<>();
        HashMap<String, String> prev = new HashMap<>();
        double alt = 0;
        for (String sommet : sommets) {
            dist.put(sommet, Double.POSITIVE_INFINITY); //initialiser plutot à null car on ne peut pas representer l'infinie avec la classe Integer
            prev.put(sommet, null);
        }
        dist.put(source, 0d);
        for (String succ : graphe.getSucc(source)) {
            dist.put(succ, (double)graphe.getValuation(source, succ));
            prev.put(succ, source);
        }
        String u;
        while (!sommets.isEmpty()){
            u = getMinVal(dist, graphe, sommets);
            sommets.remove(u);
            

            for (String v : graphe.getSucc(u)) {
                if (!dist.containsKey(v))
                    continue;
                alt = dist.get(u) + (double)graphe.getValuation(u, v);
                if (alt < dist.get(v)){
                    dist.put(v, alt);
                    prev.put(v, u);
                }
            }
        }
        boolean first = true;
        String noeu = destination;
        do {
            if (!first){
                System.out.print("- ");
            }
            System.out.print(noeu + " ");
            first = false;
            noeu = prev.get(noeu);
        } while (noeu != null);
        System.out.println("longueur : " + dist.get(destination).intValue());
    }

    /**
     * lien du pseudo code https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm#Pseudocode
     * @param graphe
     * @param source
     * @param dist
     * @param pred
     */
    public static void dijkstra(IGrapheConst graphe, String source, Map<String, Integer> dist, Map<String, String> pred){
        ArrayList<String> sommets = new ArrayList<>(graphe.getSommets());
        Integer alt = 0;
        for (String sommet : sommets) {
            dist.put(sommet, null); //initialiser plutot à null car on ne peut pas representer l'infinie avec la classe Integer
            pred.put(sommet, null);
        }
        dist.put(source, 0);
        for (String succ : graphe.getSucc(source)) {
            dist.put(succ, graphe.getValuation(source, succ));
            pred.put(succ, source);
        }

        String u;
        while (!sommets.isEmpty()){
            u = getMinVal(dist, graphe, sommets);
            sommets.remove(u);

            for (String v : graphe.getSucc(u)) {
                if (!dist.containsKey(v)) // à voir si cela fait gagner du temps
                    continue;
                // try {
                //     alt = dist.get(u) + graphe.getValuation(u, v);
                // } catch (NullPointerException e) { // cas où dist.get(v) = +infinity
                //     alt = graphe.getValuation(u, v);
                // }
                alt = graphe.getValuation(u, v);
                if (dist.get(u) != null){
                    alt += dist.get(u);
                }
                if (dist.get(v) == null || alt < dist.get(v)){
                    dist.put(v, alt);
                    pred.put(v, u);
                }
            }
        }
    }
        
    private static String getMinVal(Map<String, Integer> dist, IGrapheConst graphe, ArrayList<String> sommets) {
        // Integer valMin = null;
        // String noeuMin = "";
        // for (String noeu : sommets) {
        //     // try {
        //         if (dist.get(noeu) == null || valMin == null|| dist.get(noeu) < valMin){
        //             valMin = dist.get(noeu);
        //             noeuMin = noeu;
        //         }
        //     // } catch (Exception e) {
        //     //     valMin = dist.get(noeu);
        //     //     noeuMin = noeu;
        //     // }
        // }
        // return noeuMin;
        Double valMin = Double.POSITIVE_INFINITY;
        Double val = null;
        String noeuMin = "";
        for (String noeu : sommets) {
            if (dist.get(noeu) == null){
                val = Double.POSITIVE_INFINITY;
            }
            else{
                val = Double.valueOf(dist.get(noeu));
            }
            if (val < valMin){
                valMin = val;
                noeuMin = noeu;
            }
        }
        return noeuMin;
    }

    // private String getMinVal(String u, IGrapheConst graphe){
    //     Double valMin = Double.POSITIVE_INFINITY;
    //     String noeuMin = "";
    //     for (String noeu : graphe.getSucc(u)) {
    //         if (graphe.getValuation(u, noeu) < valMin){
    //             valMin = (double)graphe.getValuation(u, noeu);
    //             noeuMin = noeu;
    //         }
    //     }
    //     return noeuMin;
    // }
    private static String getMinVal(HashMap<String, Double> dist, IGrapheConst graphe, ArrayList<String> sommets){
        Double valMin = Double.POSITIVE_INFINITY;
        String noeuMin = "";
        for (String noeu : sommets) {
            if (dist.get(noeu) < valMin){
                valMin = dist.get(noeu);
                noeuMin = noeu;
            }
        }
        return noeuMin;
    }
}