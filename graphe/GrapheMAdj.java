package graphe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GrapheMAdj implements IGraphe{
    private int[][] matrice;
    private Map<String,Integer> indices;
    //String est la clé
    //Integer est la valeur
    //représente l'indice du sommet
    public GrapheMAdj(int tailleMatrice){
        matrice=new int[tailleMatrice][tailleMatrice];
        indices=new HashMap<>();
    }

    @Override
    public List<String> getSommets() {
        List<String > sommets = new ArrayList<>();
        for(int i = 0; i < indices.size(); ++i){;
        }
        throw new UnsupportedOperationException("Unimplemented method 'getSommets'");
    }

    @Override
    public List<String> getSucc(String sommet) {
        List<String> Succ = new ArrayList<>();

        throw new UnsupportedOperationException("Unimplemented method 'getSucc'");
    }

    @Override
    public int getValuation(String src, String dest) {
        return matrice[indices.get(src)][indices.get(dest)];
        throw new UnsupportedOperationException("Unimplemented method 'getValuation'");
    }

    @Override
    public boolean contientSommet(String sommet) {
        return indices.containsKey(sommet);
        throw new UnsupportedOperationException("Unimplemented method 'contientSommet'");
    }

    @Override
    public boolean contientArc(String src, String dest) {
        return matrice[indices.get(src)][indices.get(dest)]!=0;
        throw new UnsupportedOperationException("Unimplemented method 'contientArc'");
    }

    @Override
    public void ajouterSommet(String noeud) {
        if(!indices.containsKey(noeud)){
            indices.put(noeud, indices.size());
            for(int i = 0)
        }else{
            System.out.println("Sommet deja existant");
        }

        throw new UnsupportedOperationException("Unimplemented method 'ajouterSommet'");
    }

    @Override
    public void ajouterArc(String source, String destination, Integer valeur) {
            assert indices.containsKey(source) && indices.containsKey(destination);
            matrice[indices.get(source)][indices.get(destination)]= valeur;
        throw new UnsupportedOperationException("Unimplemented method 'ajouterArc'");
    }

    @Override
    public void oterSommet(String noeud) {
        assert indices.containsKey(noeud);
        indices.remove(noeud);
        throw new UnsupportedOperationException("Unimplemented method 'oterSommet'");
    }

    @Override
    public void oterArc(String source, String destination) {
        matrice[indices.get(source)][indices.get(destination)]=0;
        throw new UnsupportedOperationException("Unimplemented method 'oterArc'");
    }
    
}
