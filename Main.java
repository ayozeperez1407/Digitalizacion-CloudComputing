import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        final int NUM_NODES = 5;

        Scanner sc = new Scanner(System.in);
        int numLecturas;

        CloudServer cloudServer = new CloudServer();
        FogNode[] fogNodes = new FogNode[NUM_NODES];
        EdgeNode[][] edgeNodes = new EdgeNode[NUM_NODES][NUM_NODES];

        // Crear nodos
        for(int i = 0; i < NUM_NODES; i++){
            fogNodes[i] = new FogNode(cloudServer, i+1);

            for(int j = 0; j < NUM_NODES; j++){
                edgeNodes[i][j] = new EdgeNode(fogNodes[i], j+1);
            }
        }

        System.out.println("==== SIMULACION CLOUD - FOG - EDGE ====\n");
        System.out.print("Introduce el número de lecturas por cada Edge: ");
        numLecturas = sc.nextInt();

        // Envío de datos
        for (int i = 0; i < NUM_NODES; i++) {
            for (int j = 0; j < NUM_NODES; j++) {
                edgeNodes[i][j].sendData(numLecturas);
            }
        }

        // Enviar datos restantes (<5)
        for (int i = 0; i < NUM_NODES; i++) {
            fogNodes[i].flushBuffer();
        }

        // Mostrar resultados
        cloudServer.showData();

        System.out.println("\n=== ALERTAS POR FOG ===");
        for (int i = 0; i < NUM_NODES; i++) {
            System.out.println("Fog " + fogNodes[i].getId() +
                    " → Alertas: " + fogNodes[i].getAlertCount());
        }

        sc.close();
    }
}
