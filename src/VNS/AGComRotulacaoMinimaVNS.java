package VNS;

import java.io.IOException;
import java.util.ArrayList;

public class AGComRotulacaoMinimaVNS {
    public static void main(String[] args) throws IOException {
        ArrayList<Aresta> arvoreGeradora = new ArrayList();
        ArrayList<Cor> cores = new ArrayList();
        ArrayList<Cor> coresUsadas = new ArrayList();
        
        InOut inout = new InOut("/home/sidemar/NetBeansProjects/AGRM/src/Instancias/500_124750_500.txt");
        long tempo = System.currentTimeMillis();
        
        Grafo grafo = new Grafo(inout.arestas, inout.N);
        cores = grafo.getCoresUsadas(grafo.getArestas());
        arvoreGeradora = grafo.gerarArvore(cores);
        
        tempo = System.currentTimeMillis() - tempo;
        
        coresUsadas = grafo.getCoresUsadas(arvoreGeradora);
        
        if(arvoreGeradora != null) {
            for (Aresta a : arvoreGeradora) {
                System.out.println("v"+a.getVerticeOrigem().getNome() + "--" +a.getValor()
                        + "--v" + a.getVerticeDestino().getNome() + "\t");
            }
            System.out.println();
            System.out.println("Quantidade de cores = " + coresUsadas.size());
        } else
            System.out.println("Nao gera uma arvore");
        
        System.out.println("\nTempo Total: " + tempo + " milissegundos");
        
    }
}
