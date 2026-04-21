public class FogNode {
    private final CloudServer CLOUDSERVER;
    private int alertCount;
    private int id;

    public FogNode (CloudServer cloudServer, int id){
        this.CLOUDSERVER = cloudServer;
        this.id = id;
    }
    public int getId() {
        return id;
    }

    public void processData(SensorData data){
        
        System.out.println("[FOG] Dato recibido: " + data);

        if(data.getTEMPERATURE()>30){
            alertCount++;
            System.out.println("[FOG] ALERTA: temperatura alta");
        } else
            System.out.println("[FOG] Temperatura normal");

            CLOUDSERVER.saveData(data);
    } 


    public int getAlertCount(){
        return alertCount;
    }

}