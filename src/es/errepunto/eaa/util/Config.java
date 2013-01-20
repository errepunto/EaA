package es.errepunto.eaa.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Esta clase gestiona la carga del fichero de propiedades y permite obtener
 * las propiedades almacenadas en el mismo. Aunque un fichero de propiedades sólo
 * puede almacenar cadenas de texto, se proporcionan varios métodos que permiten
 * la conversión automática de cadena de texto a tipos básicos.
 * 
 * Además, casi todos los métodos de obtención de propiedades soportan un parámetro
 * con un valor por defecto, por si la propiedad no está definida.
 * 
 * @author rcorral
 *
 */
public class Config {

	protected static File propFile;
	protected static boolean loaded = false;
	protected static Properties properties;
	
	
	/**
	 * Establece el fichero de propiedades y las carga. Este método sólo debería
	 * ser llamado al arrancar la aplicación.
	 * 
	 * @param file
	 * @throws IOException
	 */
	public static void setFile(File file) throws IOException {
        loaded = false;
		if (file.isFile() && file.canRead()) {
			propFile = file;
		} else {
			System.out.println("Directorio local: "+(new File(".").getAbsolutePath()));
			throw new IOException("No se pudo leer el fichero de propiedades");
		}
        
        //Carga las propiedades desde el fichero
        readProperties();
	}
	
	
	/**
	 * Lee el fichero de propiedades y almacena las propiedades en un hashmap de
	 * texto. Si todo va bien devuelve un <code>true</code>, si ocurre algún error,
	 * devuelve <code>false</code>.
	 * 
	 * Este método podría ser llamado a lo largo de la vida de la aplicación para
	 * refrescar el fichero de propiedades si hubiera cambios. No obstante, lo normal
	 * es que únicamente lo llame una vez de forma implícita el método <code>setFile()</code>
	 * 
	 * @return <code>true</code> si no hay errores y <code>false</code> en caso contrario
	 */
	public static boolean readProperties() {
		boolean ret = false;
        loaded = false;
		
		properties = new Properties();
		
		FileInputStream fis;
		try {
			fis = new FileInputStream(propFile);
			properties.load(fis);
			ret = true;
			loaded = true;
		} catch (FileNotFoundException e) {
			System.out.println("No se ha encontrado el fichero de propiedades");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("No se ha podido leer el fichero de propiedades");
			e.printStackTrace();
		}
		
		return ret;
	}
	
    
    /**
     * Obtiene el fichero de propiedades actualmente cargado.
     * 
     * @return Fichero con las propiedades.
     */
    public File getPropFile() {
        return propFile;
    }
	
    
    /**
	 * Obtiene el valor de la propiedad indicada. Si no se han cargado aún las propiedades,
	 * realiza la operación de carga. Si se produce algún error o si no existe la
	 * clave, devuelve el valor por defecto
	 * 
	 * @param propertyName Nombre de la propiedad a obtener
	 * @param def Valor por defecto
	 * @return Valor de la propiedad o valor por defecto
	 */
	public static String get(String propertyName, String def) {
		String ret = def;
		
		try{
			if (!loaded) {
				readProperties();
			}
			
			if (properties.containsKey(propertyName)) {
				ret = properties.getProperty(propertyName);
			}
		} catch (Exception e) {
			
		}
		
		return ret;
	}
    
    
	/**
	 * Obtiene el valor de la propiedad indicada. Si no se han cargado aún las propiedades,
	 * realiza la operación de carga.
	 * 
	 * @param propertyName Nombre de la propiedad a obtener
	 * @return Valor de la propiedad
	 */
	public static String get(String propertyName) {
		return get(propertyName, null);
	}
	
	
	/**
	 * Obtiene el valor de una propiedad String. Es exactamente igual al método <code>get()</code>
	 * @param propertyName Nombre de la propiedad
	 * @return Valor de la propiedad o false en caso de error
	 */
	public static String getString(String propertyName, String def) {
		return get(propertyName, def);
	}
	
	
	/**
	 * Obtiene el valor de una propiedad String. Es exactamente igual al método <code>get()</code>
	 * @param propertyName
	 * @return
	 */
	public static String getString(String propertyName) {
		return get(propertyName);
	}
	
	
	/**
	 * Obtiene una propiedad con un valor de tipo boolean.
	 * @param propertyName Nombre de la propiedad
	 * @param def Valor por defecto
	 * @return Valor de la propiedad o false en caso de error
	 */
	public static boolean getBool(String propertyName, boolean def) {
		boolean ret = def;
		
		String str = get(propertyName);
		if (str != null) {
			ret = Boolean.parseBoolean(str);
		}
		
		return ret;
	}
	
	
	/**
	 * Obtiene una propiedad con un valor de tipo boolean.
	 * @param propertyName Valor de la propiedad o false en caso de error
	 * @return
	 */
	public static boolean getBool(String propertyName) {
		return getBool(propertyName, false);
	}
	
	
	/**
	 * Obtiene una propiedad con un valor de tipo int.
	 * @param propertyName Nombre de la propiedad
	 * @param def Valor por defecto
	 * @return Valor de la propiedad o -1 en caso de error
	 */
	public static int getInt(String propertyName, int def) {
		int ret = def;
		
		String str = get(propertyName);
		if (str != null) {
			ret = Integer.parseInt(str, 10);
		}
		
		return ret;
	}
	
	
	/**
	 * Obtiene una propiedad con un valor de tipo int.
	 * @param propertyName Nombre de la propiedad
	 * @return Valor de la propiedad o -1 en caso de error
	 */
	public static int getInt(String propertyName) {
		return getInt(propertyName, -1);
	}
	
	
	/**
	 * Obtiene una propiedad con un valor de tipo long.
	 * @param propertyName Nombre de la propiedad
	 * @param def valor por defecto
	 * @return Valor de la propiedad o -1 en caso de error
	 */
	public static long getLong(String propertyName, long def) {
		long ret = def;
		
		String str = get(propertyName);
		if (str != null) {
			ret = Long.parseLong(str, 10);
		}
		
		return ret;
	}
	
	
	/**
	 * Obtiene una propiedad con un valor de tipo long.
	 * @param propertyName Nombre de la propiedad
	 * @return Valor de la propiedad o -1 en caso de error
	 */
	public static long getLong(String propertyName) {
		return getLong(propertyName, -1L);
	}
	
	
	/**
	 * Obtiene una propiedad con un valor de tipo float.
	 * @param propertyName Nombre de la propiedad
	 * @param def Valor por defecto
	 * @return Valor de la propiedad o -1 en caso de error
	 */
	public static float getFloat(String propertyName, float def) {
		float ret = def;
		
		String str = get(propertyName);
		if (str != null) {
			ret = Float.parseFloat(str);
		}
		
		return ret;
	}
	
	
	/**
	 * Obtiene una propiedad con un valor de tipo float.
	 * @param propertyName Nombre de la propiedad
	 * @return Valor de la propiedad o -1 en caso de error
	 */
	public static float getFloat(String propertyName) {
		return getFloat(propertyName, -1f);
	}
	
	
	/**
	 * Obtiene una propiedad con un valor de tipo double.
	 * @param propertyName Nombre de la propiedad
	 * @param def Valor por defecto
	 * @return Valor de la propiedad o -1 en caso de error
	 */
	public static double getDouble(String propertyName, double def) {
		double ret = def;
		
		String str = get(propertyName);
		if (str != null) {
			ret = Double.parseDouble(str);
		}
		
		return ret;
	}
	
	
	/**
	 * Obtiene una propiedad con un valor de tipo double.
	 * @param propertyName Nombre de la propiedad
	 * @return Valor de la propiedad o -1 en caso de error
	 */
	public static double getDouble(String propertyName) {
		return getDouble(propertyName, -1d);
	}
}
