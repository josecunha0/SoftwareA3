package Model;

public class Ferramenta {

//    Atributos
    private int Id;
    private String nome;
    private String marca;
    private double custoDeAquisicao;

//    Métodos Construtores
    public Ferramenta() {
    }

    public Ferramenta(int Id, String nome, String marca, double custoDeAquisicao) {
        this.Id = Id;
        this.nome = nome;
        this.marca = marca;
        this.custoDeAquisicao = custoDeAquisicao;
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

//    Gera um relatório da soma do custo de aquisição das ferramentas    
    public String relatorioFerramentas() {
        return "s";
    }
}
