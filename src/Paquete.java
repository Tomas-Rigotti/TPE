public class Paquete {
    private int id;
    private String codPaquete;
    private int peso;
    private boolean contieneAlimentos;
    private int urgencia;

    public Paquete(int id, String codPaquete, int peso, boolean contieneAlimentos, int urgencia){
        this.id = id;
        this.codPaquete = codPaquete;
        this.peso = peso;
        this.contieneAlimentos = contieneAlimentos;
        this.urgencia = urgencia;
    }


    //Getters
    public int getId() {
        return id;
    }
    public String getCodPaquete() {
        return codPaquete;
    }
    public int getPeso() {
        return peso;
    }
    public boolean isContieneAlimentos() {
        return contieneAlimentos;
    }
    public int getUrgencia() {
        return urgencia;
    }

    public String toString(){
        return "\nID: " + id + "\nCodigo: " + codPaquete + "\nPeso: " + peso + "\nTiene alimentos: " + contieneAlimentos +
        "\nUrgencia: " + urgencia + "\n";
    }
}
