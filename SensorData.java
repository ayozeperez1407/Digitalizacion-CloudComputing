public class SensorData {

    private final String DEVICEID;
    private final double TEMPERATURE;

    public SensorData(String deviceId, double temperature){
        this.DEVICEID = deviceId;
        this.TEMPERATURE = temperature;
    }

    public String getDEVICEID(){
        return DEVICEID;
    }

    public double getTEMPERATURE(){
        return TEMPERATURE;
    }

    @Override
    public String toString(){
        return "Device: " + DEVICEID +
               " Temp: " + String.format("%.2f", TEMPERATURE);
    }
}
