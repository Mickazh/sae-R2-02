package main;
import java.util.ArrayList;
import java.util.HashMap;

import graphe.IGrapheConst;

public class CheminCourt {

    public static void Dijkstra(String source, String destination, IGrapheConst graphe){
        ArrayList<String> sommets = new ArrayList<>(graphe.getSommets());
        HashMap<String, Double> dist = new HashMap<>();
        HashMap<String, String> prev = new HashMap<>();
        double alt = 0;
        for (String sommet : sommets) {
            dist.put(sommet, Double.POSITIVE_INFINITY);
            prev.put(sommet, null);
        }
        dist.put(source, 0d);
        for (String succ : graphe.getSucc(source)) {
            dist.put(succ, (double)graphe.getValuation(source, succ));
            prev.put(succ, source);
        }
        String u = source;
        while (!sommets.isEmpty()){
            u = getMinVal(dist, u, graphe, sommets);
            sommets.remove(u);
            

            for (String v : graphe.getSucc(u)) {
                alt = dist.get(u) + (double)graphe.getValuation(u, v);
                if (alt < dist.get(v)){
                    dist.put(v, alt);
                    prev.put(v, u);
                }
            }
        }
        boolean first = true;
        String noeud = destination;
        do {
            if (!first){
                System.out.print("- ");
            }
            System.out.print(noeud + " ");
            first = false;
            noeud = prev.get(noeud);
        } while (noeud != null);
        System.out.println("longueur : " + dist.get(destination).intValue());
    }

    // private String getMinVal(String u, IGrapheConst graphe){
    //     Double valMin = Double.POSITIVE_INFINITY;
    //     String noeudMin = "";
    //     for (String noeud : graphe.getSucc(u)) {
    //         if (graphe.getValuation(u, noeud) < valMin){
    //             valMin = (double)graphe.getValuation(u, noeud);
    //             noeudMin = noeud;
    //         }
    //     }
    //     return noeuMin;
    // }
    private static String getMinVal(HashMap<String, Double> dist, String u, IGrapheConst graphe, ArrayList<String> sommets){
        Double valMin = Double.POSITIVE_INFINITY;
        String noeudMin = "";
        for (String noeud : sommets) {
            if (dist.get(noeud) < valMin){
                valMin = dist.get(noeud);
                noeudMin = noeud;
            }
        }
        return noeudMin;
    }
}
