package Model;

import DAO.AdministradorDao;

public class Administrador {

//    Atributos
    private int Id;
    private String senha;
    private String nome;

//    Métodos construtores
    public Administrador() {
    }

    public Administrador(int Id, String senha, String nome) {
        this.Id = Id;
        this.senha = senha;
        this.nome = nome;
    }

//    Métodos Getter e Setter
    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public void InsertAdministrador(Administrador obj) {
    }
    
//    Cria o login do administrador...
    public void criarLogin() {
        
    }
}
