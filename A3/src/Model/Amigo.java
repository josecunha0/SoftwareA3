package Model;

import Dao.AmigoDao;
import java.util.*;

public class Amigo {
    
//    Atributos
    private boolean devedor;
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

    public Amigo(String nome, String email, String telefone, boolean devedor) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.devedor = devedor;
        this.dao = new AmigoDao();
    }

//    Métodos Getter e Setter

    public int getQuantidadeEmprestimos() {
        return getQuantidadeEmprestimos();
    }

    public void setQuantidadeEmprestimos(int quantidadeEmprestimos) {
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
    
    public boolean getDevedor() {
        return devedor;
    }
    
    public void setDevedor(boolean devedor) {
        this.devedor = devedor;
    }
    
    public List<Amigo> getListaAmigo() {
        return dao.findAll();
    }
    
    public boolean InsertAmigo(String nome, String email, String telefone, boolean devedor) {
        Amigo obj = new Amigo(nome, email, telefone, devedor);
        dao.insert(obj);
        return true;
    }
    
    public boolean DeleteAmigo(String email) {
        dao.delete(email);
        return true;
    }
    
    public boolean UpdateAmigo(String nome, String email, String telefone, boolean devedor) {
        Amigo obj = new Amigo(nome, email, telefone, devedor);
        dao.update(obj);
        return true;
    }
    
    public Amigo CarregaAmigo(String email) {
        Amigo obj = dao.findByEmail(email);
        return obj;
    }

//    Verifica se o Amigo já tem Cadastro    
    public boolean verificarCadastroAmigo(String email) {
        boolean cadastro = false;
        for (int i = 0; i < getListaAmigo().size(); i++) {
            if (getListaAmigo().get(i).getEmail().equals(email))
                cadastro = true;
                break;
        }
        return cadastro;
    }

}
