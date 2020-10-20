package DistribuidoraDeGas.dados;

import DistribuidoraDeGas.negocio.entidades.Pessoa;
import DistribuidoraDeGas.negocio.excecoes.PessoaInexistenteException;
import DistribuidoraDeGas.dados.contratos.iRepositorioPessoa;

import java.util.ArrayList;

/**
 * Essa classe implementa os métodos da interface iRepositorioPessoa
 * Essa classe é responsável por armazenar e gerenciar a recuperação dos Clientes
 *
 * @author Izabel Vidal
 */
public class RepositorioCliente implements iRepositorioPessoa {
    private ArrayList<Pessoa> clientes;

    public RepositorioCliente(){
        this.clientes = new ArrayList<>();
    }

    @Override
    public void adicionarPessoa(Pessoa cliente) {
        this.clientes.add(cliente);
    }

    @Override
    public void removerPessoa(String cpf) throws PessoaInexistenteException{
        try{
            Pessoa cliente = this.getPessoa(cpf);
            this.clientes.remove(cliente);
        }catch (PessoaInexistenteException e){
            throw new PessoaInexistenteException(cpf);
        }
    }

    @Override
    public Pessoa getPessoa(String cpf) throws PessoaInexistenteException{
        int index;
        for(Pessoa c: this.clientes){
            if(c.getCpf().equals(cpf)){
                index = this.clientes.indexOf(c);
                return this.clientes.get(index);
            }
        }
        throw new PessoaInexistenteException(cpf);
    }

    @Override
    public void atualizarPessoa(Pessoa cliente) {
        int index = this.clientes.indexOf(cliente);
        this.clientes.set(index, cliente);
    }

    @Override
    public boolean verificarPessoa(String cpf) {
        for(Pessoa c: this.clientes){
            if(cpf.equals(c.getCpf())){
                return true;
            }
        }

        return false;
    }

    @Override
    public ArrayList<Pessoa> listarpessoa() {
        return this.clientes;
    }
}
