package co.edu.uptc.config;

import co.edu.uptc.configlib.PropertiesLoader;

public class AppConfig {
    private static AppConfig instance;
    private final PropertiesLoader config = new PropertiesLoader();

    private AppConfig() {
        config.load("config/config.properties");
    }

    public static AppConfig getInstance() {
        if (instance == null) {
            instance = new AppConfig();
        }
        return instance;
    }

    public String get(String key) {
        return config.get(key);
    }

    public int getInt(String key, int defaultValue) {
        return parseIntOrDefault(config.get(key), defaultValue);
    }

    public double getDouble(String key, double defaultValue) {
        return parseDoubleOrDefault(config.get(key), defaultValue);
    }

    private int parseIntOrDefault(String value, int defaultValue) {
        try {
            return Integer.parseInt(value);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    private double parseDoubleOrDefault(String value, double defaultValue) {
        try {
            return Double.parseDouble(value);
        } catch (Exception e) {
            return defaultValue;
        }
    }
}
