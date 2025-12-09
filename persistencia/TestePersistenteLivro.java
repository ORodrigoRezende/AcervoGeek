package persistencia;

import modelo.Livro;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestePersistenteLivro {

    //inserir
    @Test
    public void testeInserirIdNovo() {
        Persistente<Livro> p = new Persistente<>();

        Livro l = new Livro("Harry Potter", "ficção", 20f, "nao sei", 1);
        boolean resultado = p.inserir(l);
        assertTrue(resultado);
        assertEquals(l, p.getEntidades().get(0));
    }

    @Test
    public void testeInserirIdExistente() {
        Persistente<Livro> p = new Persistente<>();

        p.inserir(new Livro("a", "ficção", 20f, "autor", 1));
        boolean resultado = p.inserir(new Livro("b", "drama", 30f, "outro", 1));
        assertFalse(resultado);
        assertEquals(1, p.getEntidades().size()); 
    }
    
    //alterar
    @Test
    public void testeAlterarIdExistente() {
        Persistente<Livro> p = new Persistente<>();

        p.inserir(new Livro("a", "gênero", 20f, "autor", 1));
        boolean resultado = p.alterar(new Livro("alterado", "outro", 99f, "novo", 1));
        assertTrue(resultado);
        assertEquals("alterado", p.getEntidades().get(0).getNome());
        assertEquals("outro", p.getEntidades().get(0).getGenero());
    }

    @Test
    public void testeAlterarIdInexistente() {
        Persistente<Livro> p = new Persistente<>();

        boolean resultado = p.alterar(new Livro("nada", "x", 0f, "y", 99));
        assertFalse(resultado);
        assertEquals(0, p.getEntidades().size());
    }

    //excluir
    @Test
    public void testeExcluirIdExistente() {
        Persistente<Livro> p = new Persistente<>();

        p.inserir(new Livro("a", "g", 10f, "autor", 1));
        boolean resultado = p.excluir(1);

        assertTrue(resultado);
        assertEquals(0, p.getEntidades().size());
    }

    @Test
    public void testeExcluirIdInexistente() {
        Persistente<Livro> p = new Persistente<>();

        boolean resultado = p.excluir(55);
        assertFalse(resultado);
    }

    //buscar
    @Test
    public void testeBuscarIdExistente() throws IDNaoExistenteExeception {
        Persistente<Livro> p = new Persistente<>();

        Livro l = new Livro("a", "g", 20f, "autor", 1);
        p.inserir(l);
        Livro achado = p.buscar(1);
        assertEquals(l, achado);
    }

    @Test
    public void testeBuscarIdInexistente() {
        Persistente<Livro> p = new Persistente<>();

        assertThrows(
            IDNaoExistenteExeception.class,
            () -> p.buscar(10)
        );
    }
}