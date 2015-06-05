package VNS;

public class Vertice {
    private String nome;
    private UnionFind<Vertice> union;

    public UnionFind<Vertice> getUnion() {
        return union;
    }

    public void setUnion(UnionFind<Vertice> union) {
        this.union = union;
    }

    public Vertice(String nome) {
        this.nome = nome;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
