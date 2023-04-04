package tests;

import graphe.GrapheHHAdj;
import graphe.GrapheLArcs;
import main.CheminCourt;
import org.junit.Test;
import org.junit.platform.engine.support.descriptor.FileSystemSource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestGrapheLArc {
    @Test
    public void Test(){
            GrapheLArcs graphe = new GrapheLArcs();
            String population = "A-B(8), A-C(6), A-D(2), B-E(4), C-B(3), C-E(4), D-B(5), E:";
            graphe.peupler(population);
            assertTrue(graphe.contientSommet("A"));
            assertTrue(graphe.contientSommet("B"));
            assertTrue(graphe.contientArc("A","B"));
            graphe.oterArc("B","E");
            graphe.ajouterSommet("F");
            assertTrue(graphe.contientSommet("F"));
            graphe.ajouterArc("F","C",6);
            assertTrue(graphe.contientArc("F","C"));
            assertEquals(8,graphe.getValuation("A","B"));
            graphe.oterSommet("A");

        }
    public static void main(String[] args) {
        System.out.println("test de GrapheLArcs");
        GrapheLArcs graphe = new GrapheLArcs();
        String population = "A-B(8), A-C(6), A-D(2), B-E(4), C-B(3), C-E(4), D-B(5), E:";
        graphe.peupler(population);
        System.out.println("Test exemple cours graphe");
        CheminCourt.Dijkstra("A", "A", graphe);
        CheminCourt.Dijkstra("A", "B", graphe);
        CheminCourt.Dijkstra("A", "C", graphe);
        CheminCourt.Dijkstra("A", "D", graphe);
        CheminCourt.Dijkstra("A", "E", graphe);

        System.out.println("Test plus grand exemple cours");
        GrapheLArcs graphe2 = new GrapheLArcs();
        String population2 = "A-B(6), A-G(4), A-K(3), B-C(5), B-D(2), C-D(1), D-E(4), D-F(5), E-C(3), E-J(1), F-I(1), G-F(2), G-H(3), H-F(1), I-J(3), I-H(1), J:, K-B(2), K-G(1)";
        graphe2.peupler(population2);
        for (String dest : graphe2.getSommets()) {
            CheminCourt.Dijkstra("A", dest, graphe2);
        }
    }
    }




