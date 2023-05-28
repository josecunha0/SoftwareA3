package Model;

public class Amigo {

//    Atributos
    private int Id;
    private String nome;
    private String email;
    private int telefone;

//    Métodos construtores
    public Amigo() {
    }

    public Amigo(int Id, String nome, String email, int telefone) {
        this.Id = Id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
    }

//    Métodos Getter e Setter
    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTelefone() {
        return telefone;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }
    
    
}
