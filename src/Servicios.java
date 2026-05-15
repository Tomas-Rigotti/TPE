import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Servicios {
    //Completar con las estructuras y métodos privados que se 
    //requieran. 
    private final Map<Integer, Camion> camiones;
    private final Map<String, Paquete> paquetes;
    private final List<Paquete> conAlimentos;
    private final List<Paquete> sinAlimentos;
 
    /* 
     * Complejidad Temporal: O(n) -n siendo la suma de paquetes y camiones
     */ 
    public Servicios(String pathCamiones, String pathPaquetes){ 
        camiones = new HashMap<>();
        paquetes = new HashMap<>();
        conAlimentos = new ArrayList<>();
        sinAlimentos = new ArrayList<>();

        try{
            cargarCamiones(pathCamiones);
            cargarPaquetes(pathPaquetes);
        }catch(IOException e){
            throw new RuntimeException("No se pudieron leer los archivos" + e.getMessage());
        }
    } 
 
    /* 
     * Complejidad temporal: O(1) -Porque se uso un HashMap para almacenarlos 
     */ 
    public Paquete servicio1(String codigoPaquete){ 
        //Si no existe retorna null automaticamente
        return paquetes.get(codigoPaquete);
    } 
 
    /* 
     * Complejidad temporal: O(1) -Porque se usaron listas para almacenar los paquetes dependiendo de si tenian alimentos.
     * Solo hay que retornar la lista ya armada
     */ 
    public List<Paquete> servicio2(boolean contieneAlimentos){ 
        if(contieneAlimentos){
            return conAlimentos;
        }else{
            return sinAlimentos;
        }
    } 
 
    /* 
     * Complejidad temporal: O(n) -n siendo la cantidad de paquetes en el HashMap 
     */ 
    public List<Paquete> servicio3(int urgenciaMinima, int urgenciaMaxima){ 
        List<Paquete> retorno = new ArrayList<>();
        for(Paquete p : paquetes.values()){
            int urgencia = p.getUrgencia();
            if(urgencia >= urgenciaMinima && urgencia <= urgenciaMaxima){
                retorno.add(p);
            }
        }
        return retorno;
    }

    private void cargarCamiones(String path) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            int total = Integer.parseInt(br.readLine().trim());
            for (int i = 0; i < total; i++) {
                String[] p = br.readLine().split(";");
                int     id              = Integer.parseInt(p[0]);
                String  patente         = p[1];
                boolean estaRefrigerado   = p[2].equals("1");
                int     capacidad       = Integer.parseInt(p[3]);
                camiones.put(id, new Camion(id, patente, estaRefrigerado, capacidad));
            }
        }
    }

    private void cargarPaquetes(String path) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            int total = Integer.parseInt(br.readLine().trim());
            for (int i = 0; i < total; i++) {
                String[] p   = br.readLine().split(";");
                int     id   = Integer.parseInt(p[0]);
                String  cod  = p[1];
                int     peso = Integer.parseInt(p[2]);
                boolean alimentos  = p[3].equals("1");
                int     urgencia  = Integer.parseInt(p[4]);

                Paquete paquete = new Paquete(id, cod, peso, alimentos, urgencia);

                paquetes.put(cod, paquete);
                
                if (alimentos){
                    conAlimentos.add(paquete);
                }else{
                    sinAlimentos.add(paquete);
                }     
            }
        }
    }
}
