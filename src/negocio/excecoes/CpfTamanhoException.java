package negocio.excecoes;

public class CpfTamanhoException extends Exception {
    private String cpf;

    public CpfTamanhoException(String cpf){
        super("CPF deve conter 11 Digitos.\n");
        this.cpf = cpf;
    }
}
