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

    public void validarCnpj() throws CnpjApenasNumerosException, CnpjTamanhoException{
        boolean contemLetra;
        char[] cnpjArray = this.getCnpj().toCharArray();

        if(this.getCnpj().length() != 14){
            throw new CnpjTamanhoException(this.getCnpj());
        }
        for(int i =0; i < this.getCnpj().length(); i++){
            contemLetra = Character.isLetter(cnpjArray[i]);
            if(contemLetra){
                throw new CnpjApenasNumerosException(this.getCnpj());
            }
        }
    }

    public void cadastrar(){
        Scanner sc = new Scanner(System.in);
        iRepositorioPessoa rep = new RepositorioGerente();
        System.out.println("------------- Cadastrar Gerente ----------------\n");

        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("CPF: ");
        String cpf = sc.nextLine();
        System.out.print("Data de nascimento: ");
        String dataNascimento = sc.nextLine();
        System.out.print("CNPJ: ");
        String cnpj = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Senha: ");
        String senha = sc.nextLine();

        System.out.println("------------- Cadastrar Endereço ----------------\n");
        System.out.print("Rua: ");
        String rua = sc.nextLine();
        System.out.print("Número: ");
        int numero = sc.nextInt();
        System.out.print("Bairro: ");
        String bairro = sc.nextLine();
        System.out.print("Cidade: ");
        String cidade = sc.nextLine();
        System.out.print("Estado: ");
        String estado = sc.nextLine();

        Endereco endereco = new Endereco(rua, numero, bairro, cidade, estado);

        Pessoa gerente = new Gerente(nome, cpf, dataNascimento, endereco, email, senha, cnpj);
        rep.adicionarPessoa(gerente);
    }
}
