package utils;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static final Properties props = new Properties();

    static {
        try (InputStream is = ConfigReader.class.getClassLoader().getResourceAsStream("config.properties")) {
            props.load(is);
        } catch (Exception e) {
            throw new RuntimeException("Could not load config.properties", e);
        }
    }

    /** System property (-Dkey=value) wins over the file. */
    public static String get(String key) {
        String sys = System.getProperty(key);
        return (sys != null && !sys.isBlank()) ? sys : props.getProperty(key);
    }

    public static int getInt(String key) {
        return Integer.parseInt(get(key));
    }

    public static boolean getBoolean(String key) {
        return Boolean.parseBoolean(get(key));
    }
}
