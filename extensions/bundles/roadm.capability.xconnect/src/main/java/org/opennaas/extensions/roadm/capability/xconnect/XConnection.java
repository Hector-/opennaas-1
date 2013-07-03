package org.opennaas.extensions.roadm.capability.xconnect;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Héctor Fernández
 * @email  hbfernandezr@gmail.com
 */

@XmlRootElement
public class XConnection {
	
	private String 				instanceID;
	private String				srcEndPointId	= "";
	private String				srcLabelId		= "";
	private String				dstEndPointId	= "";
	private String				dstLabelId		= "";	
	
	public XConnection(){		
	}
	
	public String getInstanceID() {
		return instanceID;
	}
	
	public void setInstanceID(String instanceID ) {
		this.instanceID = instanceID;
	}
	
	public String getSrcEndPointId() {
		return srcEndPointId;
	}

	public void setSrcEndPointId(String srcEndPointId) {
		this.srcEndPointId = srcEndPointId;
	}

	public String getSrcLabelId() {
		return srcLabelId;
	}

	public void setSrcLabelId(String srcLabelId) {
		this.srcLabelId = srcLabelId;
	}

	public String getDstEndPointId() {
		return dstEndPointId;
	}

	public void setDstEndPointId(String dstEndPointId) {
		this.dstEndPointId = dstEndPointId;
	}

	public String getDstLabelId() {
		return dstLabelId;
	}

	public void setDstLabelId(String dstLabelId) {
		this.dstLabelId = dstLabelId;
	}
}
