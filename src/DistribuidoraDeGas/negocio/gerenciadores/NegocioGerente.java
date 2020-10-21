package DistribuidoraDeGas.negocio.gerenciadores;

import DistribuidoraDeGas.dados.RepositorioGerente;
import DistribuidoraDeGas.negocio.entidades.Cliente;
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

    public void atualizarRua(String rua, Gerente gerente, Endereco endereco){
        endereco.setRua(rua);
        gerente.setEndereco(endereco);
        this.repGerente.atualizarPessoa(gerente);
    }

    public void atualizarBairro(String bairro, Gerente gerente, Endereco endereco){
        endereco.setBairro(bairro);
        gerente.setEndereco(endereco);
        this.repGerente.atualizarPessoa(gerente);
    }
    public void atualizarNumero(int numero, Gerente gerente, Endereco endereco){
        endereco.setNumero(numero);
        gerente.setEndereco(endereco);
        this.repGerente.atualizarPessoa(gerente);
    }
    public void atualizarCidade(String cidade, Gerente gerente, Endereco endereco){
        endereco.setCidade(cidade);
        gerente.setEndereco(endereco);
        this.repGerente.atualizarPessoa(gerente);
    }
    public void atualizarEstado(String estado, Gerente gerente, Endereco endereco){
        endereco.setEstado(estado);
        gerente.setEndereco(endereco);
        this.repGerente.atualizarPessoa(gerente);
    }

    public Gerente consultarGerente(String cpf) throws PessoaInexistenteException {
        return (Gerente) this.repGerente.getPessoa(cpf);
    }
}
