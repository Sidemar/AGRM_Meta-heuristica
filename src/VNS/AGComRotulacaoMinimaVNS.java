package VNS;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class AGComRotulacaoMinimaVNS {
    static Grafo grafo = new Grafo();
    
    public static ArrayList<Cor> agitacao(ArrayList<Cor> cores, int k) {
        ArrayList<Cor> novasCores = new ArrayList<>();
        Random aleatorio = new Random();
        int numero = aleatorio.nextInt(3);
        
        for (int i = 0; i < k; i++) {
            if(numero == 0) {
            // troca entre cores
            } else if(numero == 1) {
                // adiciona uma cor
            } else {
                // deleta uma cor
            }
        }
        
        // adiciona aresta até ser possível gerar uma árvore
//        while(false) {
//            
//        }
        
        return null;
    }
    
    public static ArrayList<Cor> buscaLocal(ArrayList<Cor> cores) {
        ArrayList<Cor> novasCores = new ArrayList<>();
        Cor cor = new Cor();
        
        for (int i = 0; i < 10; i++) {
            cor = cores.get(i);
            cor = cores.remove(i);
            // vejo se é possível criar uma árvore geradora
            // se não devolvo a cor
        }
        
        return null;
    }
    
    public static void main(String[] args) throws IOException {
        ArrayList<Aresta> arvoreGeradora = new ArrayList();
        ArrayList<Cor> cores = new ArrayList();
        ArrayList<Cor> coresUsadas = new ArrayList();
        
        InOut inout = new InOut("/home/sidemar/NetBeansProjects/AGRM/src/Instancias/7_12_4.txt");
        long tempo = System.currentTimeMillis();
        
        grafo.setArestas(inout.arestas);
        grafo.setNumVertices(inout.N);
        cores = grafo.getCoresUsadas(grafo.getArestas());
        arvoreGeradora = grafo.gerarArvore(cores);
        coresUsadas = grafo.getCoresUsadas(arvoreGeradora);
        
        tempo = System.currentTimeMillis() - tempo;
        
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
        
        // Iniciando a meta-heuristica
        for (int k = 0; k < coresUsadas.size(); k++) {
            
        }
        
    }
}
