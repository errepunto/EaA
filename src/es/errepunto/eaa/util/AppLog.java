package es.errepunto.eaa.util;

import java.io.File;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.joda.time.format.DateTimeFormat;

/**
 * Sistema de logging. Esta clase se encarga de la configuración incial y de enviar
 * los mensajes al sistema de log.
 *
 * @author ruben
 */
public class AppLog {
	private static Logger logger = Logger.getLogger("app");
	private static boolean started = false;
	
	
	/**
	 * Configura el sistema de log y lo deja preparado para su uso. Sólo debería
	 * invocarse al arrancar la aplicación.
	 * 
	 * Como no se puede asegurar que el log esté disponible, todos los mensajes
	 * se muestran por la salida estandar y la salida de error (consola)
	 * 
	 * @param path Ruta donde se guardarán los ficheros de log
	 * @param file Formato del nombre del fichero de log
	 * @param level Nivel de log
	 * @return <code>true</code> si se puede inicializar sin problema y <code>false</code>
	 * si ocurre algún error
	 */
	public static boolean startLogger(String path, String file, String level) {
		boolean ret = false;
		
		String fecha = DateTimeFormat.forPattern(file).print(System.currentTimeMillis());
		String logFile = path + File.separator + fecha + "_%g.log";
		System.out.println("Iniciando fichero de log: "+logFile);
		
		Formatter formatter = new LogFormatter();
		
		try {
			FileHandler fh = new FileHandler(logFile);
			fh.setFormatter(formatter);
			logger.addHandler(fh);
			
			started = true;
		} catch (Exception e) {
			System.err.println("Ha ocurrido un error al iniciar el sistema de log: "+e.getLocalizedMessage());
			e.printStackTrace();
		}
		
		return ret;
	}
	
	
	/**
	 * 
	 * @return Devuelve <code>true</code> si se ha inicializado el log correctamente.
	 */
	public static boolean isStarted() {
		return started;
	}
    
    public static void info(String msg) {
        logger.info(msg);
    }
    
    public static void info(String id, String msg) {
        logger.info("["+Util.fillLeft(msg, " ", 10)+"]"+msg);
    }
    
    public static void fine(String msg) {
        logger.fine("["+Util.fillLeft(msg, " ", 10)+"]"+msg);
    }
    
    public static void finest(String msg) {
        logger.finest("["+Util.fillLeft(msg, " ", 10)+"]"+msg);
    }
    
    public static void warning(String msg) {
        logger.warning("["+Util.fillLeft(msg, " ", 10)+"]"+msg);
    }
    
    public static void exception(String msg) {
        logger.severe("["+Util.fillLeft(msg, " ", 10)+"]"+msg);
    }
    
    public static void warning(String msg, Throwable t) {
        logger.log(Level.WARNING, msg, t);
    }
    
    public static void exception(String msg, Throwable t) {
        logger.log(Level.SEVERE, msg, t);
    }
    
    public static void warning(String id, String msg, Throwable t) {
        logger.log(Level.WARNING, "["+Util.fillLeft(msg, " ", 10)+"]"+msg, t);
    }
    
    public static void exception(String id, String msg, Throwable t) {
        logger.log(Level.SEVERE, "["+Util.fillLeft(msg, " ", 10)+"]"+msg, t);
    }
}
