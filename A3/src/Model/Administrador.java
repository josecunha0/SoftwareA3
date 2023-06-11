package Model;

import DAO.AdministradorDao;
import java.util.*;

public class Administrador {

//    Atributos
    private int Id;
    private String senha;
    private final AdministradorDao dao;

//    Métodos construtores
    public Administrador() {
        this.dao = new Administrador();
    }

    public Administrador(int Id, String senha) {
        this.Id = Id;
        this.senha = senha;
        this.dao = new Administrador();
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
    
    public boolean InsertAdministrador(int Id, String senha) {
        Administrador obj = new Administrador(Id, senha);
        dao.insert(obj);
        return true;
    }
    
    public boolean DeleteAdministrador(int Id) {
        dao.deleteById(Id);
        return true;
    }
    
    public boolean UpdateAdministrador(int Id, String senha) {
        Administrador obj = new Administrador(Id, senha);
        dao.update(obj);
        return true;
    }
    
    public Administrador CarregaAdministrador(int Id) {
        dao.findById(Id);
        return null;
    }
    
    public List<Administrador> getListaAdm() {
        return dao.findAll();
    }
    
//    Cria o login do administrador...
    public void criarLogin(int Id, String senha) {
        InsertAdministrador(Id, senha);       
    }
    
    public boolean loginCorreto(int Id, String senha) {
        for (int i = 0; i < getListaAdm().size(); i++) {
            if (getListaAdm().get(i).getId == Id && getListaAdm().get(i).getSenha == senha){
                return true;
            }
        }
        
    }
}
