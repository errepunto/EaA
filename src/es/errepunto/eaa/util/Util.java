package es.errepunto.eaa.util;

/**
 * "Cajón de sastre" con utilidades de todo tipo usadas a lo largo y ancho de la
 * aplicación. Se puede ejecutar por separado para testear su funcionamiento.
 * 
 * @author rcorral
 *
 */
public class Util {
	
	/**
	 * Rellena una cadena de texto con el filler por la izquierda. Si la cadena
	 * es más corta, eliminará caracteres por la izquierda de la cadena original.
	 * 
	 * @param str Cadena original
	 * @param filler Cadena de relleno. La repita múltiples veces hasta conseguir
	 * la longitud deseada
	 * @param len Longitud exacta del String devuelto
	 * @return Cadena de texto con la longitud indicada y con caracteres de relleno
	 * por la izquierda.
	 */
	public static String fillLeft(String str, String filler, int len) {
		String ret = str;
		
		//Si la cadena es más larga que el máximo, quita caracteres por la izquierda
		if (str.length() > len) {
			ret = str.substring(str.length()-len);
		//Si la cadena es más corta, genera un relleno y lo añade a la izquierda
		} else {
			StringBuilder sb = new StringBuilder();
			
			int diff = len - str.length();
			
			for (int i = 0; i < diff; i += filler.length()) {
				sb.append(filler);
			}
			
			ret = sb.substring(0, diff) + str;
		}
		return ret;
	}
	
	
	/**
	 * Rellena una cadena de texto con el filler por la derecha. Si la cadena
	 * es más corta, eliminará caracteres por la derecha de la cadena original.
	 * 
	 * @param str Cadena original
	 * @param filler Cadena de relleno. La repita múltiples veces hasta conseguir
	 * la longitud deseada
	 * @param len Longitud exacta del String devuelto
	 * @return Cadena de texto con la longitud indicada y con caracteres de relleno
	 * por la derecha.
	 */
	public static String fillRight(String str, String filler, int len) {
		String ret = str;
		
		//Si la cadena es más larga que el máximo, quita caracteres por la izquierda
		if (str.length() > len) {
			ret = str.substring(0, len);
		//Si la cadena es más corta, genera un relleno y lo añade a la izquierda
		} else {
			StringBuilder sb = new StringBuilder();
			
			int diff = len - str.length();
			
			for (int i = 0; i < diff; i += filler.length()) {
				sb.append(filler);
			}
			
			ret = str+sb.substring(0, diff);
		}
		return ret;
	}
	
	
	public static void main(String[] args) {
		String str = "Hola boniato";
		
		System.out.println("fillLeft(\""+str+"\", \"abc\", 20)"+fillLeft(str, "abc", 20));
		System.out.println("fillLeft(\""+str+"\", \"abc\", 5)"+fillLeft(str, "abc", 5));
		
		System.out.println("fillRight(\""+str+"\", \"abc\", 20)"+fillRight(str, "abc", 20));
		System.out.println("fillRight(\""+str+"\", \"abc\", 5)"+fillRight(str, "abc", 5));
	}
}
