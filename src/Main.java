public class Main {
    public static void main(String[] args) {

        Servicios servicios = new Servicios("csv\\Camiones.csv", "csv\\Paquetes.csv");

        //Servicio 1
        //System.out.println(servicios.servicio1("P002"));

        //Servicio 2
        //System.out.println(servicios.servicio2(false));

        //Servicio 3 
        //System.out.println(servicios.servicio3(0, 10));
        
        Backtracking bt = new Backtracking(servicios.getCamiones(), servicios.getPaquetes());

        bt.resolver();

        Greedy gr = new Greedy(servicios.getCamiones(), servicios.getPaquetes());

        gr.resolver();

        
    }
}
