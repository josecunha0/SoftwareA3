package Model;

import java.util.*;
import DAO.EmprestimoDao;
import Model.Ferramenta;
import javax.swing.JOptionPane;
import java.text.*;

public class Emprestimo {

//    Atributos
    private int IdFerramenta;
    private String NomeAmigo;
    private Date dataInicio;
    private Date dataDevolucao;
    private String EmailAmigo;
    private boolean devolvido;
    private EmprestimoDao dao;
    private Ferramenta ferramenta;
    private Amigo amigo;
    

//    Métodos construtores
    public Emprestimo() {
        this.dao = new EmprestimoDao();
        this.ferramenta = new Ferramenta();
        this.amigo = new Amigo();
    }

    public Emprestimo(int IdFerramenta, String NomeAmigo, Date dataInicio, Date dataDevolucao, String EmailAmigo, boolean devolvido) {
        this.IdFerramenta = IdFerramenta;
        this.NomeAmigo = NomeAmigo;
        this.dataInicio = dataInicio;
        this.dataDevolucao = dataDevolucao;
        this.EmailAmigo = EmailAmigo;
        this.devolvido = devolvido;
        this.dao = new EmprestimoDao();
        this.ferramenta = new Ferramenta();
        this.amigo = new Amigo();
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
    
    public boolean getDevolvido() {
        return devolvido;
    }
    
    public void setDevolvido(boolean devolvido) {
        this.devolvido = devolvido;
    }

    public boolean InsertEmprestimo(int IdFerramenta, String NomeAmigo, Date dataInicio, Date dataDevolucao, String EmailAmigo, boolean devolvido) {
        Emprestimo obj = new Emprestimo(IdFerramenta, NomeAmigo, dataInicio, dataDevolucao, EmailAmigo, devolvido);
        dao.insert(obj);
        Ferramenta objFe = ferramenta.CarregaFerramenta(IdFerramenta);
        objFe.setDisponivel(false);
        ferramenta.UpdateFerramenta(objFe.getId(), objFe.getNome(), objFe.getMarca(), objFe.getCustoDeAquisicao(), objFe.getDisponivel());
        return true;
    }
    
    public boolean DeleteEmprestimo(int IdFerramenta, String EmailAmigo) {
        dao.deleteById(IdFerramenta, EmailAmigo);
        return true;
    }
    
    public boolean UpdateEmprestimo(int IdFerramenta, String NomeAmigo, Date dataInicio, Date dataDevolucao, String EmailAmigo, boolean devolvido) {
        Emprestimo obj = new Emprestimo(IdFerramenta, NomeAmigo, dataInicio, dataDevolucao, EmailAmigo, devolvido);
        dao.update(obj);
        return true;
    }
    
    public Emprestimo CarregaEmprestimo(int IdFerramenta, String EmailAmigo) {
        Emprestimo obj = dao.findById(IdFerramenta, EmailAmigo);
        return obj;
    }
    
    public List<Emprestimo> getListaEmprestimo() {
        return dao.findAll();
    }
    
//    Retorna quantos empréstimos estão ativos
    public int emprestimosAtivos() {
        int ativos = 0;
        for (int i = 0; i < getListaEmprestimo().size(); i++) {
            if (getListaEmprestimo().get(i).getDevolvido()) {
                ativos++;
            }
        }
        return ativos;
    }

//    Retorna o total de empréstimos realizados
    public int totalEmprestimos() {
        int total = getListaEmprestimo().size();
        return total;
    }
    
//    Registra a devolução de um empréstimo
    public void emprestimoDevolvido(int IdFerramenta, String EmailAmigo) {
        Emprestimo objEmp = new Emprestimo();
        objEmp = CarregaEmprestimo(IdFerramenta, EmailAmigo);
        objEmp.setDevolvido(true);
        objEmp.UpdateEmprestimo(objEmp.getIdFerramenta(), objEmp.getNomeAmigo(), objEmp.getDataInicio(), objEmp.getDataDevolucao(), objEmp.getEmailAmigo(), objEmp.getDevolvido());
        JOptionPane.showMessageDialog(null, "Devolução registrada com sucesso!");
        
        Ferramenta objFe = ferramenta.CarregaFerramenta(IdFerramenta);
        objFe.setDisponivel(true);
        objFe.UpdateFerramenta(objFe.getId(), objFe.getNome(), objFe.getMarca(), objFe.getCustoDeAquisicao(), objFe.getDisponivel());
        
        Amigo objAmigo = amigo.CarregaAmigo(EmailAmigo);
        objAmigo.setDevedor(false);
        objAmigo.UpdateAmigo(objAmigo.getNome(), objAmigo.getEmail(), objAmigo.getTelefone(), objAmigo.getDevedor());
    }
    
//    Retorna se o amigo a ser cadastrado é um devedor ou não
    public boolean devedor(String EmailAmigo) {
        boolean devedor = false;
        Date dateAtual = new Date();
        for (int i = 0; i < getListaEmprestimo().size(); i++) {
            if (getListaEmprestimo().get(i).getEmailAmigo().equals(EmailAmigo)) {
                if (dateAtual.after(getListaEmprestimo().get(i).getDataDevolucao())) {
                    if (getListaEmprestimo().get(i).getDevolvido() == false) {
                        devedor = true;
                        break;
                    }
                }
            }
        }
        return devedor;
    }

//    Retorna o total de devedores no sistema
    public int totalDevedores() {
        int devedores = 0;
        for (int i = 0; i < amigo.getListaAmigo().size(); i++) {
            if (amigo.getListaAmigo().get(i).getDevedor()) {
                devedores++;
            }
        }
        return devedores;
    }
}
