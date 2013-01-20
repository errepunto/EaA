package es.errepunto.eaa.events;

import java.util.ArrayList;

import es.errepunto.eaa.common.Parameter;

/**
 * Main interface for events.
 * 
 * @author ruben
 *
 */
public interface Event {
	public boolean isTriggered();
	
	public void addParameter(Parameter<?> p);
	
	public ArrayList<Parameter> getParameters();
	
	public Parameter getParameter(String name);
	
	public void removeParameter(String name);
}
