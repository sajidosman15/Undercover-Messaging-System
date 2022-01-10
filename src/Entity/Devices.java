package Entity;

public class Devices {

    private String deviceId;
    private String LogInTime;

    public Devices(String deviceId, String LogInTime) {
        this.deviceId = deviceId;
        this.LogInTime = LogInTime;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public String getLogInTime() {
        return LogInTime;
    }

}
