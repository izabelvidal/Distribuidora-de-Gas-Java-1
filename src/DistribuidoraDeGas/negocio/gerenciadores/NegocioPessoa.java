package DistribuidoraDeGas.negocio.gerenciadores;


import DistribuidoraDeGas.dados.contratos.iRepositorioPessoa;
import DistribuidoraDeGas.negocio.entidades.Pessoa;
import DistribuidoraDeGas.negocio.excecoes.CpfTamanhoException;
import DistribuidoraDeGas.negocio.excecoes.CpfApenasNumerosException;
import DistribuidoraDeGas.negocio.excecoes.NomeApenasLetrasException;
import DistribuidoraDeGas.negocio.excecoes.NomeTamanhoException;

public class NegocioPessoa{
    private iRepositorioPessoa repositorioCliente;
    private iRepositorioPessoa repositorioGerente;

    public NegocioPessoa(iRepositorioPessoa repositorioCliente, iRepositorioPessoa repositorioGerente){
        this.repositorioCliente = repositorioCliente;
        this.repositorioGerente = repositorioGerente;
    }

    public void validarCpf(Pessoa pessoa) throws CpfApenasNumerosException, CpfTamanhoException{
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

    public void validarNome(Pessoa pessoa) throws NomeApenasLetrasException, NomeTamanhoException {
        boolean contemNumero;
        char[] nomeArray = pessoa.getNome().toCharArray();
        if (pessoa.getNome().length() < 5 || pessoa.getNome().length() >= 50) {
            throw new NomeTamanhoException(pessoa.getNome());
        }
        for (int i = 0; i < pessoa.getNome().length(); i++) {
            contemNumero = Character.isLetter(nomeArray[i]);
            if (!contemNumero) {
                throw new NomeApenasLetrasException(pessoa.getNome());
            }
        }
    }

}
