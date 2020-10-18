package negocio.gerenciadores;

import dados.RepositorioCliente;
import negocio.entidades.Cliente;
import negocio.entidades.Pessoa;
import negocio.excecoes.PessoaInexistenteException;
import negocio.excecoes.PessoaJaCadastradaException;

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
}
