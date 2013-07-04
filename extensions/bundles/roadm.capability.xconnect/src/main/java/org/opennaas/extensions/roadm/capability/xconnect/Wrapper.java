package org.opennaas.extensions.roadm.capability.xconnect;

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
public class Wrapper {
		
	@XmlElement
	protected List<String> entry;
	
	public Wrapper(){
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
	
	public void clear(){
		entry.clear();
	}
}
