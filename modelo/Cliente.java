package modelo;

public class Cliente{
    private int id_cliente;
    private String nome_do_cliente;
    private String telefone_do_cliente;
    private String cpf;
    private String endereco;
    
    public Cliente(){
        this.id_cliente = 0;
        this.nome_do_cliente = "";
        this.telefone_do_cliente = "";
        this.cpf = "";
        this.endereco = "";
    }

    public Cliente(int id, String nome_do_cliente, String telefone_do_cliente, String cpf, String endereco) {
        this.id_cliente = id;
        this.nome_do_cliente = nome_do_cliente;
        this.telefone_do_cliente = telefone_do_cliente;
        this.cpf = cpf;
        this.endereco = endereco;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getNome_do_cliente() {
        return nome_do_cliente;
    }

    public void setNome_do_cliente(String nome_do_cliente) {
        this.nome_do_cliente = nome_do_cliente;
    }

    public String getTelefone_do_cliente() {
        return telefone_do_cliente;
    }

    public void setTelefone_do_cliente(String telefone_do_cliente) {
        this.telefone_do_cliente = telefone_do_cliente;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    public String toString() {
        return "\n Cliente\n Id: "+id_cliente+"\n Nome: " + nome_do_cliente + "\n Telefone: " + telefone_do_cliente + "\n CPF: "+ cpf + "\n Endereço:" + endereco + "\n";
    }
}