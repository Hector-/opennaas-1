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

@XmlRootElement(name="List")
@XmlAccessorType(XmlAccessType.FIELD)
public class Wrapper {
		
	@XmlElement
	protected List<String> results;
	
	public Wrapper(){
		results = new ArrayList<String>();
	}

	public List<String> getResults() {
		return this.results;
	}

	public void setResults(List<String> results) {
		this.results = results;
	}	
	
	public void add(String s){ 
		results.add(s);
	}
	
	public void clear(){
		results.clear();
	}
}
