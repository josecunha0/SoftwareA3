package Model;

public class Ferramenta {

//    Atributos
    private int Id;
    private String nome;
    private String marca;
    private double custo_de_aquisicao;

//    Métodos Construtores
    public Ferramenta() {
    }

    public Ferramenta(int Id, String nome, String marca, double custo_de_aquisicao) {
        this.Id = Id;
        this.nome = nome;
        this.marca = marca;
        this.custo_de_aquisicao = custo_de_aquisicao;
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
        return custo_de_aquisicao;
    }

    public void setCustoDeAquisicao(double custo_de_aquisicao) {
        this.custo_de_aquisicao = custo_de_aquisicao;
    }

//    Gera um relatório da soma do custo de aquisição das ferramentas    
    public String relatorioFerramentas() {
        return "s";
    }
}
