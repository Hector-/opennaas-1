package org.opennaas.extensions.router.model.opticalSwitch.dwdm.proteus;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.opennaas.core.resources.IModel;
import org.opennaas.core.resources.ObjectSerializer;
import org.opennaas.core.resources.SerializationException;
import org.opennaas.extensions.router.model.LogicalDevice;
import org.opennaas.extensions.router.model.opticalSwitch.FiberConnection;
import org.opennaas.extensions.router.model.opticalSwitch.dwdm.proteus.cards.ProteusOpticalSwitchCard;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ProteusOpticalSwitch extends org.opennaas.extensions.router.model.System implements IModel, Serializable{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 6737244447225380204L;
	private List<FiberConnection>	fiberConnections	= new ArrayList<FiberConnection>();

	public List<FiberConnection> getFiberConnections() {
		return fiberConnections;
	}

	public void setFiberConnections(List<FiberConnection> fiberConnections) {
		this.fiberConnections = fiberConnections;
	}

	/**
	 * Returns ProteusOpticalSwitchCard identifyied with given chasis and slot, or null if there is no such card.
	 * 
	 * @param chasis
	 * @param slot
	 * @return
	 */
	public ProteusOpticalSwitchCard getCard(int chasis, int slot) {

		for (LogicalDevice dev : getLogicalDevices()) {
			if (dev instanceof ProteusOpticalSwitchCard) {
				if (((ProteusOpticalSwitchCard) dev).getChasis() == chasis &&
						((ProteusOpticalSwitchCard) dev).getSlot() == slot) {
					return (ProteusOpticalSwitchCard) dev;
				}
			}
		}
		return null;
	}
	
	@Override
	public String toXml() throws SerializationException {		
		return ObjectSerializer.toXml(this);		
	}
}
