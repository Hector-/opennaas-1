package org.opennaas.extensions.roadm.capability.xconnect;

import java.util.Iterator;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.opennaas.core.resources.ActivatorException;
import org.opennaas.core.resources.ResourceException;
import org.opennaas.core.resources.action.IAction;
import org.opennaas.core.resources.action.IActionSet;
import org.opennaas.core.resources.capability.AbstractCapability;
import org.opennaas.core.resources.capability.CapabilityException;
import org.opennaas.core.resources.descriptor.CapabilityDescriptor;
import org.opennaas.core.resources.descriptor.ResourceDescriptorConstants;
import org.opennaas.extensions.queuemanager.IQueueManagerCapability;
import org.opennaas.extensions.roadm.capability.connections.IConnectionsCapability;
import org.opennaas.extensions.router.model.FCPort;
import org.opennaas.extensions.router.model.LogicalDevice;
import org.opennaas.extensions.router.model.LogicalPort;
import org.opennaas.extensions.router.model.NetworkPort;
import org.opennaas.extensions.router.model.opticalSwitch.DWDMChannel;
import org.opennaas.extensions.router.model.opticalSwitch.FiberChannel;
import org.opennaas.extensions.router.model.opticalSwitch.FiberConnection;
import org.opennaas.extensions.router.model.opticalSwitch.dwdm.WDMFCPort;
import org.opennaas.extensions.router.model.opticalSwitch.dwdm.proteus.ProteusOpticalSwitch;
import org.opennaas.extensions.router.model.opticalSwitch.dwdm.proteus.cards.ProteusOpticalSwitchCard;

/**
 * @author Héctor Fernández
 * @email  hbfernandezr@gmail.com
 */

@XmlRootElement
public class XConnectCapability extends AbstractCapability implements IXConnectCapability {
	
	public static final String	CAPABILITY_TYPE	= "xconnect";

	public static String		XCONNECT		= CAPABILITY_TYPE;

	Log							log				= LogFactory.getLog(XConnectCapability.class);

	private String				resourceId	    = "";
	
	@XmlElement
	@XmlElementWrapper(name="Wrapper")
	private Wrapper		 		results 		= new Wrapper();
	
	public XConnectCapability(CapabilityDescriptor descriptor, String resourceId) {
		super(descriptor);
		this.resourceId = resourceId;
		log.debug("Built new XConnect Capability");
	}
	
	@Override
	public void activate() throws CapabilityException {
		registerService(Activator.getContext(), CAPABILITY_TYPE, getResourceType(), getResourceName(), IXConnectCapability.class.getName());
		super.activate();
	}

	@Override
	public void deactivate() throws CapabilityException {
		registration.unregister();
		super.deactivate();
	}

	@Override
	public String getCapabilityName() {
		return CAPABILITY_TYPE;
	}
	
	@Override
	public void queueAction(IAction action) throws CapabilityException {
		getQueueManager(resourceId).queueAction(action);
	}
	
	/**
	 * 
	 * @return QueuemanagerService this capability is associated to.
	 * @throws CapabilityException
	 *             if desired queueManagerService could not be retrieved.
	 */
	private IQueueManagerCapability getQueueManager(String resourceId) throws CapabilityException {
		try {
			return Activator.getQueueManagerService(resourceId);
		} catch (ActivatorException e) {
			throw new CapabilityException("Failed to get QueueManagerService for resource " + resourceId, e);
		}
	}
	
	@Override
	public IActionSet getActionSet() throws CapabilityException {
		String name = this.descriptor.getPropertyValue(ResourceDescriptorConstants.ACTION_NAME);
		String version = this.descriptor.getPropertyValue(ResourceDescriptorConstants.ACTION_VERSION);

		try {
			return Activator.getXConnectActionSetService(name, version);
		} catch (ActivatorException e) {
			throw new CapabilityException(e);
		}
	}

	@Override
	public String makeXConnection(XConnection xconnect) throws CapabilityException {
		log.info("Start of makeXConnection call");
		
		String id = "";		
		
		try {
			FiberConnection connectionRequest = XConnectBridge.xconnectionToFiberConnection(xconnect);
			
			id = XConnectBridge.getXconnectionID(xconnect);
	
			IConnectionsCapability connectionsCapability = (IConnectionsCapability) resource.getCapabilityByInterface(IConnectionsCapability.class);			
			connectionsCapability.makeConnection(connectionRequest);			
		} 
		catch (ResourceException e) {
			log.info("ERROR: makeXConnection call failed");
			e.printStackTrace();
			return "";			
		}
				
		log.info("End of makeXConnection call");
		
		return id;
	}

	@Override
	public void removeXConnection(String id) throws CapabilityException {
		log.info("Start of removeXConnection call");
		
		try{									
			XConnection xconnect = XConnectBridge.xconnectionFromId(id);			
						
			FiberConnection connectionRequest = XConnectBridge.xconnectionToFiberConnection(xconnect);
				
			IConnectionsCapability connectionsCapability = (IConnectionsCapability) resource.getCapabilityByInterface(IConnectionsCapability.class);
			connectionsCapability.removeConnection(connectionRequest);																				
		}
		catch (ResourceException e) {
			log.info("ERROR: removeXConnection call failed");
			e.printStackTrace();			
		}
		
		log.info("End of removeXConnection call");
	}
	
	@Override
	public Wrapper listXConnections() throws CapabilityException {
		log.info("Start of listXConnections call");		
		results.clear();		
				
		ProteusOpticalSwitch model = (ProteusOpticalSwitch) resource.getModel();			
		
		for (FiberConnection connection : model.getFiberConnections()) {

			XConnection xconnect = XConnectBridge.fiberconnectionToXConnection(connection);					
			
			results.add("Port " + xconnect.getSrcEndPointId() + " in " + connection.getSrcCard().getCardType().toString() + " using channel " + xconnect.getSrcLabelId() + " --> " +
					"Port " + xconnect.getDstEndPointId() + " in " + connection.getDstCard().getCardType().toString() + " using channel " + xconnect.getDstLabelId());
		}					
		
		log.info("End of listXConnections call");
		
		return results;			
	}
	
	@Override	
	public Wrapper getEndPoints() throws CapabilityException {
		log.info("Start of listEndPoints call");
		results.clear();
		
		ProteusOpticalSwitch model = (ProteusOpticalSwitch) resource.getModel();		
		
		for (LogicalDevice card : model.getLogicalDevices()) {
			if (card instanceof ProteusOpticalSwitchCard) {

				String cardType = ((ProteusOpticalSwitchCard) card).getCardType().toString();
				int chassis = ((ProteusOpticalSwitchCard) card).getChasis();
				int slot = ((ProteusOpticalSwitchCard) card).getModuleNumber();

				results.add("\t" + cardType + " card in chassis " + chassis + " and slot " + slot);

				// ports
				results.add("\t\tNumber of ports: " + ((ProteusOpticalSwitchCard) card).getModulePorts().size());
				for (NetworkPort port : ((ProteusOpticalSwitchCard) card).getModulePorts()) {
					results.add("\t\tPort " + port.getPortNumber() + " used in " + port.getPortsOnDevice().size() + " connections.");
				}
			}
		}														
		
		log.info("End of listEndPoints call");
		
		return results;							
	}

	@Override	
	public Wrapper getLabels(String endPointID) throws CapabilityException {
		log.info("Start of listLabels call");	
		results.clear();
		
		ProteusOpticalSwitch model = (ProteusOpticalSwitch) resource.getModel();	
		
		int cardParameters[] = XConnectBridge.getCardParameters(endPointID);
		
		ProteusOpticalSwitchCard card = model.getCard(cardParameters[0], cardParameters[1]);				
		
		List<FiberChannel> allChannels = card.getChannelPlan().getAllChannels();

		results.add("Port " + cardParameters[2] + " supports " + allChannels.size() + " channels");	

		for (int i = 0; i < allChannels.size(); i++) {

			FiberChannel channel = allChannels.get(i);
			boolean inUse = false;

			NetworkPort port = card.getPort(cardParameters[2]);
			// look for current channel in port
			for (int j = 0; j < ((FCPort) port).getPortsOnDevice().size() && !inUse; j++) {

				LogicalPort subPort = ((FCPort) port).getPortsOnDevice().get(j);
				if (subPort instanceof WDMFCPort) {
					DWDMChannel subPortChannel = ((WDMFCPort) subPort).getDWDMChannel();
					if (channel.getNumChannel() == subPortChannel.getNumChannel()) {
						inUse = true;
					}
				}
			}

			// set channel info
			String lambda = "-";
			if (channel instanceof DWDMChannel) {
				lambda = Double.toString(((DWDMChannel) channel).getLambda());
			}
			String inUseS = "-";
			if (inUse) {
				inUseS = "X";
			}

			results.add(Integer.toString(channel.getNumChannel()) + " - " + lambda + " - " + inUseS);					
		}			
		
		log.info("End of listLabels call");			
		
		return results;
	}

	@Override
	public XConnection getXConnection(String id) throws CapabilityException {
		log.info("Start of getXConnection call");			
		
		boolean found = false;
		
		XConnection xconnect = XConnectBridge.xconnectionFromId(id);					
		
		ProteusOpticalSwitch model = (ProteusOpticalSwitch) resource.getModel();
		
		Iterator<FiberConnection> iter = model.getFiberConnections().iterator();			
		
		while(iter.hasNext() && !found){
			FiberConnection fconnect = iter.next();
			String actualID = XConnectBridge.fiberconnectionToXConnection(fconnect).getInstanceID();
			
			if(actualID.equals(id)) 
				found = true;			
		}							
		
		log.info("End of getXConnection call");		
		
		if(found) return xconnect;
		
		return null;
	}	
}
