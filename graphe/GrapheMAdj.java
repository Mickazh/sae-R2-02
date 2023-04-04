package graphe;
import java.util.*;

public class GrapheMAdj implements IGraphe{
    private int[][] matrice;
    private Map<String,Integer> indices;
    //String est la clé
    //Integer est la valeur
    //représente l'indice du sommet
    public GrapheMAdj(){
        matrice = new int[1][1];
        indices = new HashMap<>();
    }

    @Override
    public List<String> getSommets() {
        List<String> sommets = new ArrayList<>();
        sommets.addAll(indices.keySet());
        return sommets;
    }

    public String getSommetNom(int value){
        for(String s : indices.keySet()){
            if (indices.get(s)==value)
                    return s;

        }
        return null;
    }
    
    @Override
    public List<String> getSucc(String sommet) {
        List<String> Succ = new ArrayList<>();
        int[]valSom = matrice[indices.get(sommet)];
        for(int i = 0; i < valSom.length; ++i){
            if (valSom[i]!=-1)
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
        return matrice[indices.get(src)][indices.get(dest)]!=-1;
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
        }
    }

    @Override
    public void ajouterArc(String source, String destination, Integer valeur) {
        if(matrice[indices.get(source)][indices.get(destination)]!=-1 &&
                indices.containsKey(source) && indices.containsKey(destination) || valeur<0)
            throw new IllegalArgumentException();
        else if(!indices.containsKey(source))
            ajouterSommet(source);
        else if (!indices.containsKey(destination))
            ajouterSommet(destination);
        else
            matrice[indices.get(source)][indices.get(destination)]= valeur;
    }

    @Override
    public void oterSommet(String noeud) {
        if(indices.containsKey(noeud)){
            indices.remove(noeud);
            SetMatrice();
        }
    }

    @Override
    public void oterArc(String source, String destination) {
        if(!indices.containsKey(source) || !indices.containsKey(destination) || matrice[indices.get(source)][indices.get(destination)]==-1)
            throw new IllegalArgumentException("L'arc n'estiste pas");
        else
            matrice[indices.get(source)][indices.get(destination)]= -1;
    }

    public String toString(){
        StringBuilder s = new StringBuilder();
        List<String> sommets = getSommets();
        Collections.sort(sommets);
        for (String som : sommets){
            List<String> succSom = getSucc(som);
            if(succSom.isEmpty()){
                s.append(som);
                s.append(":, ");
            }else{
                Collections.sort(succSom);
                for(String Succe : succSom){
                    s.append(som);
                    s.append("-");
                    s.append(Succe);
                    s.append("(");
                    s.append(matrice[indices.get(som)][indices.get(Succe)]);
                    s.append(")");
                    s.append(", ");
                }
            }

        }
        s.setLength(s.length()-2);
        return s.toString();
    }

}
