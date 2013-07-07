package org.opennaas.core.resources;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Héctor Fernández
 * @email  hbfernandezr@gmail.com
 */

@XmlRootElement(name="Result")
@XmlAccessorType(XmlAccessType.FIELD)
public class ResWrapper {
		
	@XmlElement
	protected List<String> entry;
	
	public ResWrapper(){
		entry = new ArrayList<String>();
	}

	public List<String> getResults() {
		return this.entry;
	}

	public void setResults(List<String> results) {
		this.entry = results;
	}	
	
	public void add(String s){ 
		entry.add(s);
	}

	public boolean isEmpty() {
		return entry.isEmpty();
	}
	
	public void clear(){
		entry.clear();
	}
}