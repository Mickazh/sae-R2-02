package tests;

import graphe.GrapheLArcs;
import graphe.GrapheMAdj;
import org.junit.Test;

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
    }


