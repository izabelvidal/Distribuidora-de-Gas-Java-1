package DistribuidoraDeGas.negocio.entidades;

import DistribuidoraDeGas.negocio.entidades.Cliente;
import DistribuidoraDeGas.negocio.entidades.Produto;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Essa classe representa a venda dos produtos.
 *
 * @author Letícia Araújo
 */

public class Venda {
    private int quantidade;
    private Produto produto;
    private Cliente cliente;
    private String hora,data,status;

    public Venda(int quantidade, Produto produto, Cliente cliente){
        this.quantidade = quantidade;
        this.produto = produto;
        this.cliente = cliente;
        this.status = "Não concluída";

        Calendar c = Calendar.getInstance();
        c.add(Calendar.HOUR,-1);
        Date data = c.getTime();
        SimpleDateFormat dFormatada = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat hFormatada = new SimpleDateFormat("HH:mm");
        // Hora e Data atual do sistema.

        this.hora = hFormatada.format(data);
        this.data = dFormatada.format(data);
    }

    //GETTERS

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

    //SETTERS

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

    public void concluirStatus(){
        this.status = "Concluída";
    }
    //


    @Override
    public String toString() {
        return "Produto: " + this.produto + "\nCliente: " + this.cliente +
                "\nQuantidade: " + this.quantidade + "\nData: " + this.data +
                "\nHora: " + this.hora + "\nStatus: " + this.status;
    }
}
