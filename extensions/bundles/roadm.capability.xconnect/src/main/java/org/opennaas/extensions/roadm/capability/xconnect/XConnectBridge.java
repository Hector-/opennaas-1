package org.opennaas.extensions.roadm.capability.xconnect;

import org.opennaas.extensions.router.model.FCPort;
import org.opennaas.extensions.router.model.opticalSwitch.DWDMChannel;
import org.opennaas.extensions.router.model.opticalSwitch.FiberChannel;
import org.opennaas.extensions.router.model.opticalSwitch.FiberConnection;
import org.opennaas.extensions.router.model.opticalSwitch.dwdm.proteus.cards.ProteusOpticalSwitchCard;

/**
 * @author Héctor Fernández
 * @email  hbfernandezr@gmail.com
 */

public class XConnectBridge {	

	public static FiberConnection xconnectionToFiberConnection(XConnection xconnection){
		
		FiberConnection fiberConnection = new FiberConnection();
		
		String srcEPId = xconnection.getSrcEndPointId();
        String srcLabelId = xconnection.getSrcLabelId();
        
        String dstEPId = xconnection.getDstEndPointId();
        String dstLabelId = xconnection.getDstLabelId();          

		ProteusOpticalSwitchCard srcCard = findCardFromEPId(srcEPId);		
		ProteusOpticalSwitchCard dstCard = findCardFromEPId(dstEPId);		

		FCPort srcPort = findFCPortFromEPId(srcEPId);		
		FCPort dstPort = findFCPortFromEPId(dstEPId);	

		DWDMChannel srcFiberChannel = findChannelFromLabelId(srcLabelId);
		DWDMChannel dstFiberChannel = findChannelFromLabelId(dstLabelId);
				
		fiberConnection.setSrcCard(srcCard);
		fiberConnection.setDstCard(dstCard);

		fiberConnection.setSrcPort(srcPort);
		fiberConnection.setDstPort(dstPort);

		fiberConnection.setSrcFiberChannel(srcFiberChannel);
		fiberConnection.setDstFiberChannel(dstFiberChannel);
        
        return fiberConnection;
	}
	
	public static XConnection fiberconnectionToXConnection(FiberConnection fiberConnection){
		
		XConnection xconnection = new XConnection();
		
		String srcEPId = fiberConnection.getSrcCard().getChasis() + "-" + fiberConnection.getSrcCard().getModuleNumber() + "-" +fiberConnection.getSrcPort().getPortNumber();
		
        Integer srcLabelId = Integer.valueOf(((FiberChannel)fiberConnection.getSrcFiberChannel()).getNumChannel());
        
        String dstEPId = fiberConnection.getDstCard().getChasis() + "-" + fiberConnection.getDstCard().getModuleNumber() + "-" +fiberConnection.getDstPort().getPortNumber();
        Integer dstLabelId = Integer.valueOf(((FiberChannel)fiberConnection.getDstFiberChannel()).getNumChannel());
        
        xconnection.setSrcEndPointId(srcEPId);
        xconnection.setSrcLabelId(srcLabelId.toString());
        xconnection.setDstEndPointId(dstEPId);
        xconnection.setDstLabelId(dstLabelId.toString());

        xconnection.setInstanceID(srcEPId+":"+srcLabelId.toString()+"::"+dstEPId+":"+dstLabelId.toString());
        
		return xconnection;
	}
	
    private static ProteusOpticalSwitchCard findCardFromEPId(String endPointID){        	
    	ProteusOpticalSwitchCard card = new ProteusOpticalSwitchCard();
    	
    	String[] srcPortId = endPointID.split("-");
		
    	card.setChasis(Integer.parseInt(srcPortId[0]));		
    	card.setModuleNumber(Integer.parseInt(srcPortId[1]));
		
    	return card;
    }
    
    private static FCPort findFCPortFromEPId(String endPointID){    	
    	FCPort port = new FCPort();
    	
    	String[] srcPortId = endPointID.split("-");
    	
    	port.setPortNumber(Integer.parseInt(srcPortId[2]));
    	
    	return port;
    }
    
    private static DWDMChannel findChannelFromLabelId(String labelID){
    	DWDMChannel fiberChannel = new DWDMChannel();
    	
    	fiberChannel.setNumChannel(Integer.parseInt(labelID));
    	
    	return fiberChannel;
    }
    
    public static XConnection xconnectionFromId(String id){
    	XConnection xconnect = new XConnection();
		String[] srcDstIDs = id.split("::");		
		
		String[] srcIDs = srcDstIDs[0].split(":");
		String[] dstIDs = srcDstIDs[1].split(":");
		
		String srcEPId = srcIDs[0];
		String srcLabelId = srcIDs[1];
		String dstEPId = dstIDs[0];
		String dstLabelId = dstIDs[1];
		
		xconnect.setSrcEndPointId(srcEPId);
		xconnect.setSrcLabelId(srcLabelId);
		xconnect.setDstEndPointId(dstEPId);
	    xconnect.setDstLabelId(dstLabelId);
	    xconnect.setInstanceID(id);
	    
    	return xconnect;    	    
    }
    
    public static String getXconnectionID(XConnection xconnect){    	        	
    	return xconnect.getSrcEndPointId() + ":" + xconnect.getSrcLabelId() + "::" + xconnect.getDstEndPointId() + ":" + xconnect.getDstLabelId();
    }
    
    public static int[] getCardParametersFromEPId(String endPointID){    	 
    	String[] temp = endPointID.split("-");
		
    	int[] id = new int[3]; 
    	id[0] = Integer.parseInt(temp[0]);		
    	id[1] = Integer.parseInt(temp[1]);
    	id[2] = Integer.parseInt(temp[2]);    	
    	
    	return id;
    }
    
    public static String getEPIdFromCardParameters(int chasis, int slot, int port){
    	return Integer.toString(chasis) + "-" + Integer.toString(slot) + "-" + Integer.toString(port);
    }    
}
