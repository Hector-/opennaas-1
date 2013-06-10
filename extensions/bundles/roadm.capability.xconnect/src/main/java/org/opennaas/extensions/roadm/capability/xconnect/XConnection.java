package org.opennaas.extensions.roadm.capability.xconnect;

public class XConnection {
	
	private String 				instanceID;
	public String				srcEndPointId	= "";
	public String				srcLabelId		= "";
	public String				dstEndPointId	= "";
	public String				dstLabelId		= "";	
	
	public XConnection(){
		setInstanceID(generateInstanceID());
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
	
	protected String generateInstanceID() {
		String orgID = "opennaas.org"; 
		String localID = this.getClass().getName() + "/" + this.toString();

		return orgID + ":" + localID;
	}
}
