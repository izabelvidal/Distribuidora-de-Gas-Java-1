package excecoes;

public class ProdutoJaCadastradoException extends Exception{
    private String id;

    public ProdutoJaCadastradoException(String id){
        super("Produto já cadastrado com ID: " + id);
        this.id = id;
    }

    public String getId(){
        return this.id;
    }
}
