package VNS;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class AGComRotulacaoMinimaVNS {
    
    public static void main(String[] args) throws IOException {
        ArrayList<Aresta> arestas = new ArrayList();
        ArrayList<Aresta> arestasFinais = new ArrayList();
        ArrayList<Cor> cores = new ArrayList();
        ArrayList<Aresta> arvoreGeradora = new ArrayList<>();
        
        InOut inout = new InOut("/home/sidemar/NetBeansProjects/AGRM/src/Instancias/500_124750_500.txt");
        long tempoInicio = System.currentTimeMillis();
        
        arestas = inout.arestas;
        
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
        
        // Como estou usando sempre as cores que mais estão presente no grafo
        // como critério de criação da árvore geradora então estou 
        // usando o MergeSort para ordenar essas cores
        Collections.sort(cores);  
        
//        for (Cor c : cores) {
//            System.out.println("Cor: " + c.getCor() + " Quant: " + c.getQuantidade());
//        }
        
        for (Cor c : cores) {
            for (Aresta a : arestas) {
                if(a.getValor() == c.getCor()) {
                    arestasFinais.add(0, a);
                }
            }
        }
        
        // Complexidade theta(E), onde E é o número de arestas 
        Collections.reverse(arestasFinais);
        
        for (Aresta a : arestasFinais) {
            a.getVerticeOrigem().setUnion(UnionFind.makeSet(a.getVerticeOrigem()));
            a.getVerticeDestino().setUnion(UnionFind.makeSet(a.getVerticeDestino()));
        }
        
        for(Aresta a : arestasFinais) {
            if(!UnionFind.areUnited(a.getVerticeOrigem().getUnion(), a.getVerticeDestino().getUnion())){
                a.getVerticeOrigem().getUnion().union(a.getVerticeDestino().getUnion());
                arvoreGeradora.add(0, a);
            }
        }
        
        // conjunto para contar as cores da árvore geradora
        HashSet<Integer> auxiliar = new HashSet<>(); 
        for (Aresta a : arvoreGeradora) {
            auxiliar.add(a.getValor());
        } 
        
        if((inout.N - 1) == arvoreGeradora.size()) { // verifica se é uma árvore geradora
            for (Aresta a : arvoreGeradora) {
                System.out.println("v"+a.getVerticeOrigem().getNome() + "--" +a.getValor()
                        + "--v" + a.getVerticeDestino().getNome() + "\t");
            }
            System.out.println();
            System.out.println("Quantidade de cores = " + auxiliar.size());
        } else
            System.out.println("Nao gera uma arvore");
        
        System.out.println("\nTempo Total: "+(System.currentTimeMillis()-tempoInicio)+" milissegundos");
    }
    
}
