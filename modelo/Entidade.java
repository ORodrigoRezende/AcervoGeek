package modelo;

public abstract class Entidade{
    protected int id;

    public Entidade(){
        super(); //Coloquei pq na lista de ex dele ele colocou ;-;
        this.id = 0;
    }
    public Entidade(int id){
        super();
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String toString() {
        return "Id=" + id + "\n";
    }
}
