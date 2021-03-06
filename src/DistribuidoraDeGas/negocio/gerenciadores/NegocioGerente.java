package DistribuidoraDeGas.negocio.gerenciadores;

import DistribuidoraDeGas.dados.RepositorioGerente;
import DistribuidoraDeGas.negocio.entidades.Endereco;
import DistribuidoraDeGas.negocio.entidades.Gerente;
import DistribuidoraDeGas.negocio.entidades.Pessoa;
import DistribuidoraDeGas.negocio.excecoes.*;

public class NegocioGerente{
    private RepositorioGerente repGerente;

    public NegocioGerente(RepositorioGerente repGerente){
        this.repGerente = repGerente;
    }

    public void adicionarGerente(Pessoa gerente) throws PessoaJaCadastradaException {
        boolean existe = repGerente.verificarPessoa(gerente.getCpf());

        if(existe){
            throw new PessoaJaCadastradaException(gerente.getCpf());
        }else{
            this.repGerente.adicionarPessoa(gerente);
        }
    }

    public void validarSenha(Gerente gerente) throws SenhaTamanhoException, SenhaCaracteresInvalidosException {
        if(gerente.getSenha().length() < 8 ){
            throw new SenhaTamanhoException(gerente.getSenha());
        }
        if(!gerente.getSenha().matches("[a-zA-Z_0-9]")){
            throw new SenhaCaracteresInvalidosException(gerente.getSenha());
        }
    }

    public void validarCnpj(Gerente gerente) throws CnpjApenasNumerosException, CnpjTamanhoException{
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

    public void alterarTelefone(Gerente gerente, String telefone){
        gerente.setTelefone(telefone);
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

    public Pessoa consultarGerente(String cpf) throws PessoaInexistenteException {
        return this.repGerente.getPessoa(cpf);
    }
}
