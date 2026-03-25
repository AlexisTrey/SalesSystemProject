package co.edu.uptc.config;

import co.edu.uptc.configlib.PropertiesLoader;

public class I18n {
    private static I18n instance;
    private final PropertiesLoader config = new PropertiesLoader();

    private I18n() {
        String lang = getLanguage();
        config.load("i18n/messages_" + lang + ".properties");
    }

    public static I18n getInstance() {
        if (instance == null) {
            instance = new I18n();
        }
        return instance;
    }

    public String get(String key) {
        String value = config.get(key);
        return (value != null) ? value : key;
    }

    private String getLanguage() {
        String lang = AppConfig.getInstance().get("app.language");
        return (lang != null) ? lang : "es";
    }
}
