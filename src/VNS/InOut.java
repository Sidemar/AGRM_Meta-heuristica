package VNS;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public final class InOut {

    String linha = " ";
    FileReader reader;
    BufferedReader leitor;
    
    int N, D;
    ArrayList<Aresta> arestas;
    
    String elemento = "";

    public InOut(String path) throws FileNotFoundException, IOException {
        File arquivo = new File(path);
        reader = new FileReader(arquivo);
        leitor = new BufferedReader(reader);
        arestas = new ArrayList<>();
        getConfig();
        getArestas();
    }
    
    private void getConfig() throws IOException {
        linha += leitor.readLine() + " ";
        
        ArrayList<String> elementos = new ArrayList<>();

        for (int i = 0; i < linha.length() - 1; i++) {
            if (linha.charAt(i) == ' ') {
                for (int j = i + 1; j < linha.length() - 1; j++) {
                    if (linha.charAt(j) == ' ') {
                        break;
                    } else {
                        elemento += linha.charAt(j);
                    }
                }
                elementos.add(elemento);
                elemento = "";
            }
        }

        N = Integer.parseInt(elementos.get(0));
        D = Integer.parseInt(elementos.get(1));
    }
    
    private void getArestas() throws IOException {
        ArrayList<Vertice> vertices = new ArrayList<>();
        
        for (int i = 0; i < N; i++) {
            Vertice c = new Vertice(""+i);
            vertices.add(c);
        }

        elemento = "";

        int ID = 0;
        int contador = 1;
        
        while (leitor.ready()) {

            linha = " " + leitor.readLine();

            contador = ID + 1;

            for (int i = 0; i < linha.length() - 1; i++) {

                if (linha.charAt(i) == ' ') {
                    for (int j = i + 1; j < linha.length() - 1; j++) {
                        if (linha.charAt(j) == ' ') {
                            break;
                        } else {
                            elemento += linha.charAt(j);
                        }
                    }
                    
                    if(Integer.parseInt(elemento) != 0)
                        arestas.add(new Aresta(vertices.get(ID), vertices.get(contador), Integer.parseInt(elemento)));
                    contador++;
                    elemento = "";
                }
            }

            ID++;
        }
    }
}
