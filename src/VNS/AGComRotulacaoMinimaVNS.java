package VNS;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class AGComRotulacaoMinimaVNS {
    static Grafo grafo = new Grafo();
    
    public static void main(String[] args) throws IOException {
        ArrayList<Aresta> arvoreGeradora = new ArrayList();
        ArrayList<Cor> cores = new ArrayList();
        ArrayList<Cor> melhorSolucao = new ArrayList();  
        InOut inout = new InOut("C:\\Users\\Sidemar_user\\Documents\\NetBeansProjects\\AGRM_Meta-heuristica\\src\\Instancias\\100_125.txt");
        long tempo = System.currentTimeMillis();
        
        grafo.setArestas(inout.arestas);
        grafo.setNumVertices(inout.N);
        cores = grafo.getCoresUsadas(grafo.getArestas());   
        arvoreGeradora = grafo.gerarArvore(cores);
        melhorSolucao = grafo.getCoresUsadas(arvoreGeradora);
        
        if(arvoreGeradora != null) {
//            for (Aresta a : arvoreGeradora) {
//                System.out.println("v"+a.getVerticeOrigem().getNome() + "--" +a.getValor()
//                        + "--v" + a.getVerticeDestino().getNome() + "\t");
//            }
//            System.out.println();
            //System.out.println("Quantidade de cores = " + melhorSolucao.size());
        } else {
            System.out.println("Nao gera uma arvore");
            return;
        }
            
        ArrayList<Cor> novaSolucao = new ArrayList();
        for (Cor s : melhorSolucao) {
            novaSolucao.add(0, s);
        }
        
        // Iniciando a meta-heuristica deste ponto
        for (int k = 0; k < melhorSolucao.size(); ) {
            novaSolucao = mudarVizinhanca(novaSolucao, cores, k);
            novaSolucao = buscaLocal(novaSolucao);

            if(novaSolucao.size() < melhorSolucao.size()) {
                melhorSolucao.clear();
                for (Cor s : novaSolucao) {
                    melhorSolucao.add(0, s);
                }
                k = 0;
            } else {
                k++;
            }
        }
        tempo = System.currentTimeMillis() - tempo;
        System.out.println("\nTempo Total: " + tempo + " milissegundos");
        System.out.println("Quantidade de cores finais = " + melhorSolucao.size());
    }
    
    public static ArrayList<Cor> mudarVizinhanca(ArrayList<Cor> coresUsadas, ArrayList<Cor> coresTotal, int k) {
        Random aleatorio = new Random();
        int numero;
        int valor;
        boolean continuar;
        Cor cor;
        
        for (int i = 0; i <= k; i++) {
            numero = aleatorio.nextInt(3);
            if(numero == 0) {
                // troca uma cor
                valor = aleatorio.nextInt(coresUsadas.size());
                cor = coresUsadas.get(valor);
                if(coresUsadas.size() > 0) {
                    coresUsadas.remove(valor);
                }
                
                while(true) {
                    continuar = false;
                    valor = aleatorio.nextInt(coresTotal.size());
                    for(Cor c : coresUsadas) {
                        if((coresTotal.get(valor).getCor() == c.getCor()) || 
                                (coresTotal.get(valor).getCor() == cor.getCor()) ) {
                            continuar = true;
                            break;
                        }

                    }

                    if(!continuar) {
                        coresUsadas.add(0, coresTotal.get(valor));
                        break;
                    }
                }
                
                
            } else if(numero == 1) {
                // adiciona cor
                if(coresTotal.size() != coresUsadas.size()) {
                    while(true) {
                        continuar = false;
                        valor = aleatorio.nextInt(coresTotal.size());
                        for(Cor c : coresUsadas) {
                            if(coresTotal.get(valor).getCor() == c.getCor()) {
                                continuar = true;
                                break;
                            }

                        }

                        if(!continuar) {
                            coresUsadas.add(0, coresTotal.get(valor));
                            break;
                        }
                    }
                }
                
            } else {
                // deleta uma cor
                valor = aleatorio.nextInt(coresUsadas.size());
                if(coresUsadas.size() > 0) {
                    coresUsadas.remove(valor);
                }
            }
        }
        
        if(coresTotal.size() != coresUsadas.size()) {
            // adiciona aresta até ser possível gerar uma árvore
            while(true) {
                
                ArrayList<Aresta> arvoreGeradora;
                arvoreGeradora = grafo.gerarArvore(coresUsadas);
                if(arvoreGeradora != null) {
                    break;
                }
                
                while(true) {
                    continuar = false;
                    valor = aleatorio.nextInt(coresTotal.size());
                    for(Cor c : coresUsadas) {
                        if(coresTotal.get(valor).getCor() == c.getCor()) {
                            continuar = true;
                            break;
                        }

                    }

                    if(!continuar) {
                        coresUsadas.add(0, coresTotal.get(valor));
                        break;
                    }
                }
            }
        }
        
        return coresUsadas;
    }
    
    public static ArrayList<Cor> buscaLocal(ArrayList<Cor> cores) {
        ArrayList<Aresta> arvoreGeradora;
        Cor cor;
        
        for (int i = 0; i < cores.size(); i++) {
            cor = cores.get(i);
            cor = cores.remove(i);
            arvoreGeradora = grafo.gerarArvore(cores);
            if(arvoreGeradora == null) {
                cores.add(0, cor);
            } else {
                i--;
            }
        }
        
        return cores;
    }
}
