package dados.contratos;

import negocio.entidades.Pessoa;
import negocio.excecoes.PessoaInexistenteException;

import java.util.ArrayList;

public interface iRepositorioPessoa {
    void adicionarPessoa(Pessoa pessoa);
    void removerPessoa(String cpf) throws PessoaInexistenteException;
    Pessoa getPessoa(String cpf) throws PessoaInexistenteException;
    void atualizarPessoa(Pessoa pessoa);
    boolean verificarPessoa(String cpf);
    ArrayList<Pessoa> listarpessoa();
}
