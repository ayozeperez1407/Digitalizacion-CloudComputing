import java.util.ArrayList;
import java.util.List;

public class CloudServer {

    private final List<SensorData> dataList = new ArrayList<>();
    private int totalAlerts = 0;

    public void saveData(SensorData data){
        dataList.add(data);
        System.out.println("[CLOUD] Dato guardado " + data);
    }

    public void incrementAlerts() {
        totalAlerts++;
    }

    public int getTotalAlerts() {
        return totalAlerts;
    }

    public void showData(){
        double sum = 0;

        System.out.println("\n[CLOUD] Historial de datos:");
        
        if(dataList.isEmpty()){
            System.out.println("No hay datos guardados");
            return;
        }

        for(SensorData data: dataList){
            System.out.println(data);
            sum += data.getTEMPERATURE();
        }

        double average = sum / dataList.size();

        System.out.println("Total datos: " + dataList.size());
        System.out.println("Media de temperatura: " + String.format("%.2f", average));
        System.out.println("Total alertas globales: " + totalAlerts);
    }
}
