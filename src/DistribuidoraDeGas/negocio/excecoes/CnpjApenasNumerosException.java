package DistribuidoraDeGas.negocio.excecoes;

public class CnpjApenasNumerosException extends Exception{
    String cnpj;
    public CnpjApenasNumerosException(String cnpj){
        super("O CNPJ deve conter apenas números\n");
        this.cnpj = cnpj;
    }

    public String getCnpj() {
        return cnpj;
    }
}
