package es.errepunto.eaa;

import java.io.File;
import java.io.IOException;

import es.errepunto.eaa.util.AppLog;
import es.errepunto.eaa.util.Config;

public class Main {
	public static String configFile = "config/app.properties";
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		File f = new File(configFile);
        try {
            Config.setFile(f);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    
		String version = Config.get("global.version");
		System.out.println("Version: "+version);
		
		// Inicia el sistema de login
		String path = Config.get("log.path");
		String file = Config.get("log.file");
		String level = Config.get("log.level");
		AppLog.startLogger(path, file, level);
		
		AppLog.info("Cargando aplicacion...");
	}

}
