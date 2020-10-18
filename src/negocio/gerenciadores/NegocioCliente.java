package negocio.gerenciadores;

import negocio.entidades.Cliente;
import negocio.entidades.Endereco;
import negocio.excecoes.ClienteTipoInvalidoException;

public class NegocioCliente extends Cliente {
    public NegocioCliente(String nome, String cpf, String dataNascimento, Endereco endereco, String tipo){
        super(nome, cpf, dataNascimento, endereco, tipo);
    }

    public void validarTipo() throws ClienteTipoInvalidoException {
        if(this.getTipo().toLowerCase() != "consumidor" || this.getTipo().toLowerCase() != "revendedor"){
            throw new ClienteTipoInvalidoException(this.getTipo());
        }
    }
}
