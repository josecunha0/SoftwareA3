package Model;

import java.util.*;
import DAO.EmprestimoDao;

public class Emprestimo {

//    Atributos
    private int IdFerramenta;
    private String NomeAmigo;
    private Date dataInicio;
    private Date dataDevolucao;
    private String EmailAmigo;
    private EmprestimoDao dao;
    

//    Métodos construtores
    public Emprestimo() {
        this.dao = new Emprestimo();
    }

    public Emprestimo(int IdFerramenta, String NomeAmigo, Date dataInicio, Date dataDevolucao, String EmailAmigo) {
        this.IdFerramenta = IdFerramenta;
        this.NomeAmigo = NomeAmigo;
        this.dataInicio = dataInicio;
        this.dataDevolucao = dataDevolucao;
        this.EmailAmigo = EmailAmigo;
        this.dao = new Emprestimo();
    }

//    Métodos Getter e Setter
    public int getIdFerramenta() {
        return IdFerramenta;
    }

    public void setFerramenta(int IdFerramenta) {
        this.IdFerramenta = IdFerramenta;
    }

    public String getNomeAmigo() {
        return NomeAmigo;
    }

    public void setNomeAmigo(String NomeAmigo) {
        this.NomeAmigo = NomeAmigo;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }
    
    public String getEmailAmigo() {
        return EmailAmigo;
    }
    
    public void setEmailAmigo(String EmailAmigo) {
        this.EmailAmigo = EmailAmigo;
    }

    public boolean InsertEmprestimo(int IdFerramenta, String NomeAmigo, Date dataInicio, Date dataDevolucao, String EmailAmigo) {
        Emprestimo obj = new Emprestimo(IdFerramenta, NomeAmigo, dataInicio, dataDevolucao, EmailAmigo);
        dao.insert(obj);
        return true;
    }
    
    public boolean DeleteEmprestimo(int IdFerramenta, String EmailAmigo) {
        dao.deleteById(IdFerramenta, EmailAmigo);
        return true;
    }
    
    public boolean UpdateEmprestimo(int IdFerramenta, String NomeAmigo, Date dataInicio, Date dataDevolucao, String EmailAmigo) {
        Emprestimo obj = new Emprestimo(IdFerramenta, NomeAmigo, dataInicio, dataDevolucao, EmailAmigo);
        dao.update(obj);
        return true;
    }
    
    public Emprestimo CarregaEmprestimo(int IdFerramenta, String EmailAmigo) {
        dao.findById(IdFerramenta, EmailAmigo);
        return null;
    }
    
    public List<Emprestimo> getListaEmprestimo() {
        return dao.findAll();
    }

//    Verifica se amigo já tem cadastro
    public boolean verificarCadastroAmigo() {
        return true;
    }
    
//    Verifica se ferramenta já foi cadastrada
    public boolean verificarCadastroFerramenta() {
        return true;
    }
    
//    Verifica se amigo tem algum empréstimo ativo
    public boolean emprestimoAtivo() {
        return true;
    }

//    Gera um relatório de todos os empréstimos
    public String relatorioEmprestimo() {
        return "s";
    }
    
//    Verifica se o empréstimo foi devolvido
    public boolean emprestimoDevolvido() {
        return true;
    }
}
