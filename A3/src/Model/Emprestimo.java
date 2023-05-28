package Model;
import java.util.*;

public class Emprestimo {

//    Atributos
    private int Id;
    private String ferramenta;
    private String amigo;
    private Date dataInicio;
    private Date dataDevolucao;

//    Métodos construtores
    public Emprestimo() {
    }

    public Emprestimo(int Id, String ferramenta, String amigo, Date dataInicio, Date dataDevolucao) {
        this.Id = Id;
        this.ferramenta = ferramenta;
        this.amigo = amigo;
        this.dataInicio = dataInicio;
        this.dataDevolucao = dataDevolucao;
    }

//    Métodos Getter e Setter
    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getFerramenta() {
        return ferramenta;
    }

    public void setFerramenta(String ferramenta) {
        this.ferramenta = ferramenta;
    }

    public String getAmigo() {
        return amigo;
    }

    public void setAmigo(String amigo) {
        this.amigo = amigo;
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
