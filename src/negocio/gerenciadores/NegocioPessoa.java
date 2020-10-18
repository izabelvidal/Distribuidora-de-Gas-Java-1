package negocio.gerenciadores;


import dados.contratos.iRepositorioPessoa;
import negocio.entidades.Pessoa;
import negocio.excecoes.CpfTamanhoException;
import negocio.excecoes.CpfApenasNumerosException;
import negocio.excecoes.NomeApenasLetrasException;
import negocio.excecoes.NomeTamanhoException;

public class NegocioPessoa{
    private iRepositorioPessoa repositorioPessoa;
    private Pessoa pessoa;

    public NegocioPessoa(iRepositorioPessoa repPessoa, Pessoa pessoa){
        this.repositorioPessoa = repPessoa;
        this.pessoa = pessoa;
    }

    public void validarCpf() throws CpfApenasNumerosException, CpfTamanhoException{
        boolean contemLetra = false;
        char[] cpfArray = pessoa.getCpf().toCharArray();

        if(pessoa.getCpf().length() != 11){
            throw new CpfTamanhoException(pessoa.getCpf());
        }
        for(int i = 0; i < pessoa.getCpf().length(); i++){
            contemLetra = Character.isLetter(cpfArray[i]);
            if(contemLetra){
                throw new CpfApenasNumerosException(pessoa.getCpf());
            }
        }

    }

    public void validarNome() throws NomeApenasLetrasException, NomeTamanhoException {
        boolean contemNumero;
        char[] nomeArray = pessoa.getNome().toCharArray();
        if(pessoa.getNome().length() < 5 || pessoa.getNome().length() >= 50){
            throw new NomeTamanhoException(pessoa.getNome());
        }
        for (int i = 0; i<pessoa.getNome().length(); i++){
            contemNumero = Character.isLetter(nomeArray[i]);
            if(!contemNumero){
                throw new NomeApenasLetrasException(pessoa.getNome());
            }
        }
    }



}
