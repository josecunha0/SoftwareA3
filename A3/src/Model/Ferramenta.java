package Model;

import Dao.FerramentaDao;
import java.util.*;

public class Ferramenta {

//    Atributos
    private int Id;
    private String nome;
    private String marca;
    private double custoDeAquisicao;
    private boolean disponivel;
    private final FerramentaDao dao;

//    Métodos Construtores
    public Ferramenta() {
        this.dao = new FerramentaDao();
    }

    public Ferramenta(int Id, String nome, String marca, double custoDeAquisicao, boolean disponivel) {
        this.Id = Id;
        this.nome = nome;
        this.marca = marca;
        this.custoDeAquisicao = custoDeAquisicao;
        this.disponivel = disponivel;
        this.dao = new FerramentaDao();
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

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public double getCustoDeAquisicao() {
        return custoDeAquisicao;
    }

    public void setCustoDeAquisicao(double custoDeAquisicao) {
        this.custoDeAquisicao = custoDeAquisicao;
    }
    
    public boolean getDisponivel() {
        return disponivel;
    }
    
    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }
    
    public boolean InsertFerramenta(String nome, String marca, double custoDeAquisicao, boolean disponivel) {
        Ferramenta obj = new Ferramenta(Id, nome, marca, custoDeAquisicao, disponivel);
        dao.insert(obj);
        return true;
    }
    
    public boolean DeleteFerramenta(int Id) {
        dao.delete(Id);
        return true;
    }
    
    public boolean UpdateFerramenta(int Id, String nome, String marca, double custoDeAquisicao, boolean disponivel) {
        Ferramenta obj = new Ferramenta(Id, nome, marca, custoDeAquisicao, disponivel);
        dao.update(obj);
        return true;
    }
    
    public Ferramenta CarregaFerramenta(int Id) {
        Ferramenta obj = dao.findById(Id);
        return obj;
    }
    
    public List<Ferramenta> getListaFerramenta() {
        return dao.findAll();
    }
    
    //    Verifica se ferramenta já foi cadastrada
    public boolean verificarCadastroFerramenta(int Id) {
        boolean cadastro = false;
        for (int i = 0; i < getListaFerramenta().size(); i++) {
            if (getListaFerramenta().get(i).getId() == Id) {
                cadastro = true;
                break;
            }
        }
        return cadastro;
    }
    
    public boolean ferramentaDisponivel(int Id) {
        Ferramenta objFe = new Ferramenta();
        objFe = CarregaFerramenta(Id);
        if (objFe.getDisponivel() == true) {
            return true;
        } else {
            return false;
        }
    }

//    Gera um relatório da soma do custo de aquisição das ferramentas    
    public double relatorioFerramentas() {
        double custoTotal = 0;
        for (int i = 0; i < getListaFerramenta().size(); i++) {
            custoTotal += getListaFerramenta().get(i).getCustoDeAquisicao();
        }
        return custoTotal;
    }
}
