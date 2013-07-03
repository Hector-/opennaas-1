package org.opennaas.extensions.router.model.opticalSwitch;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlRootElement;

import org.opennaas.extensions.router.model.FCPort;
import org.opennaas.extensions.router.model.opticalSwitch.dwdm.proteus.cards.ProteusOpticalSwitchCard;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class FiberConnection {

	/* These parameters don't have to be complete, it is enough if they contain */
	
	@XmlID
	private String	instanceID;	
	
	/**
	 * A card with chassis and slot correctly setted up.
	 */
	@XmlElement(name="srcProteusCard")
	@XmlIDREF	
	ProteusOpticalSwitchCard	srcCard	= null;
	
	/**
	 * A card with chassis and slot correctly setted up.
	 */
	@XmlElement(name="dstProteusCard")
	@XmlIDREF
	ProteusOpticalSwitchCard	dstCard	= null;

	/**
	 * A FCPort with portNumber correctly setted up.
	 */
	@XmlElement(name="srcFCPort")
	@XmlIDREF
	FCPort						srcPort	= null;
	
	/**
	 * A FCPort with portNumber correctly setted up.
	 */
	@XmlElement(name="dstFCPort")
	@XmlIDREF
	FCPort						dstPort	= null;

	/**
	 * A DWDMChannel with lambda correctly setted up.
	 */	
	@XmlElement(name="srcDWDMChannel")
	@XmlIDREF
	DWDMChannel					srcDWDMFiberChannel;
	
	/**
	 * A DWDMChannel with lambda correctly setted up.
	 */	
	@XmlElement(name="dstDWDMChannel")
	@XmlIDREF
	DWDMChannel					dstDWDMFiberChannel;

	public FiberConnection() {
		setInstanceID(generateInstanceID());
	}
	
	public String getInstanceID() {
		return instanceID;
	}
	
	public void setInstanceID(String instanceID ) {
		this.instanceID = instanceID;
	}
	
	public ProteusOpticalSwitchCard getSrcCard() {
		return srcCard;
	}
	
	public void setSrcCard(ProteusOpticalSwitchCard srcCard) {
		this.srcCard = (ProteusOpticalSwitchCard)srcCard;
	}

	public ProteusOpticalSwitchCard getDstCard() {
		return dstCard;
	}

	public void setDstCard(ProteusOpticalSwitchCard dstCard) {		
		this.dstCard = (ProteusOpticalSwitchCard)dstCard;
	}

	public FCPort getSrcPort() {
		return srcPort;
	}

	public void setSrcPort(FCPort srcPort) {
		this.srcPort = srcPort;
	}

	public FCPort getDstPort() {
		return dstPort;
	}

	public void setDstPort(FCPort dstPort) {
		this.dstPort = dstPort;
	}

	public DWDMChannel getSrcFiberChannel() {
		return srcDWDMFiberChannel;
	}

	public void setSrcFiberChannel(DWDMChannel srcFiberChannel) {
		this.srcDWDMFiberChannel = srcFiberChannel;
	}

	public DWDMChannel getDstFiberChannel() {
		return dstDWDMFiberChannel;
	}

	public void setDstFiberChannel(DWDMChannel dstFiberChannel) {
		this.dstDWDMFiberChannel = dstFiberChannel;
	}
	
	protected String generateInstanceID() {
		String orgID = "opennaas.org"; 
		String localID = this.getClass().getName() + "/" + this.toString();

		return orgID + ":" + localID;
	}
}
