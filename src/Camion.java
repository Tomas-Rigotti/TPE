public class Camion {
    private int id;
    private String patente;
    private boolean estaRefrigerado;
    private int capacidad;

    public Camion(int id, String patente, boolean estaRefrigerado, int capacidad){
        this.id = id;
        this.patente = patente;
        this.estaRefrigerado = estaRefrigerado;
        this.capacidad = capacidad;
    }

    //Getters
    public int getId() {
        return id;
    }
    public String getPatente() {
        return patente;
    }
    public boolean isEstaRefrigerado() {
        return estaRefrigerado;
    }
    public int getCapacidad() {
        return capacidad;
    }

    public String toString(){
        return "\nCamion: " + id + "\nPatente: " + patente + "\nEsta refrigerado: " + estaRefrigerado + "\nCapacidad: " + capacidad + "\n";
    }
}
