package excecoes;

public class CpfApenasNumerosException extends Exception {
    private String cpf;

    public CpfApenasNumerosException(){
        super("CPF deve conter apenas numeros.\n");
    }
}
