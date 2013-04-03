package org.opennaas.extensions.router.model.opticalSwitch;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import org.opennaas.extensions.router.model.FCPort;
import org.opennaas.extensions.router.model.opticalSwitch.dwdm.proteus.cards.ProteusOpticalSwitchCard;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({ 
	DWDMChannel.class
})
public class FiberConnection {

	/* These parameters don't have to be complete, it is enough if they contain */
	/**
	 * A card with chassis and slot correctly setted up.
	 */	
	ProteusOpticalSwitchCard	srcCard	= null;
	
	/**
	 * A card with chassis and slot correctly setted up.
	 */
	ProteusOpticalSwitchCard	dstCard	= null;

	/**
	 * A FCPort with portNumber correctly setted up.
	 */
	@XmlIDREF
	FCPort						srcPort	= null;
	
	/**
	 * A FCPort with portNumber correctly setted up.
	 */
	@XmlIDREF
	FCPort						dstPort	= null;

	/**
	 * A DWDMChannel with lambda correctly setted up.
	 */	
	@XmlIDREF
	DWDMChannel					srcDWDMFiberChannel;
	
	/**
	 * A DWDMChannel with lambda correctly setted up.
	 */	
	@XmlIDREF
	DWDMChannel					dstDWDMFiberChannel;

	public FiberConnection() {

	}
	
	public ProteusOpticalSwitchCard getSrcCard() {
		return srcCard;
	}
	
	public void setSrcCard(ProteusOpticalSwitchCard srcCard) {
		this.srcCard = srcCard;
	}

	public ProteusOpticalSwitchCard getDstCard() {
		return dstCard;
	}

	public void setDstCard(ProteusOpticalSwitchCard dstCard) {
		this.dstCard = dstCard;
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

}
