package org.opennaas.extensions.router.model.opticalSwitch;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlSeeAlso({ DWDMChannel.class })
@XmlAccessorType(XmlAccessType.FIELD)
public class FiberChannel {
	
	@XmlID	
	protected String instanceID;
	
	@XmlElement(name="fiberNumChannel")
	private int	numChannel;
	
	public FiberChannel(){
		setInstanceID(generateLocalID());
	}

	public int getNumChannel() {
		return numChannel;
	}

	public void setNumChannel(int numChannel) {
		this.numChannel = numChannel;
	}
	
	public String getInstanceID() {
		return instanceID;
	}
	
	public void setInstanceID(String instanceID ) {
		this.instanceID = instanceID;
	}
	
	public String generateLocalID() {
		return this.getClass().getName() + "/" + getNumChannel();
	}
}
