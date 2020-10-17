package entidades;

public class Produto {
    protected String nome,marca,id;
    protected int quantidade;
    protected double peso,preco;

    public Produto(String nome, String marca, String id, int quantidade, double peso, double preco){
        this.nome = nome;
        this.marca = marca;
        this.id = id;
        this.quantidade = quantidade;
        this.peso = peso;
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public String getMarca() {
        return marca;
    }

    public String getId() {
        return id;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getPeso() {
        return peso;
    }

    public double getPreco() {
        return preco;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
}
