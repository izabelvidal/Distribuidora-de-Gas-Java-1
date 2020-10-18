package negocio.gerenciadores;

import dados.RepositorioCliente;
import dados.contratos.*;
import negocio.entidades.Cliente;
import negocio.entidades.Produto;
import negocio.entidades.Venda;
import negocio.excecoes.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class NegocioProduto {
    private iRepositorioEstoqueProdutos repositorioProdutos;
    private iRepositorioProdutosVendidos repositorioProdutosVendidos;
    private RepositorioCliente repositorioPessoa;

    public NegocioProduto(iRepositorioProdutosVendidos repositorioProdVendidos, iRepositorioEstoqueProdutos repositorioProd, RepositorioCliente repositorioPessoa){
        this.repositorioProdutos = repositorioProd;
        this.repositorioProdutosVendidos = repositorioProdVendidos;
        this.repositorioPessoa = repositorioPessoa;
    }

    public void adicionarProduto(Produto produto) throws ProdutoJaCadastradoException{
        boolean existe = this.repositorioProdutos.verificarProduto(produto.getId());

        if(existe){
            throw new ProdutoJaCadastradoException(produto.getId());
        }else{
            this.repositorioProdutos.adicionarProduto(produto);
        }
    }

    public void removerProduto(String id) throws ProdutoInexistenteException{
        this.repositorioProdutos.removerProduto(id);
    }

    public void decrementarQntd(String id, int qntd, String cpf) throws QuantidadeInvalidaException, ProdutoInexistenteException, PessoaInexistenteException {
        Produto produto = this.consultarProduto(id);
        Cliente cliente = this.consultarCliente(cpf);

        if(produto.getQuantidade() < qntd){
            throw new QuantidadeInvalidaException(qntd);
        }else{
            produto.setQuantidade(produto.getQuantidade() - qntd);
        }

        Venda venda2 = new Venda(qntd, new Produto(produto.getNome(), produto.getMarca(), produto.getId(), produto.getQuantidade(), produto.getPeso(),
                produto.getPreco() * qntd), new Cliente(cliente.getNome(), cliente.getCpf(),
                cliente.getEndereco(), cliente.getTipo()));

        this.registarVenda(venda2);
    }

    public void marcarVendaConcluida(Venda v){
        v.concluirStatus();
        this.repositorioProdutosVendidos.atualizarVenda(v);
    }

    private void registarVenda(Venda venda){
        this.repositorioProdutosVendidos.adicionarVenda(venda);
    }

    public Produto consultarProduto(String id) throws ProdutoInexistenteException{
        return this.repositorioProdutos.getProduto(id);
    }

    public Cliente consultarCliente(String cpf) throws PessoaInexistenteException {
        return (Cliente) this.repositorioPessoa.getPessoa(cpf);
    }

    public void alterarPreco(Produto produto, double preco) throws ProdutoInexistenteException{
        boolean existe = this.repositorioProdutos.verificarProduto(produto.getId());

        if(existe){
            produto.setPreco(preco);
            this.repositorioProdutos.atualizarProduto(produto);
        }else{
            throw new ProdutoInexistenteException(produto.getId());
        }
    }

    public void alterarQuantidade(Produto produto, int qntd) throws ProdutoInexistenteException, QuantidadeInvalidaException{
        boolean existe = this.repositorioProdutos.verificarProduto(produto.getId());

        if(existe){
            if(qntd>0){
                produto.setQuantidade(qntd);
                this.repositorioProdutos.atualizarProduto(produto);
            }else{
                throw new QuantidadeInvalidaException(qntd);
            }
        }else {
            throw new ProdutoInexistenteException(produto.getId());
        }
    }

    public ArrayList<Produto> consultarProdutosEstoque(){
        return this.repositorioProdutos.listarProdutos();
    }

    public ArrayList<Venda> consultarVendaPorData(String data){
        return this.repositorioProdutosVendidos.consultarVendaPorData(data);
    }

    public ArrayList<Venda> consultarVendaProdutos(){
        return this.repositorioProdutosVendidos.consultarVendas();
    }

    public void alterarVenda(Venda venda, String data, String hora){
        venda.setData(data);
        venda.setHora(hora);
        this.repositorioProdutosVendidos.atualizarVenda(venda);
    }

    public ArrayList<Venda> consultarVendaCliente(String cpf){
        return this.repositorioProdutosVendidos.consultarVendaPeloCliente(cpf);
    }

    public ArrayList<Venda> consultarVendasCLienteNaoConcluida(String cpf){
        ArrayList<Venda> vendaCliente = this.repositorioProdutosVendidos.consultarVendaPeloCliente(cpf);
        ArrayList<Venda> vendas = new ArrayList<>();

        for(Venda v: vendaCliente){
            if(v.getStatus().equals("Não concluída")){
                vendas.add(v);
            }
        }
        return vendas;
    }

    public ArrayList<String> consultarHorariosDisponiveisPorData(String data){
        SimpleDateFormat sdfData = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sdfDataComp = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        GregorianCalendar gc = new GregorianCalendar();

        gc.setTime(new Date());
        gc.add(Calendar.HOUR,-1);
        String dataAtual = sdfData.format(gc.getTime());

        ArrayList<Venda> lista = this.repositorioProdutosVendidos.consultarVendaNaoConcluida(data);

        ArrayList<String> listaHoras = new ArrayList<String>(Arrays.asList("08:00", "09:00", "10:00", "11:00", "12:00", "11:00"));

        GregorianCalendar horaDaVenda = new GregorianCalendar();
        GregorianCalendar horaTemp = new GregorianCalendar();
        ArrayList<String> horasRemovidasTemp = new ArrayList<>();

        if(data.equals(dataAtual)){
            for(String hora: listaHoras){
                try{
                    horaTemp.setTime(sdfDataComp.parse(dataAtual + " " + hora));
                    if(horaTemp.getTime().before(gc.getTime())){
                        horasRemovidasTemp.add(hora);
                    }
                }catch (ParseException e){
                    e.printStackTrace();
                }
            }for(String hora: horasRemovidasTemp){
                listaHoras.remove(hora);
            }
        }
        return listaHoras;
    }

    public ArrayList<Venda> consultarVendasPorData(String data){
        return this.repositorioProdutosVendidos.consultarVendaPorData(data);
    }

    public ArrayList<Venda> consultarVendaClientePorData(String cpf, String data){
        ArrayList<Venda> vendaPorData = this.repositorioProdutosVendidos.consultarVendaPorData(data);
        ArrayList<Venda> vendaClientePorData = new ArrayList<>();

        for(Venda v: vendaPorData){
            if(v.getCliente().getCpf().equals(cpf)){
                vendaClientePorData.add(v);
            }
        }
        return vendaClientePorData;
    }
}

