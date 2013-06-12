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

/**
 * @author Héctor Fernández
 * @email  hbfernandezr@gmail.com
 */

@Path("/")
public interface IXConnectCapability extends ICapability {

	/**
	 * Creates a XConnection in the ROADM
	 * 
	 * @param xconnect
	 * @throws CapabilityException
	 */
	@Path("/makeXConnection")
	@POST
	@Produces(MediaType.APPLICATION_XML)
	public void makeXConnection(XConnection xconnect) throws CapabilityException;

	/**
	 * Deletes a XConnection in the ROADM
	 * 
	 * @param id
	 * @throws CapabilityException
	 */
	@Path("/removeXConnection/{id}")
	@DELETE
	@Produces(MediaType.APPLICATION_XML)
	public void removeXConnection(@PathParam("id") String id) throws CapabilityException;
	
	/**
	 * Returns all XConncetion in the ROADM
	 * 
	 * @throws CapabilityException
	 */
	@Path("/getXConnections")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public List<String> listXConnections() throws CapabilityException;

	/**
	 * Returns all available EndPoints in the ROADM
	 * 
	 * @throws CapabilityException
	 */
	@Path("/getEndPoints")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public List<String> listEndPoints() throws CapabilityException;
	
	/**
	 * Returns all available Labels from an EndPoint in the ROADM
	 * 
	 * @param id
	 * @throws CapabilityException
	 */
	@Path("/getLabels/{id}")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public List<String> listLabels(@PathParam("id") String id) throws CapabilityException;
	
	/**
	 * Returns a specific XConnetion
	 * 
	 * @param id
	 * @throws CapabilityException
	 */
	@Path("/getXConnection/{id}")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public XConnection getXConnection(@PathParam("id") String id) throws CapabilityException;		
}
