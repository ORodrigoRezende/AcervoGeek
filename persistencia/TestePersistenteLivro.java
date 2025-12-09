package persistencia;

import modelo.Livro;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestePersistenteLivro {

    // -----------------------
    //        INSERIR
    // -----------------------

    @Test
    public void testInserirIdNovo() {
        Persistente<Livro> p = new Persistente<>();

        Livro l = new Livro("A", "Ficção", 20f, "Autor", 1);
        boolean resultado = p.inserir(l);

        assertTrue(resultado);
        assertEquals(l, p.getEntidades().get(0));
    }

    @Test
    public void testInserirIdExistente() {
        Persistente<Livro> p = new Persistente<>();

        p.inserir(new Livro("A", "Ficção", 20f, "Autor", 1));
        boolean resultado = p.inserir(new Livro("B", "Drama", 30f, "Outro", 1));

        assertFalse(resultado);
        assertEquals(1, p.getEntidades().size()); // não inseriu o novo
    }

    // -----------------------
    //        ALTERAR
    // -----------------------

    @Test
    public void testAlterarIdExistente() {
        Persistente<Livro> p = new Persistente<>();

        p.inserir(new Livro("A", "Gênero", 20f, "Autor", 1));

        boolean resultado = p.alterar(new Livro("Alterado", "Outro", 99f, "Novo Autor", 1));

        assertTrue(resultado);
        assertEquals("Alterado", p.getEntidades().get(0).getNome());
        assertEquals("Outro", p.getEntidades().get(0).getGenero());
    }

    @Test
    public void testAlterarIdInexistente() {
        Persistente<Livro> p = new Persistente<>();

        boolean resultado = p.alterar(new Livro("Nada", "X", 0f, "Y", 99));

        assertFalse(resultado);
        assertEquals(0, p.getEntidades().size());
    }

    // -----------------------
    //        EXCLUIR
    // -----------------------

    @Test
    public void testExcluirIdExistente() {
        Persistente<Livro> p = new Persistente<>();

        p.inserir(new Livro("A", "G", 10f, "Autor", 1));

        boolean resultado = p.excluir(1);

        assertTrue(resultado);
        assertEquals(0, p.getEntidades().size());
    }

    @Test
    public void testExcluirIdInexistente() {
        Persistente<Livro> p = new Persistente<>();

        boolean resultado = p.excluir(55);

        assertFalse(resultado);
    }

    // -----------------------
    //        BUSCAR
    // -----------------------

    @Test
    public void testBuscarIdExistente() throws IDNaoExistenteExeception {
        Persistente<Livro> p = new Persistente<>();

        Livro l = new Livro("A", "G", 20f, "Autor", 1);
        p.inserir(l);

        Livro achado = p.buscar(1);

        assertEquals(l, achado);
    }

    @Test
    public void testBuscarIdInexistente() {
        Persistente<Livro> p = new Persistente<>();

        assertThrows(
            IDNaoExistenteExeception.class,
            () -> p.buscar(10)
        );
    }
}
