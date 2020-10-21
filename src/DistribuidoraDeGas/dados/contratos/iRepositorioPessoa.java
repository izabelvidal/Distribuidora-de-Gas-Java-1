package DistribuidoraDeGas.dados.contratos;

import DistribuidoraDeGas.negocio.entidades.Pessoa;
import DistribuidoraDeGas.negocio.excecoes.PessoaInexistenteException;
import DistribuidoraDeGas.negocio.excecoes.PessoaJaCadastradaException;

import java.util.ArrayList;
/**
 * Classe de interface para repositórios de Pessoas.
 *
 * @author Izabel Vidal
 */
public interface iRepositorioPessoa {
    void adicionarPessoa(Pessoa pessoa) throws PessoaJaCadastradaException;
    void removerPessoa(String cpf) throws PessoaInexistenteException;
    Pessoa getPessoa(String cpf) throws PessoaInexistenteException;
    void atualizarPessoa(Pessoa pessoa);
    boolean verificarPessoa(String cpf);
    ArrayList<Pessoa> listarpessoa();
}
