package negocio.gerenciadores;


import negocio.entidades.Pessoa;
import negocio.entidades.Endereco;
import negocio.excecoes.CpfTamanhoException;
import negocio.excecoes.CpfApenasNumerosException;
import negocio.excecoes.NomeApenasLetrasException;
import negocio.excecoes.NomeTamanhoException;

public class NegocioPessoa extends Pessoa {

    public NegocioPessoa(String nome, String cpf, String dataNascimento, Endereco endereco) {
        super(nome, cpf, dataNascimento, endereco);
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

    public void validarNome() throws NomeApenasLetrasException, NomeTamanhoException {
        boolean contemNumero;
        char[] nomeArray = this.getNome().toCharArray();
        if(this.getNome().length() < 5 || this.getNome().length() >= 50){
            throw new NomeTamanhoException(this.getNome());
        }
        for (int i = 0; i<this.getNome().length(); i++){
            contemNumero = Character.isLetter(nomeArray[i]);
            if(!contemNumero){
                throw new NomeApenasLetrasException(this.getNome());
            }
        }
    }
}
