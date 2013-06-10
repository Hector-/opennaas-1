package org.opennaas.extensions.roadm.capability.xconnect;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.opennaas.core.resources.capability.CapabilityException;
import org.opennaas.core.resources.capability.ICapability;
import org.opennaas.extensions.router.model.opticalSwitch.FiberConnection;

@Path("/")
public interface IXConnectCapability extends ICapability {

	@Path("/makeXConnection")
	@POST
	@Produces(MediaType.APPLICATION_XML)
	public void makeXConnection(XConnection xconnect) throws CapabilityException;

	@Path("/removeXConnection/{id}")
	@DELETE
	@Produces(MediaType.APPLICATION_XML)
	public void removeXConnection(@PathParam("id") String id) throws CapabilityException;
	
	@Path("/fiberConnections")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public List<String> listXConnections() throws CapabilityException;

	@Path("/fiberConnection/{id}")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public FiberConnection getXConnection(@PathParam("id") String id) throws CapabilityException;	
}
