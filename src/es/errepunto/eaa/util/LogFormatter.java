package es.errepunto.eaa.util;

import java.util.logging.Formatter;
import java.util.logging.LogRecord;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Formato personalizado de log. Muestra la fecha y hora, el nivel de log y el mensaje.
 * @author rcorral
 *
 */
public class LogFormatter extends Formatter {
	/**
	 * Formato de fecha internacional: "yyyy/MM/dd HH:mm:ss.sss"
	 */
	public final static DateTimeFormatter LOG_DATE = DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss.sss"); 

	@Override
	public String format(LogRecord record) {
		StringBuffer sb = new StringBuffer();
		
		sb.append(LOG_DATE.print(record.getMillis())).append(" ");
		sb.append("[").append(record.getLevel().getName()).append("] ");
		sb.append(record.getMessage());
		sb.append("\n");
		
		return sb.toString();
	}
}
