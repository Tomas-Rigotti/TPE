import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Backtracking {
    private List<Camion> camiones;
    private List<Paquete> paquetes;

    private int[] cargaActual;
    private int[] mejorAsignacion;
    private int[] asignacionActual;
    private int mejorPesoSinAsignar;
    private int estadosGenerados;

    public Backtracking(Map<Integer, Camion> camiones, Map<String, Paquete> paquetes){
        this.camiones = new ArrayList<>(camiones.values());
        this.paquetes = new ArrayList<>(paquetes.values());
        this.cargaActual = new int[this.camiones.size()];
        this.asignacionActual = new int[this.paquetes.size()];
        this.mejorAsignacion = new int[this.paquetes.size()];
        Arrays.fill(asignacionActual, -1);
        Arrays.fill(mejorAsignacion, -1);
        this.mejorPesoSinAsignar = Integer.MAX_VALUE;
        this.estadosGenerados = 0;
    }

    public void resolver() {
        backtracking(0);
        imprimirSolucion();
    }

    /*
     Se recorren todos los paquetes, a cada uno se lo intenta asignar a un camion
     que cumpla con las restricciones, o se deja sin asignar si no se puede.
     Se exploran todas las combinaciones y se conserva la que minimiza el peso de paquetes sin asignar.
     Se hacen podas cuando:
     -El peso sin asignar es mayor o igual a la mejor solucion encontrada
     -Si la mejor solucion tiene peso sin asignar igual a 0
    */
    public void backtracking(int indicePaquete) {

        if (mejorPesoSinAsignar == 0){
            return;
        }

        if (indicePaquete == paquetes.size()) {
            estadosGenerados++;
            int pesoSinAsignar = calcularPesoSinAsignar();
            if (pesoSinAsignar < mejorPesoSinAsignar) {
                mejorPesoSinAsignar = pesoSinAsignar;
                mejorAsignacion = Arrays.copyOf(asignacionActual, asignacionActual.length);
            }
            return;
        }
        
        int pesoActualSinAsignar = calcularPesoSinAsignar();
        if (pesoActualSinAsignar >= mejorPesoSinAsignar){
            return;
        }

        Paquete paquete = paquetes.get(indicePaquete);
        for (int i = 0; i < camiones.size(); i++) {
            estadosGenerados++;
            Camion camion = camiones.get(i);
            if (esAsignacionValida(paquete, camion, i)) {
                asignacionActual[indicePaquete] = i;
                cargaActual[i] += paquete.getPeso();

                backtracking(indicePaquete + 1);

                asignacionActual[indicePaquete] = -1;
                cargaActual[i] -= paquete.getPeso();
                }
        }
        //Si el paquete no se puede asignar
        estadosGenerados++;
        asignacionActual[indicePaquete] = -1;
        backtracking(indicePaquete + 1);
    }


    private boolean esAsignacionValida(Paquete paquete, Camion camion, int indiceCamion) {
        //Restriccion capacidad
        if (cargaActual[indiceCamion] + paquete.getPeso() > camion.getCapacidad()) {
            return false;
        }
        //Restriccion refrigerados
        if (paquete.isContieneAlimentos() && !camion.isEstaRefrigerado()) {
            return false;
        }
        return true;
    }

    private int calcularPesoSinAsignar() {
        int peso = 0;
        for (int i = 0; i < paquetes.size(); i++) {
            if (asignacionActual[i] == -1) {
                peso += paquetes.get(i).getPeso();
            }
        }
        return peso;
    }

    private void imprimirSolucion() {
        System.out.println("Backtracking");
        for (int i = 0; i < paquetes.size(); i++) {
            if (mejorAsignacion[i] == -1) {
                System.out.println(paquetes.get(i).getCodPaquete() + " -> Sin asignar");
            } else {
                System.out.println("Paquete " + paquetes.get(i).getCodPaquete() 
                    + ": A Camion con patente-> " + camiones.get(mejorAsignacion[i]).getPatente());
            }
        }
        System.out.println("Peso sin asignar: " + mejorPesoSinAsignar + " kg");
        System.out.println("Estados generados: " + estadosGenerados);
    }


    public int[] getMejorAsignacion() {
        return mejorAsignacion;
    }
    public List<Camion> getCamiones() {
        return camiones;
    }
    public List<Paquete> getPaquetes() {
        return paquetes;
    }
    public int getMejorPesoSinAsignar() {
        return mejorPesoSinAsignar;
    }
    public int getEstadosGenerados() {
        return estadosGenerados;
    }
}
