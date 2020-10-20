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
        boolean existe = repClientes.verificarPessoa(cliente.getCpf());

        if(existe){
            throw new PessoaJaCadastradaException(cliente.getCpf());
        }else{
            this.repClientes.adicionarPessoa(cliente);
        }
    }

    public Cliente consultarCliente(String cpf) throws PessoaInexistenteException{
        return (Cliente) this.repClientes.getPessoa(cpf);
    }

    public void alterarTipoCliente(Cliente cliente, String tipo){
        cliente.setTipo(tipo);
        this.repClientes.atualizarPessoa(cliente);
    }

    public void alterarTelefone(Cliente cliente, String telefone){
        cliente.setTelefone(telefone);
        this.repClientes.atualizarPessoa(cliente);
    }

    public void atualizarEndereco(Endereco endereco, Cliente cliente, String rua, int numero, String bairro, String cidade, String estado){
        endereco.setRua(rua);
        endereco.setNumero(numero);
        endereco.setBairro(bairro);
        endereco.setCidade(cidade);
        endereco.setEstado(estado);
        cliente.setEndereco(endereco);
        this.repClientes.atualizarPessoa(cliente);
    }
}
