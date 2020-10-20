package DistribuidoraDeGas.negocio.excecoes;

public class CnpjTamanhoException extends Exception {
    private String cnpj;

    public CnpjTamanhoException(String cnpj){
        super("O CNPJ deve conter 14 Digitos.\n");
        this.cnpj = cnpj;
    }
}
