package Entity;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class User {

    private String name;
    private String username;
    private String email;
    private String password;
    private String DeviceId;
    private int UserId;

    public User(int UserId, String name, String username, String email, String password, String DeviceId) {
        this.UserId = UserId;
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.DeviceId = DeviceId;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public int getUserId() {
        return UserId;
    }

    public void editAccount(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getSavedDeviceId() {
        return DeviceId;
    }

    public static String getDeviceId() {
        String deviceid = "";
        try {
            String data[] = new String[10];
            String com[] = {"CMD", "/C", "WMIC CPU GET ProcessorId"};
            Process process = Runtime.getRuntime().exec(com);
            process.getOutputStream().close();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String s;
            while ((s = reader.readLine()) != null) {
                deviceid += s;
            }
            data = deviceid.split("   ");
            for (int i = 1; i < 10; i++) {
                if (data[i].length() > 5) {
                    deviceid = data[i];
                    break;
                }
            }
            deviceid = deviceid.trim();

        } catch (Exception ex) {
            deviceid = "1";
        }
        return deviceid;
    }

}
