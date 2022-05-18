public class Forecast {
    private double tempF;
    private double tempC;
    private String currentCondition;
    private String currentIcon;

    public Forecast(double tempF, double tempC, String currentCondition, String currentIcon)
    {
        this.tempF = tempF;
        this.tempC = tempC;
        this.currentCondition = currentCondition;
        this.currentIcon = currentIcon;
    }

    private double getTempF()
    {
        return tempF;
    }

    private double getTempC()
    {
        return tempC;
    }

    private String getCurrentCondition()
    {
        return currentCondition;
    }

    private String getCurrentIcon()
    {
        return currentIcon;
    }

}
