package VNS;

public class Cor implements Comparable<Cor>{
    private int cor;
    private int quantidade;

    /**
     * @return the cor
     */
    public int getCor() {
        return cor;
    }

    /**
     * @param cor the cor to set
     */
    public void setCor(int cor) {
        this.cor = cor;
    }

    /**
     * @return the quantidade
     */
    public int getQuantidade() {
        return quantidade;
    }

    /**
     * @param quantidade the quantidade to set
     */
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    
    @Override
    public int compareTo(Cor cor) {
        if(this.quantidade <= cor.getQuantidade())
            return 1;
        else
            return -1;   
    }
}
