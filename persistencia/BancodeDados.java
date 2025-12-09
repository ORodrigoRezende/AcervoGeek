package persistencia;

import java.util.ArrayList;
import java.util.List;
import modelo.Aluga;
import modelo.Cliente;
import modelo.Filme;
import modelo.Jogo;
import modelo.Livro;
import modelo.Produto;


public class BancodeDados {

    private Persistente<Cliente> rCliente;
    private Persistente<Aluga> rAluga;
    private Persistente<Produto> rProduto;

    public BancodeDados() {
        rCliente = new Persistente<>();
        rAluga = new Persistente<>();
        rProduto = new Persistente<>();      
    }

    public Persistente<Cliente> getrCliente() {
        return rCliente;
    }

    public Persistente<Aluga> getrAluga() {
        return rAluga;
    }

    public Persistente<Produto> getrProduto() {
        return rProduto;
    }
    public List<Filme> getListarFilmes() {
        List<Filme> listaFilmes = new ArrayList<>();
        List<Produto> todos = rProduto.getEntidades(); 
        
        for (Produto p : todos) {
            if (p instanceof Filme) {
                listaFilmes.add((Filme) p);
            }
        }
        return listaFilmes;
    }

    public List<Jogo> getListarJogos() {
        List<Jogo> listaJogos = new ArrayList<>();
        for (Produto p : rProduto.getEntidades()) {
            if (p instanceof Jogo) {
                listaJogos.add((Jogo) p);
            }
        }
        return listaJogos;
    }

    public List<Livro> getListarLivros() {
        List<Livro> listaLivros = new ArrayList<>();
        for (Produto p : rProduto.getEntidades()) {
            if (p instanceof Livro) {
                listaLivros.add((Livro) p);
            }
        }
        return listaLivros;
    }
}

