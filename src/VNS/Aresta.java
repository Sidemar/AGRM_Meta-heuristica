package VNS;

public class Aresta implements Comparable<Aresta>{
    private Vertice verticeOrigem = null;
    private Vertice verticeDestino = null;
    private int valor = 0;

    public Aresta(Vertice verticeOrigem, Vertice verticeDestino, int valor) {
        this.verticeOrigem = verticeOrigem;
        this.verticeDestino = verticeDestino;
        this.valor = valor;
    }

    public Vertice getVerticeOrigem() {
        return verticeOrigem;
    }

    public void setVerticeOrigem(Vertice verticeOrigem) {
        this.verticeOrigem = verticeOrigem;
    }

    public Vertice getVerticeDestino() {
        return verticeDestino;
    }

    public void setVerticeDestino(Vertice verticeDestino) {
        this.verticeDestino = verticeDestino;
    }
    
    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    @Override
    public int compareTo(Aresta o) {
        if(this.valor < o.valor)
            return -1;
        else if(this.valor > o.valor)
            return 1;
        else
            return 0;
    }
}
