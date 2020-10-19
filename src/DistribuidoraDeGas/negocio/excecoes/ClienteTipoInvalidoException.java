package DistribuidoraDeGas.negocio.excecoes;

public class ClienteTipoInvalidoException extends Exception {
    String tipo;
    public ClienteTipoInvalidoException(String tipo){
        super("Tipo inválido de cliente!!\n" + tipo);
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }
}
