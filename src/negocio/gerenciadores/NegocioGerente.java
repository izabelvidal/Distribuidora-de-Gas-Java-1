package negocio.gerenciadores;

import dados.RepositorioGerente;
import dados.contratos.iRepositorioPessoa;
import negocio.entidades.Endereco;
import negocio.entidades.Gerente;
import negocio.entidades.Pessoa;
import negocio.excecoes.CnpjApenasNumerosException;
import negocio.excecoes.CnpjTamanhoException;
import negocio.excecoes.SenhaCaracteresInvalidosException;
import negocio.excecoes.SenhaTamanhoException;

import java.util.Scanner;

public class NegocioGerente{
    private RepositorioGerente repGerente;
    private Gerente gerente;

    public NegocioGerente(RepositorioGerente repGerente, Gerente gerente){
        this.repGerente = repGerente;
        this.gerente = gerente;
    }

    public void validarSenha() throws SenhaTamanhoException, SenhaCaracteresInvalidosException {
        if(gerente.getSenha().length() < 8 ){
            throw new SenhaTamanhoException(gerente.getSenha());
        }
        if(!gerente.getSenha().matches("[a-zA-Z_0-9]")){
            throw new SenhaCaracteresInvalidosException(gerente.getSenha());
        }
    }

    public void validarCnpj() throws CnpjApenasNumerosException, CnpjTamanhoException{
        boolean contemLetra;
        char[] cnpjArray = gerente.getCnpj().toCharArray();

        if(gerente.getCnpj().length() != 14){
            throw new CnpjTamanhoException(gerente.getCnpj());
        }
        for(int i =0; i < gerente.getCnpj().length(); i++){
            contemLetra = Character.isLetter(cnpjArray[i]);
            if(contemLetra){
                throw new CnpjApenasNumerosException(gerente.getCnpj());
            }
        }
    }

    public void alterarEmail(Gerente gerente, String email){
        gerente.setEmail(email);
        this.repGerente.atualizarPessoa(gerente);
    }

    public void alterarSenha(Gerente gerente, String senha){
        gerente.setSenha(senha);
        this.repGerente.atualizarPessoa(gerente);
    }

    public void atualizarEndereco(Endereco endereco, Gerente gerente, String rua, int numero, String bairro, String cidade, String estado){
        endereco.setRua(rua);
        endereco.setNumero(numero);
        endereco.setBairro(bairro);
        endereco.setCidade(cidade);
        endereco.setEstado(estado);
        gerente.setEndereco(endereco);
        this.repGerente.atualizarPessoa(gerente);
    }
}
