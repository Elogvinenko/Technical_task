package config;

/**
 * Created by elogvinenko on 26.04.17.
 */
public class Configuration {
    //All common data for all scenariops, for example basic url, is saved here
    private static String baseUrl = "http://sports.williamhill.com/betting/en-gb";
    public static String getBaseUrl() {
        return baseUrl;
    }
}
