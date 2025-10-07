package persistencia;

import java.util.ArrayList;
import java.util.List;
import modelo.Entidade;

public class Persistente {
    private List<Entidade> entidades;

    public Persistente(){
        this.entidades = new ArrayList<>();
    }

    public void inserir(Entidade r) {
        entidades.add(r);
    }

    public boolean alterar(Entidade r) {
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

    public Entidade buscar(int id) {
        for (Entidade r : entidades) {
            if (r.getId() == id) {
                return r;
            }
        }
        return null; 
    }
    public boolean idExiste(int id) {
        return buscar(id) != null;
    }

    public List<Entidade> getEntidades() {
        return entidades;
    }

    public void setEntidades(List<Entidade> entidades) {
        this.entidades = entidades;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Entidade r : entidades) {
            s.append(r.toString()).append("\n");
        }
        return s.toString();
    }
}