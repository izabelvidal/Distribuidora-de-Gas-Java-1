package negocios;

import entidades.Endereco;
import entidades.Pessoa;
import excecoes.CpfApenasNumerosException;
import excecoes.CpfTamanhoException;
import excecoes.NomeApenasLetrasException;

public class NegocioPessoa extends Pessoa {

    public NegocioPessoa(String nome, String email, String senha, String cpf, Endereco endereco) {
        super(nome, email, senha, cpf, endereco);
    }

    public void validarCpf() throws CpfTamanhoException, CpfApenasNumerosException {
        boolean contemLetra = false;
        char[] cpfArray = this.getCpf().toCharArray();

        if(this.getCpf().length() != 11){
            throw new CpfTamanhoException(this.getCpf());
        }
        for(int i = 0; i < this.getCpf().length(); i++){
            contemLetra = Character.isLetter(cpfArray[i]);
            if(contemLetra){
                throw new CpfApenasNumerosException(this.getCpf());
            }
        }
    }

    public void validarNome() throws NomeApenasLetrasException {
        boolean contemNumero;
        char[] nomeArray = this.getNome().toCharArray();
        if(this.getNome().length() < 5 || this.getNome().length() > 50){

        }
        for (int i = 0; i<this.getNome().length(); i++){
            contemNumero = Character.isLetter(nomeArray[i]);
            if(!contemNumero){
                throw new NomeApenasLetrasException(this.getNome());
            }
        }
    }
}
