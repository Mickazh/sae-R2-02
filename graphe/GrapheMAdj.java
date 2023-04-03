package graphe;

import java.util.*;

public class GrapheMAdj implements IGraphe{
    private int[][] matrice;
    private Map<String,Integer> indices;
    //String est la clé
    //Integer est la valeur
    //représente l'indice du sommet
    public GrapheMAdj(int tailleMatrice){
        matrice = new int[tailleMatrice][tailleMatrice];
        indices=new HashMap<>();
    }

    @Override
    public List<String> getSommets() {
        assert !indices.isEmpty();
        List<String > sommets = new ArrayList<>();
        sommets.addAll(indices.keySet());
        return sommets;
    }

    public String getSommetNom(int value){
        assert indices.containsValue(value);
        for(String s : indices.keySet()){
            if (indices.get(s)==value)
                    return s;

        }
        return null;
    }
    
    @Override
    public List<String> getSucc(String sommet) {
        assert indices.containsKey(sommet);
        List<String> Succ = new ArrayList<>();
        int[]valSom = matrice[indices.get(sommet)];
        for(int i = 0; i < valSom.length; ++i){
            if (valSom[i]!=0)
                Succ.add(getSommetNom(i));
        }
        return Succ;
    }

    @Override
    public int getValuation(String src, String dest) {
        return matrice[indices.get(src)][indices.get(dest)];
    }

    @Override
    public boolean contientSommet(String sommet) {
        return indices.containsKey(sommet);
    }

    @Override
    public boolean contientArc(String src, String dest) {
        return matrice[indices.get(src)][indices.get(dest)]==0;
    }

    public void SetMatrice(){
        int [][]matrice2=new int[indices.size()+1][indices.size()+1];
        for(int i = 0; i < matrice2.length; ++i)
            Arrays.fill(matrice2[i],-1);
        for(int i = 0; i < indices.size(); ++i){
            for(int j = 0; j< indices.size(); ++j){
                matrice2[i][j]=matrice[i][j];
            }
        }
        matrice=matrice2;
    }

    @Override
    public void ajouterSommet(String noeud) {
        if(!indices.containsKey(noeud)){
            SetMatrice();
            indices.put(noeud, indices.size());
        }else{
            System.out.println("Sommet deja existant");
        }

    }

    @Override
    public void ajouterArc(String source, String destination, Integer valeur) {
        if(indices.containsKey(source) && indices.containsKey(destination))
            matrice[indices.get(source)][indices.get(destination)]= valeur;
        else{
            ajouterSommet(source);
            ajouterSommet(destination);
            ajouterArc(source, destination, valeur);
        }

    }

    @Override
    public void oterSommet(String noeud) {
        if(indices.containsKey(noeud)){
            indices.remove(noeud);
            SetMatrice();
        }else
            System.out.println("Le sommet n'exite pas");

    }

    @Override
    public void oterArc(String source, String destination) {
        matrice[indices.get(source)][indices.get(destination)]= -1;
    }

    public String toString(){
        StringBuilder s = new StringBuilder();
        s.append(" ");
        for (String so : indices.keySet()){
            s.append(so);
            s.append(" ");
        }
        s.append(System.lineSeparator());
        for(int i = 0; i < matrice.length; ++i){
            s.append(getSommetNom(i));
            for(int j = 0; j < matrice.length; ++j){
                if(matrice[i][j]!=-1)
                    s.append(matrice[i][j]);
                else
                    s.append(" ");
            }
            s.append(System.lineSeparator());
        }
        return s.toString();
    }

}
