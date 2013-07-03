package org.opennaas.extensions.router.model.opticalSwitch;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class DWDMChannel extends FiberChannel {	
		
	double	lambda;
	
	public DWDMChannel() {
		super();
	}

	/**
	 * Deprecated in lieu of getNumChannel
	 * @return
	 */
	@Deprecated
	public int getChannelNumber() {
		return getNumChannel();
	}
	/**
	 * Deprecated in lieu of setNumChannel
	 * @param channelNumber
	 */
	@Deprecated
	public void setChannelNumber(int channelNumber) {
		setNumChannel(channelNumber);
	}

	public double getLambda() {
		return lambda;
	}

	public void setLambda(double lambda) {
		this.lambda = lambda;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(lambda);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DWDMChannel other = (DWDMChannel) obj;
		if (Double.doubleToLongBits(lambda) != Double.doubleToLongBits(other.lambda))
			return false;
		return true;
	}
}
