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
        Arvore arvore = new Arvore();
        
        InOut inout = new InOut("/home/sidemar/NetBeansProjects/AGRM/src/Instancias/500_124750_500.txt");
        long tempo = System.currentTimeMillis();
        
        arestas = inout.arestas;
        
        cores = arvore.getCoresUsadas(arestas);
        
        // Como estou usando sempre as cores que mais estão presente no grafo
        // como critério de criação da árvore geradora então estou 
        // usando o MergeSort para ordenar essas cores
        Collections.sort(cores);  
        
        for (Cor c : cores) {
            for (Aresta a : arestas) {
                if(a.getValor() == c.getCor()) {
                    arestasFinais.add(0, a);
                }
            }
        }
        
        // Complexidade theta(E), onde E é o número de arestas 
        Collections.reverse(arestasFinais);
        
        arestasFinais = arvore.gerarArvore(arestasFinais, inout.N);
        
        tempo = System.currentTimeMillis() - tempo;
        
        if(arestasFinais != null) {
            for (Aresta a : arestasFinais) {
                System.out.println("v"+a.getVerticeOrigem().getNome() + "--" +a.getValor()
                        + "--v" + a.getVerticeDestino().getNome() + "\t");
            }
            System.out.println();
            System.out.println("Quantidade de cores = " + arvore.getCoresUsadas(arestasFinais).size());
        } else
            System.out.println("Nao gera uma arvore");
        
        System.out.println("\nTempo Total: " + tempo + " milissegundos");
    }
}
