package cz.engeto.genesisResources.settings;

import java.net.URL;

public class Settings {
    private static final String PERSONID = "resources/personId.txt";
    private Settings(){
    }

    public static String getPersonid() {
        URL resource = Settings.class.getClassLoader().getResource("personId.txt");
        if (resource != null) {
            return resource.getPath();
        } else {
            throw new RuntimeException("Soubor personId.txt nebyl nalezen.");
        }
    }
}
