import java.util.ArrayList;
import java.util.List;

public class FogNode {

    private final CloudServer CLOUDSERVER;
    private int alertCount;
    private int id;

    private List<SensorData> buffer = new ArrayList<>();

    public FogNode (CloudServer cloudServer, int id){
        this.CLOUDSERVER = cloudServer;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public CloudServer getCloudServer() {
        return CLOUDSERVER;
    }

    public int getAlertCount(){
        return alertCount;
    }

    public void processData(SensorData data){
        
        System.out.println("[FOG " + id + "] Dato recibido: " + data);

        if(data.getTEMPERATURE() > 30){
            alertCount++;
            CLOUDSERVER.incrementAlerts();
            System.out.println("[FOG] ALERTA: temperatura alta");
        } else {
            System.out.println("[FOG] Temperatura normal");
        }

        buffer.add(data);

        // enviar de 5 en 5
        if(buffer.size() == 5){
            sendToCloud();
        }
    }

    private void sendToCloud(){
        System.out.println("📤 Fog " + id + " envía 5 datos al Cloud");

        for(SensorData d : buffer){
            CLOUDSERVER.saveData(d);
        }

        buffer.clear();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Para enviar lo que quede (<5)
    public void flushBuffer(){
        if(!buffer.isEmpty()){
            sendToCloud();
        }
    }
}
