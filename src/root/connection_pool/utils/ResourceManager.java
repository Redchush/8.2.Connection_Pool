package root.connection_pool.utils;

import java.util.ResourceBundle;
import java.util.Set;


public enum ResourceManager {
    DATABASE("root.connection_pool.properties.database");

    private ResourceBundle resourceBundle;

    private ResourceManager(String string){
        resourceBundle = ResourceBundle.getBundle(string);
    }

    public String getString(String key){
        return resourceBundle.getString(key);
    }

    public Set<String> keySet() {
        return resourceBundle.keySet();
    }
}
