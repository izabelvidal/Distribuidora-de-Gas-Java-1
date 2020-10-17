package entidades;

import entidades.Cliente;
import entidades.Produto;

public class Venda {
    protected int quantidade;
    protected Produto produto;
    protected Cliente cliente;
    protected String hora,data,status;

    public Venda(int quantidade, Produto produto, Cliente cliente, String hora, String data, String status){
        this.quantidade = quantidade;
        this.produto = produto;
        this.cliente = cliente;
        this.hora = hora;
        this.data = data;
        this.status = status;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public String getHora() {
        return hora;
    }

    public String getData() {
        return data;
    }

    public String getStatus() {
        return status;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
