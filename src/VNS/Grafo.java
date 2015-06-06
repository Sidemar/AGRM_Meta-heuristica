package VNS;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author sidemar
 */
public class Grafo {
    
    private ArrayList<Aresta> arestas;
    private int numVertices;

    public Grafo() {
    }
    
    public Grafo(ArrayList<Aresta> arestas, int numVertices) {
        this.arestas = arestas;
        this.numVertices = numVertices;
    }
    
    public ArrayList<Aresta> gerarArvore(ArrayList<Cor> cores) {
        ArrayList<Aresta> arvoreGeradora = new ArrayList<>();
        ArrayList<Aresta> arestasCoresUsadas = new ArrayList();
        
        Collections.sort(cores);
        
        for (Cor c : cores) {
            for (Aresta a : this.arestas) {
                if(a.getValor() == c.getCor()) {
                    arestasCoresUsadas.add(0, a);
                }
            }
        }
        
        Collections.reverse(arestasCoresUsadas);
        
        for (Aresta a : arestasCoresUsadas) {
            a.getVerticeOrigem().setUnion(UnionFind.makeSet(a.getVerticeOrigem()));
            a.getVerticeDestino().setUnion(UnionFind.makeSet(a.getVerticeDestino()));
        }
        
        for(Aresta a : arestasCoresUsadas) {
            if(!UnionFind.areUnited(a.getVerticeOrigem().getUnion(), a.getVerticeDestino().getUnion())){
                a.getVerticeOrigem().getUnion().union(a.getVerticeDestino().getUnion());
                arvoreGeradora.add(0, a);
            }
        }
        
        if((getNumVertices() - 1) == arvoreGeradora.size()) // verifica se é uma árvore geradora
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

    /**
     * @return the arestas
     */
    public ArrayList<Aresta> getArestas() {
        return arestas;
    }

    /**
     * @param arestas the arestas to set
     */
    public void setArestas(ArrayList<Aresta> arestas) {
        this.arestas = arestas;
    }

    /**
     * @return the numVertices
     */
    public int getNumVertices() {
        return numVertices;
    }

    /**
     * @param numVertices the numVertices to set
     */
    public void setNumVertices(int numVertices) {
        this.numVertices = numVertices;
    }
    
    
}
