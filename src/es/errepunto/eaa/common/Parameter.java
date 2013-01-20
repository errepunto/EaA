package es.errepunto.eaa.common;

import java.util.Observable;

/**
 * A generic parameter. A parameter has a value, a name and an optional description.
 * Parameter extends "Observable", so you can register observers that will be notified
 * when the value changes.
 * 
 * @author ruben
 *
 * @param <T>
 */
public class  Parameter<T> extends Observable {
	protected String name;
	protected T value;
	protected String desc;
	
	public Parameter(String name, T value, String desc) {
		this.name = name;	//Name shouldn't be modified
		this.value = value;
		this.desc = desc;
	}
	
	public Parameter(String name, T value) {
		this(name, value, "");
	}
	
	public Parameter(String name) {
		this(name, null, "");
	}

	public String getName() {
		return name;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
		setChanged();
		notifyObservers();
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public String toString() {
		return name+": " + desc;
	}
}
