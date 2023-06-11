package Model;

import DAO.FerramentaDao;
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
        this.dao = new Ferramenta();
    }

    public Ferramenta(int Id, String nome, String marca, double custoDeAquisicao, boolean disponivel) {
        this.Id = Id;
        this.nome = nome;
        this.marca = marca;
        this.custoDeAquisicao = custoDeAquisicao;
        this.disponivel = disponivel;
        this.dao = new Ferramenta();
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
    
    public boolean InsertFerramenta(int Id, String nome, String marca, double custoDeAquisicao, boolean disponivel) {
        Ferramenta obj = new Ferramenta(Id, nome, marca, custoDeAquisicao, disponivel);
        dao.insert(obj);
        return true;
    }
    
    public boolean DeleteFerramenta(int Id) {
        dao.deleteById(Id);
        return true;
    }
    
    public boolean UpdateFerramenta(int Id, String nome, String marca, double custoDeAquisicao, boolean disponivel) {
        Ferramenta obj = new Ferramenta(Id, nome, marca, custoDeAquisicao, disponivel);
        dao.update(obj);
        return true;
    }
    
    public Ferramenta CarregaFerramenta(int Id) {
        dao.findById(Id);
        return null;
    }
    
    public List<Ferramenta> getListaFerramenta() {
        return dao.findAll();
    }

//    Gera um relatório da soma do custo de aquisição das ferramentas    
    public String relatorioFerramentas() {
        return "s";
    }
}
