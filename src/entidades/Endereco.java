package entidades;

public class Endereco {
    protected String rua;
    protected int numero;
    protected String bairro;
    protected String cidade;
    protected String estado;

    public Endereco(String rua, int numero, String bairro, String cidade, String estado){
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
    }

    //getters
    public String getRua(){
        return rua;
    }

    public int getNumero() {
        return numero;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEstado() {
        return estado;
    }

    //setters

    public void setRua(String rua) {
        this.rua = rua;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Rua: " + this.rua + "\n Número: " + this.numero + "\n Bairro: " + this.bairro +
                "\n Cidade: " + this.cidade + "\n Estado: " + this.estado;
    }
}
