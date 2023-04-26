package main;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import graphe.Arc;
import graphe.GraphImporter;
import graphe.GrapheHHAdj;
import graphe.GrapheLArcs;
import graphe.GrapheMAdj;
import graphe.IGraphe;

public class TestDijskstra {
    private final String graphesRep = "graphes/";
	private final String reponsesRep = "reponses/";

	private final static IGraphe[] graphes = {
			new GrapheLArcs(), new GrapheLArcs(),
			new GrapheMAdj(), new GrapheHHAdj()
	};

	// graphe de l'exercice 3.1 du poly de maths
	// avec en plus un noeud isole : J
	private final String g31 =
			"A-C(2), A-D(1), "
					+ "B-G(3), "
					+ "C-H(2), "
					+ "D-B(3), D-C(5), D-E(3), "
					+ "E-C(1), E-G(3), E-H(7), "
					+ "F:, "
					+ "G-B(2), G-F(1), "
					+ "H-F(4), H-G(2), "
					+ "I-H(10), "
					+ "J:";

	private final String g31a = ""       // arcs non tries
			+ "D-C(5), D-E(3), D-B(3), "
			+ "E-G(3), E-C(1), E-H(7), "
			+ "I-H(10), "
			+ "J:,"
			+ "G-B(2), G-F(1), "
			+ "F:, "
			+ "H-G(2), H-F(4), "
			+ "A-C(2), A-D(1), "
			+ "B-G(3), "
			+ "C-H(2) ";

    // void exo3_1Maths() {
    //     for (IGraphe g : graphes) {
    //         g.peupler(g31a);
    //         tester3_1(g);
    //     }
    // }

    static void petiteImporation(IGraphe g) {
		Arc a = GraphImporter.importer("graphes/g-10-1.txt", g);
		assertEquals("1-3(5), "
						+ "10-3(3), 2-1(5), 2-3(5), 2-5(4), "
						+ "3-4(4), 3-5(4), 4-10(1), 4-2(1), 4-7(3), "
						+ "5-9(4), 6-2(3), 6-3(4), 7-3(2),"
						+ " 8-2(4), 8-6(1), 9-2(4)",
				g.toString());
		assertEquals("5", a.getSource());
		assertEquals("7", a.getDestination());
	}

    static void petitTestImportation() {
		for (IGraphe g : graphes)
			petiteImporation(g);
	}
    static void importer() throws NumberFormatException, FileNotFoundException {
		String graphesRep = "graphes";
		String reponsesRep = "reponses";
		try {
			List<Path> files1 = Files.list(Paths.get(graphesRep))
					.filter(Files::isRegularFile)
					.collect(Collectors.toList());
			List<Path> files2 = Files.list(Paths.get(reponsesRep))
					.filter(Files::isRegularFile)
					.collect(Collectors.toList());
            System.out.println(files1);
            System.out.println(files2);
			Iterator<Path> iterator1 = files1.iterator();
			Iterator<Path> iterator2 = files2.iterator();

			while (iterator1.hasNext() && iterator2.hasNext()) {
				Path file1 = iterator1.next();
				Path file2 = iterator2.next();

				IGraphe g = new GrapheHHAdj();
				Arc arc = GraphImporter.importer(String.valueOf(file1.toFile()), g);

				List<Integer> listeEntiers = new ArrayList<>();
				int distance_attendue = GraphImporter.importerReponse(file2.toString(), listeEntiers);

				System.out.println("\ngraphe: " + file1.getFileName());
				System.out.println("chemin a trouver " + arc.getSource()+ " "+ arc.getDestination());
				System.out.println("Graphe de  " + g.getSommets().size() + " sommets");

				System.out.println("reponse: " + file2.getFileName());
				if (distance_attendue >=0) {
					System.out.println("distance attendue : " + distance_attendue);
					System.out.println("chemin possible : " + listeEntiers);
				} else System.out.println("Aucun chemin attendu");

				Map<String, Integer> dist = new HashMap<>();
				Map<String, String> prev = new HashMap<>();
				long debut = System.nanoTime();
				Dijkstra.dijkstra(g, arc.getSource(), dist, prev);
				long fin = System.nanoTime();
				System.out.println("dijkstra a dure " + (fin - debut)/1000000 + " millisecondes");

				System.out.println(g.toAString());

				// reconstruction d'un plus court chemin
				List<String> path = new LinkedList<>();
				String currentNode = arc.getDestination();
				while (currentNode != null && !currentNode.equals(arc.getSource())) {
					path.add(0, currentNode);
					currentNode = prev.get(currentNode);
				}

				// ajouter la source
				if (currentNode != null && currentNode.equals(arc.getSource())) {
					path.add(0, arc.getSource());
				}

				if (dist.containsKey(arc.getDestination())) {
					int distance_trouvee = dist.get(arc.getDestination());
					System.out.println("chemin trouve : <" + String.join(", ", path)+">");
					System.out.println("Distance trouvee : " + distance_trouvee);
					assertEquals(distance_trouvee, distance_attendue);
				} else {
					System.out.println("Aucun chemin trouve");
					assertEquals(-1, distance_attendue);
				}
			}
		} catch (IOException e) {
			System.out.println("Erreur lors de l'acces aux dossiers: " + e.getMessage());
		}
	}

    public static void main(String[] args) {
        GrapheHHAdj g = new GrapheHHAdj();
        // String population2 = "A-B(6), A-G(4), A-K(3), B-C(5), B-D(2), C-D(1), D-E(4), D-F(5), E-C(3), E-J(1), F-I(1), G-F(2), G-H(3), H-F(1), I-J(3), I-H(1), J:, K-B(2), K-G(1), L:";
        // g.peupler(population2);
        HashMap<String, Integer> dist = new HashMap<>();
        HashMap<String, String> pred = new HashMap<>();
        // Dijkstra.dijkstra(g, "A", dist, pred);
        // System.out.println(dist);
        // System.out.println(pred);
        // Arc a = GraphImporter.importer("graphes/g-10-1.txt", g);
        // System.out.println(g.toString());
        petitTestImportation();
        try {
            importer();
        } catch (NumberFormatException e) {
            System.out.println("not ok : "+e);
        } catch (FileNotFoundException e) {
            System.out.println("not ok"+e);
        }
        System.out.println("ok");
		String pop = "1-3(4), 1-5(2), 1-6(3), 1-8(5), 10-1(1), 2-1(3), 2-3(4), 2-4(3), 2-5(4), 2-7(3), 3-8(2), 4-1(2), 4-10(5), 5:, 6-2(2), 7-3(2), 8-9(2), 9-3(5)";
		g.peupler(pop);
		Dijkstra.dijkstra(g, "1", dist, pred);
		System.out.println(dist);
		System.out.println(pred);
    }
}
