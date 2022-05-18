import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class WeatherNetworking {

    private String baseUrl;
    private String apiKey;

    public WeatherNetworking() {
        baseUrl = "http://api.weatherapi.com/v1";
        apiKey = "d78d444b9e9a4285957164220221605";
    }

    public String makeAPICallForForecast(String zipCode, int days) {
        String endPoint = "/forecast.json";
        String url = baseUrl + endPoint + "?q=" + zipCode + "&key=" + apiKey + "&days=" + days;

        try {
            URI myUri = URI.create(url); // creates a URI object from the url string
            HttpRequest request = HttpRequest.newBuilder().uri(myUri).build();
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void parseForecast(String json) {
        JSONObject jsonObj = new JSONObject(json);
        JSONObject forecastObj = jsonObj.getJSONObject("forecast");
        JSONArray forecaseArr = forecastObj.getJSONArray("forecastday");

        for (int i = 0; i < forecaseArr.length(); i++) {
            JSONObject forecast = forecaseArr.getJSONObject(i);
            JSONObject dayObj = forecast.getJSONObject("day");
            double minTemp = dayObj.getDouble("mintemp_f");
            double maxTemp = dayObj.getDouble("maxtemp_f");
            System.out.println("Min temp: " + minTemp + ", Max temp = " + maxTemp);
        }
    }

    public String makeAPICallForCurrentWeather(String zipCode) {
        String endPoint = "/current.json";
        String url = baseUrl + endPoint + "?q=" + zipCode + "&key=" + apiKey;
        try {
            URI myUri = URI.create(url); // creates a URI object from the url string
            HttpRequest request = HttpRequest.newBuilder().uri(myUri).build();
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Forecast parseCurrent(String json) {
        JSONObject jsonObj = new JSONObject(json);
        JSONObject currentObj = jsonObj.getJSONObject("current");
        double tempF = currentObj.getDouble("temp_f");
        double tempC = currentObj.getDouble("temp_c");
        JSONObject conditionObj = currentObj.getJSONObject("condition");
        String currentCondition = conditionObj.getString("text");
        String currentIcon = conditionObj.getString("icon");
        Forecast weather = new Forecast(tempF, tempC, currentCondition, currentIcon);
        return weather;
    }


}
