package DistribuidoraDeGas.negocio.gerenciadores;

import DistribuidoraDeGas.dados.RepositorioCliente;
import DistribuidoraDeGas.negocio.entidades.Cliente;
import DistribuidoraDeGas.negocio.entidades.Endereco;
import DistribuidoraDeGas.negocio.entidades.Pessoa;
import DistribuidoraDeGas.negocio.excecoes.PessoaInexistenteException;
import DistribuidoraDeGas.negocio.excecoes.PessoaJaCadastradaException;

/**
 * Essa classe aplica as regras de negócio e o gerenciamento do Cliente no sistema
 *
 * @author Izabel Vidal
 */

public class NegocioCliente{
    private RepositorioCliente repClientes;
    public NegocioCliente(RepositorioCliente rep){
        this.repClientes = rep;
    }

    public void adicionarCliente(Pessoa cliente) throws PessoaJaCadastradaException{
        this.repClientes.adicionarPessoa(cliente);
    }

    public Cliente consultarCliente(String cpf) throws PessoaInexistenteException{
        return (Cliente) this.repClientes.getCliente(cpf);
    }

    public void alterarTipoCliente(Cliente cliente, String tipo){
        cliente.setTipo(tipo);
        this.repClientes.atualizarPessoa(cliente);
    }

    public void alterarTelefone(Cliente cliente, String telefone){
        cliente.setTelefone(telefone);
        this.repClientes.atualizarPessoa(cliente);
    }

    public void atualizarRua(String rua, Cliente cliente, Endereco endereco){
        endereco.setRua(rua);
        cliente.setEndereco(endereco);
        this.repClientes.atualizarPessoa(cliente);
    }

    public void atualizarBairro(String bairro, Cliente cliente, Endereco endereco){
        endereco.setBairro(bairro);
        cliente.setEndereco(endereco);
        this.repClientes.atualizarPessoa(cliente);
    }
    public void atualizarNumero(int numero, Cliente cliente, Endereco endereco){
        endereco.setNumero(numero);
        cliente.setEndereco(endereco);
        this.repClientes.atualizarPessoa(cliente);
    }
    public void atualizarCidade(String cidade, Cliente cliente, Endereco endereco){
        endereco.setCidade(cidade);
        cliente.setEndereco(endereco);
        this.repClientes.atualizarPessoa(cliente);
    }
    public void atualizarEstado(String estado, Cliente cliente, Endereco endereco){
        endereco.setEstado(estado);
        cliente.setEndereco(endereco);
        this.repClientes.atualizarPessoa(cliente);
    }
}
