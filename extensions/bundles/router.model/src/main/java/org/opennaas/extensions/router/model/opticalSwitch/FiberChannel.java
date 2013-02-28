package org.opennaas.extensions.router.model.opticalSwitch;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlSeeAlso({ DWDMChannel.class })
@XmlAccessorType(XmlAccessType.FIELD)
public class FiberChannel {
	int	numChannel;

	public int getNumChannel() {
		return numChannel;
	}

	public void setNumChannel(int numChannel) {
		this.numChannel = numChannel;
	}

}
