package Model;

import DAO.AmigoDao;
import java.util.*;

public class Amigo {
    
//    Atributos
    private boolean devedor = false;
    private String nome;
    private String email;
    private String telefone;
    private final AmigoDao dao;
    
    @Override
    public String toString() {
        return "Amigo [nome=" + nome + ", telefone=" + telefone + ", email=" + email + ", devedor=" + devedor +"]";
    }

//    Métodos construtores
    public Amigo() {
        this.dao = new AmigoDao();
    }

    public Amigo(String nome, String email, String telefone) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.dao = new AmigoDao();
    }

//    Métodos Getter e Setter
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
    
    public boolean getDevedor() {
        return devedor;
    }
    
    public void setDevedor(boolean devedor) {
        this.devedor = devedor;
    }
    
    public List<Amigo> getListaAmigo() {
        return dao.findAll();
    }
    
    public boolean InsertAmigo(Amigo obj) {
        dao.insert(obj);
        return true;
    }
    
    public boolean DeleteAmigo(String email) {
        dao.deleteById(email);
        return true;
    }
    
    public boolean UpdateAmigo(Amigo obj) {
        dao.update(obj);
        return true;
    }
    
    public Amigo CarregaAmigo(String email) {
        dao.findById(email);
        return null;
    }

}
