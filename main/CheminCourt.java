package main;
import java.util.ArrayList;
import java.util.HashMap;

import graphe.IGrapheConst;

public class CheminCourt {

    static public void Dijkstra(String source, String destination, IGrapheConst graphe){
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
    static private String getMinVal(HashMap<String, Double> dist, String u, IGrapheConst graphe, ArrayList<String> sommets){
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
