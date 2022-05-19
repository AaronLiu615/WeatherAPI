public class WeatherAPI {

    private WeatherNetworking networker;

    public WeatherAPI() {
        this.networker = new WeatherNetworking();
    }

    public void getCurrent(String zipCode)
    {
        String response = networker.makeAPICallForCurrentWeather(zipCode);
        networker.parseCurrent(response);
    }
}