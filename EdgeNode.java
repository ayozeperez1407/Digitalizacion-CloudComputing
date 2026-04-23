public class EdgeNode {

    private final FogNode fogNode;
    private int id;

    public EdgeNode(FogNode fogNode, int id){
        this.fogNode = fogNode;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void sendData(int totalReadings){

        for (int i = 1; i <= totalReadings; i++){

            // parar sistema global en 20 alertas
            if (fogNode.getCloudServer().getTotalAlerts() >= 20){
                System.out.println("⛔ Límite global de alertas alcanzado");
                break;
            }

            double temperature = 20 + Math.random() * 16;

            SensorData data = new SensorData(
                "sensor-F" + fogNode.getId() + "-E" + id,
                temperature
            );

            System.out.println("Fog " + fogNode.getId() +
                    " Edge " + id +
                    " → temperatura: " +
                    String.format("%.2f", temperature));

            fogNode.processData(data);

            System.out.println();
        }
    }
}
