package startProject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class Weather{
    
    // Replace with your own API key from OpenWeatherMap
    private static final String API_KEY = "your_api_key_here";
    private static final String LOCATION = "your_city_here"; // e.g., "London"
    private static final String UNITS = "metric"; // Use "imperial" for Fahrenheit

    public static void main(String[] args) {
        try {
            String response = getWeatherData(LOCATION, API_KEY, UNITS);
            JSONObject json = new JSONObject(response);
            double temp = json.getJSONObject("main").getDouble("temp");
            System.out.println("Current temperature in " + LOCATION + ": " + temp + "°C");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getWeatherData(String location, String apiKey, String units) throws Exception {
        String urlString = String.format("http://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s&units=%s", location, apiKey, units);
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }

        in.close();
        conn.disconnect();
        
        return content.toString();
    }
}

