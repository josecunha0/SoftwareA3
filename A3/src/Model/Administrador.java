package Model;

import DAO.AdministradorDao;
import java.util.*;
import javax.swing.JOptionPane;

public class Administrador {

//    Atributos
    private int Id;
    private String senha;
    private final AdministradorDao dao;

//    Métodos construtores
    public Administrador() {
        this.dao = new AdministradorDao();
    }

    public Administrador(int Id, String senha) {
        this.Id = Id;
        this.senha = senha;
        this.dao = new AdministradorDao();
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
        Administrador obj = dao.findById(Id);
        return obj;
    }
    
    public List<Administrador> getListaAdm() {
        return dao.findAll();
    }
    
//    Cria o login do administrador...
    public boolean login(int Id, String senha) {
        if (getListaAdm().size() == 0) {
            InsertAdministrador(Id, senha);
            JOptionPane.showMessageDialog(null, "Credenciais de login salvas com sucesso!");
            return true;
        } else {
            if (loginCorreto(Id, senha)) {
                return true;
            } else {
                return false;
            }
        } 
    }
    
    public boolean loginCorreto(int Id, String senha) {
        boolean correto = false;
        for (int i = 0; i < getListaAdm().size(); i++) {
            if (getListaAdm().get(i).getId() == Id && getListaAdm().get(i).getSenha().equals(senha)){
                correto = true;
                break;
            }
        }
        return correto;
    }
}
