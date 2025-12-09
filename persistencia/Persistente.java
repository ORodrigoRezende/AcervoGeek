package persistencia;

import java.util.ArrayList;
import java.util.List;
import modelo.Entidade;

public class Persistente<T extends Entidade> {

    private List<T> entidades;

    public Persistente() {
        this.entidades = new ArrayList<>();
    }

    public boolean  inserir(T r) {
        if(idExiste(r.getId())) return false; 
        entidades.add(r);
        return true;
    }

    public boolean alterar(T r) {
        for (int i = 0; i < entidades.size(); i++) {
            if (entidades.get(i).getId() == r.getId()) {
                entidades.set(i, r);
                return true;
            }
        }
        return false;
    }

    public boolean excluir(int id) {
        return entidades.removeIf(r -> r.getId() == id);
    }

    public T buscar(int id) throws IDNaoExistenteExeception{
        for (T r : entidades) {
            if (r.getId() == id) {
                return r;
            }
        }

        throw new IDNaoExistenteExeception("ID " + id + " não existe.");
    }

    public boolean idExiste(int id) {
        for (T r : entidades) {
            if (r.getId() == id) {
                return true;
            }
        }
        return false;
    }

    public List<T> getEntidades() {
        return entidades;
    }

    public void setEntidades(List<T> entidades) {
        this.entidades = entidades;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (T r : entidades) {
            s.append(r.toString()).append("\n");
        }
        return s.toString();
    }
}
