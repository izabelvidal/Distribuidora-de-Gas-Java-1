package negocio.gerenciadores;

import negocio.entidades.Endereco;
import negocio.entidades.Gerente;
import negocio.excecoes.SenhaCaracteresInvalidosException;
import negocio.excecoes.SenhaTamanhoException;

public class NegocioGerente extends Gerente {
    public NegocioGerente(String nome, String cpf, String dataNascimento, Endereco endereco, String email, String senha, String cnpj){
        super(nome, cpf, dataNascimento, endereco, email, senha, cnpj);
    }

    public void validarSenha() throws SenhaTamanhoException, SenhaCaracteresInvalidosException {
        if(this.getSenha().length() < 8 ){
            throw new SenhaTamanhoException(this.getSenha());
        }
        if(!this.getSenha().matches("[a-zA-Z_0-9]")){
            throw new SenhaCaracteresInvalidosException(this.getSenha());
        }
    }
}
