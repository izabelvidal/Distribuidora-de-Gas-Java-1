package excecoes;

public class CpfTamanhoException extends Exception {
    private String CPF;
    public CpfTamanhoException(String CPF){
        super("CPF deve conter 11 Digitos.\n");
        this.CPF = CPF;
    }
}
