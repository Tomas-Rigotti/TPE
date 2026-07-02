import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class Greedy {
    private List<Camion> camiones;
    private List<Paquete> paquetes;
    private int[] asignacion;
    private int[] capacidadRestante;
    private int pesoSinAsignar;
    private int candidatosConsiderados;

    public Greedy(Map<Integer, Camion> camiones, Map<String, Paquete> paquetes){
        this.camiones = new ArrayList<>(camiones.values());
        this.paquetes = new ArrayList<>(paquetes.values());
        this.asignacion = new int[this.paquetes.size()];
        this.capacidadRestante = new int[this.camiones.size()];
        this.pesoSinAsignar = 0;
        this.candidatosConsiderados = 0;

        Arrays.fill(asignacion, -1);

        for(int i = 0; i < this.camiones.size(); i++){
            capacidadRestante[i]  = this.camiones.get(i).getCapacidad();
        }
    }

    /*
    Ordeno paquetes de mayor a menor peso asi priorizo los que mas peso dejan sin asignar si no puedo asignarlos
    a ningun camion. Se elige para cada paquete el camion con menor capacidad restante que pueda contenerlo,
    asi queda menos espacio desperdiciado y mas espacio en los camiones para otros paquetes. A diferencia de backtracking,
    greedy no garantiza la solucion optima, como se ve en los resultados
    */
    public void resolver(){
        //Ordeno paquetes por peso de mayor a menor
        paquetes.sort(Comparator.comparingInt(Paquete::getPeso).reversed());

        for(int i = 0; i < paquetes.size(); i++){
            Paquete paquete = paquetes.get(i);
            int mejorCamion = -1;
            int menorCapacidad = Integer.MAX_VALUE;

            //Busco el mejor camion
            for(int j = 0; j  < camiones.size(); j++){
                candidatosConsiderados++;
                Camion camion = camiones.get(j);
                if(esAsignacionValida(paquete, camion, j)){
                    if(capacidadRestante[j] < menorCapacidad){
                        menorCapacidad = capacidadRestante[j];
                        mejorCamion = j;
                    }
                }
            }
            if(mejorCamion != -1){
                asignacion[i] = mejorCamion;
                capacidadRestante[mejorCamion] -= paquete.getPeso();
            }else{
                pesoSinAsignar += paquete.getPeso();
            }
        }
        imprimirSolucion();
    }

    private boolean esAsignacionValida(Paquete paquete, Camion camion, int indiceCamion){
        if(capacidadRestante[indiceCamion] < paquete.getPeso()){
            return false;
        }
        if(paquete.isContieneAlimentos() && !camion.isEstaRefrigerado()){
            return false;
        }
        return true;
    }

    private void imprimirSolucion(){
        System.out.println("Greedy");
        for (int i = 0; i < paquetes.size(); i++) {
            if (asignacion[i] == -1) {
                System.out.println(paquetes.get(i).getCodPaquete() + " -> Sin asignar");
            } else {
                System.out.println(paquetes.get(i).getCodPaquete()
                    + ": A Camion con patente-> " + camiones.get(asignacion[i]).getPatente());
            }
        }
        System.out.println("Peso sin asignar: " + pesoSinAsignar + " kg");
        System.out.println("Candidatos considerados: " + candidatosConsiderados);
    }
}
