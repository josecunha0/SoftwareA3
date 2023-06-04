package Model;

public class Amigo {
    
    

//    Atributos
    private int Id;
    private String nome;
    private String email;
    private String telefone;
    
    @Override
    public String toString() {
        return "Amigo [id=" + Id + ", nome=" + nome + ", telefone=" + telefone + ", email=" + email + "]";
    }

//    Métodos construtores
    public Amigo() {
    }

    public Amigo(int Id, String nome, String email, String telefone) {
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    
    
}
