package VNS;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author sidemar
 */
public class Arvore {
    
    public ArrayList<Aresta> gerarArvore(ArrayList<Aresta> arestas, int numVertices) {
        ArrayList<Aresta> arvoreGeradora = new ArrayList<>();
        
        for (Aresta a : arestas) {
            a.getVerticeOrigem().setUnion(UnionFind.makeSet(a.getVerticeOrigem()));
            a.getVerticeDestino().setUnion(UnionFind.makeSet(a.getVerticeDestino()));
        }
        
        for(Aresta a : arestas) {
            if(!UnionFind.areUnited(a.getVerticeOrigem().getUnion(), a.getVerticeDestino().getUnion())){
                a.getVerticeOrigem().getUnion().union(a.getVerticeDestino().getUnion());
                arvoreGeradora.add(0, a);
            }
        }
        
        if((numVertices - 1) == arvoreGeradora.size()) // verifica se é uma árvore geradora
            return arvoreGeradora;
        else
            return null;
        
    }
    
    public ArrayList<Cor> getCoresUsadas(ArrayList<Aresta> arestas) {
        ArrayList<Cor> cores = new ArrayList();
        
        boolean contem;
        for (Aresta a : arestas) {
            contem = false;
            for (Cor c : cores) {
                if(a.getValor() == c.getCor()) {
                    c.setQuantidade(c.getQuantidade() + 1);
                    contem = true;
                    break;
                }
                
            }
            
            if(!contem) {
                Cor c = new Cor();
                c.setCor(a.getValor());
                c.setQuantidade(1);
                cores.add(c);
            }
            
        }
        
        return cores;
    }
}
