package graphe.algos;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

import graphe.core.IGrapheConst;

public class Dijkstra {
    
    public static void dijkstra(IGrapheConst graphe, String source, Map<String, Integer> dist, Map<String, String> pred) {
        PriorityQueue<String> sommetsAVisite = new PriorityQueue<>(Comparator.comparingInt(dist::get));
        Set<String> sommetsDejaVisite = new HashSet<>();
        dist.put(source, 0);
        sommetsAVisite.offer(source);
    
        while (!sommetsAVisite.isEmpty()) {
            String sommetActuel = sommetsAVisite.poll();

            if (sommetsDejaVisite.contains(sommetActuel)) {continue;}

            sommetsDejaVisite.add(sommetActuel);
            updateDist(sommetActuel, dist, pred, sommetsAVisite, graphe);
        }
    }
    
    private static void updateDist(String sommetActuel, Map<String, Integer> dist, Map<String, String> pred,
        PriorityQueue<String> sommetsAVisite, IGrapheConst graphe) {
        for (String succ : graphe.getSucc(sommetActuel)) {
            int distToSucc = dist.get(sommetActuel) + graphe.getValuation(sommetActuel, succ);
            if (distToSucc < dist.getOrDefault(succ, Integer.MAX_VALUE)) {
                dist.put(succ, distToSucc);
                pred.put(succ, sommetActuel);
                sommetsAVisite.offer(succ);
            }
        }
    }

    // public static void Dijkstra(String source, String destination, IGrapheConst graphe){
        //     ArrayList<String> sommets = new ArrayList<>(graphe.getSommets());
        //     HashMap<String, Double> dist = new HashMap<>();
        //     HashMap<String, String> prev = new HashMap<>();
    //     double alt = 0;
    //     for (String sommet : sommets) {
    //         dist.put(sommet, Double.POSITIVE_INFINITY); //initialiser plutot à null car on ne peut pas representer l'infinie avec la classe Integer
    //         prev.put(sommet, null);
    //     }
    //     dist.put(source, 0d);
    //     for (String succ : graphe.getSucc(source)) {
    //         dist.put(succ, (double)graphe.getValuation(source, succ));
    //         prev.put(succ, source);
    //     }
    //     String u;
    //     while (!sommets.isEmpty()){
    //         u = getMinVal(dist, graphe, sommets);
    //         sommets.remove(u);
            

    //         for (String v : graphe.getSucc(u)) {
    //             if (!dist.containsKey(v))
    //                 continue;
    //             alt = dist.get(u) + (double)graphe.getValuation(u, v);
    //             if (alt < dist.get(v)){
    //                 dist.put(v, alt);
    //                 prev.put(v, u);
    //             }
    //         }
    //     }
    //     boolean first = true;
    //     String noeu = destination;
    //     do {
    //         if (!first){
    //             System.out.print("- ");
    //         }
    //         System.out.print(noeu + " ");
    //         first = false;
    //         noeu = prev.get(noeu);
    //     } while (noeu != null);
    //     System.out.println("longueur : " + dist.get(destination).intValue());
    // }

    /**
     * lien du pseudo code https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm#Pseudocode
     * @param graphe
     * @param source
     * @param dist
     * @param pred
     */
    // public static void dijkstra(IGrapheConst graphe, String source, Map<String, Integer> dist, Map<String, String> pred){

    //     ArrayList<String> sommets = new ArrayList<>(graphe.getSommets()); //utiliser une linkedlist et la trier
    //     Collections.swap(sommets, 0, sommets.indexOf(source));
    //     Integer alt = 0;
    //     sommets.sort(null);
    //     for (String sommet : sommets) {
    //         dist.put(sommet, null); //initialiser plutot à null car on ne peut pas representer l'infinie avec la classe Integer
    //         pred.put(sommet, null);
    //     }
    //     dist.put(source, 0);
    //     for (String succ : graphe.getSucc(source)) {
    //         dist.put(succ, graphe.getValuation(source, succ));
    //         pred.put(succ, source);
    //     }

    //     String u = null;
    //     while (!sommets.isEmpty()){
    //         u = getMinVal(dist, graphe, sommets);
    //         if (u.equals("")){
    //             termineDijkstra(dist, sommets);
    //             return;
    //         }
    //         sommets.remove(u);

    //         for (String v : graphe.getSucc(u)) {
    //             // à voir si cela fait gagner du temps
    //             // if (!sommets.contains(v)){ //il semblerait que cela fasse ralentir le programme car contains parcours tout le tableau
    //             //     continue;
    //             // }
                    
    //             // try {
    //             //     alt = dist.get(u) + graphe.getValuation(u, v);
    //             // } catch (NullPointerException e) { // cas où dist.get(v) = +infinity
    //             //     alt = graphe.getValuation(u, v);
    //             // }
    //             alt = graphe.getValuation(u, v);
    //             if (dist.get(u) != null){
    //                 alt += dist.get(u);
    //             }
    //             if (dist.get(v) == null || alt < dist.get(v)){
    //                 dist.put(v, alt);
    //                 pred.put(v, u);
    //             }
    //         }
    //     }
    // }


    // private static void termineDijkstra(Map<String, Integer> dist, List<String> sommets){
    //     for (String sommet : sommets) {
    //         // dist.put(sommet, -1);
    //         dist.remove(sommet);
    //     }
    // }

    /**
     * Pseudo code https://towardsdatascience.com/an-overview-of-quicksort-algorithm-b9144e314a72.
     * Tri la liste sommets selon la distance.
     * Sur ma machine (Mickaël), je peux faire environ 20 000 appels récursif, donc j'espère que le nombre
     * d'appels récursif n'atteindra pas la limite (limite qui dépend des machines)
     * @param sommets
     * @param dist
     */
    // private static void quickSort(List<String> sommets, Map<String, Integer> dist, int start, int end){
    //     if (start < end){
    //         int pIndex = partition(sommets, dist, start, end);
    //         quickSort(sommets, dist, start, pIndex-1);
    //         quickSort(sommets, dist, pIndex+1, end);
    //     }
    // }
    
    // private static int partition(List<String> sommets, Map<String, Integer> dist, int start, int end) {
    //     Integer pivot = dist.get(sommets.get(end));
    //     int i = start - 1;

    //     for (int j = start; j <= end-1; j++) {
    //         if (dist.get(sommets.get(j)) < pivot) {
    //             i++;
    //             Collections.swap(sommets, i, j);
    //         }
    //     }
    //     Collections.swap(sommets, i+1, end);
    //     return i + 1;
    // }

    /**
     * 
     * @param dist
     * @param graphe
     * @param sommets
     * @return
     */
    // private static String getMinVal(Map<String, Integer> dist, IGrapheConst graphe, List<String> sommets) {
    //     Double valMin = Double.POSITIVE_INFINITY;
    //     Double val = null;
    //     String noeuMin = "";
    //     for (String noeu : sommets) {
    //         if (dist.get(noeu) == null){
    //             val = Double.POSITIVE_INFINITY;
    //         }
    //         else{
    //             val = Double.valueOf(dist.get(noeu));
    //         }
    //         if (val < valMin){
    //             valMin = val;
    //             noeuMin = noeu;
    //         }
    //         // un peu plus compacte mais peut etre moins comprehensible
    //         // if ((dist.get(noeu) == null ? Double.POSITIVE_INFINITY : Double.valueOf(dist.get(noeu))) < valMin){
    //         //     valMin = val;
    //         //     noeuMin = noeu;
    //         // }
    //     }
    //     // if (noeuMin.equals("")){
    //     //     return sommets.get(0);
    //     // }
    //     return noeuMin;
    // }

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
    // private static String getMinVal(HashMap<String, Double> dist, IGrapheConst graphe, ArrayList<String> sommets){
    //     Double valMin = Double.POSITIVE_INFINITY;
    //     String noeuMin = "";
    //     for (String noeu : sommets) {
    //         if (dist.get(noeu) < valMin){
    //             valMin = dist.get(noeu);
    //             noeuMin = noeu;
    //         }
    //     }
    //     return noeuMin;
    // }
}