package es.errepunto.eaa.events;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;

public class HttpEvent implements Event {
	
	protected URL url;
	protected Proxy proxy;
	protected String lastData;
	protected String err;
	protected long lastCheck;
	protected long activeUntil;
	protected long activeForMillis;
	
	
	/**
	 * Obtains an URL and remains active for "activeForMillis" milliseconds
	 * 
	 * @param url
	 * @param proxy
	 * @param activeForMillis
	 */
	public HttpEvent(URL url, Proxy proxy, long activeForMillis) {
		this.url = url;
		this.proxy = proxy;
		this.lastData = null;
		this.err = null;
		this.activeForMillis = activeForMillis;
	}
	
	
	/**
	 * Obtains an URL and remains active for 1 minute.
	 * @param url
	 */
	public HttpEvent(URL url) {
		//By default, active for 1 minute
		this(url, null, 1*60*1000);
	}
	
	
	/**
	 * Return if the event is active. The event will be active for "activeForMillis" milliseconds
	 * after the data is grabbed and processed.
	 */
	public boolean isActive() {
		boolean ret = false;
		
		this.lastCheck = System.currentTimeMillis();
		
		if (activeUntil <= lastCheck) {
			ret = true;
		}
		
		return ret;
	}

	
	/**
	 * Gets data from a server
	 * 
	 * @return Returns "true" if the data is obtained and processed succesfully and false if an error happens
	 */
	public boolean obtainData() {
		boolean ret = false;
		
		try {
			URLConnection conn;
			if (proxy == null) {
				conn = url.openConnection();
			} else {
				conn = url.openConnection(proxy);
			}
			
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			StringBuilder sb = new StringBuilder();
			String inputLine;
			while ((inputLine = in.readLine()) != null) { 
				sb.append(inputLine);
			}
			in.close();
			
			processData(sb.toString());
		}catch(IOException e) {
			err = "Error while opening url: "+e.getMessage();
		}
		
		return ret;
	}
	
	
	/**
	 * Returns last error code
	 */
	public String getLastError() {
		return err;
	}
	
	
	/**
	 * Process the obtained data
	 * 
	 * @param rawData Raw data from server
	 */
	protected void processData(String rawData) {
		this.lastData = rawData;
		this.activeUntil = System.currentTimeMillis() + activeForMillis;
	}

}
