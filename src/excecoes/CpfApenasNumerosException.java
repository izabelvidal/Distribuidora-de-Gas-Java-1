package excecoes;

public class CpfApenasNumerosException extends Exception {
    private String cpf;

    public CpfApenasNumerosException(String cpf){
        super("CPF deve conter apenas numeros.\n");
        this.cpf =cpf;
    }

    public String getCpf() {
        return cpf;
    }
}
