public class WeatherAPI {

    private WeatherNetworking networker;

    public WeatherAPI() {
        this.networker = new WeatherNetworking();
    }

    public void getForecast(String zipCode, int days)
    {
        String response = networker.makeAPICallForForecast(zipCode, days);
        networker.parseForecast(response);
    }

    public void getCurrent(String zipCode)
    {
        String response = networker.makeAPICallForCurrentWeather(zipCode);
        networker.parseCurrent(response);
    }
}