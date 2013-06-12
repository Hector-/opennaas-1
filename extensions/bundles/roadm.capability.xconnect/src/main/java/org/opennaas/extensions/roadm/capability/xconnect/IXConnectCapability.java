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
	 * @return the XConnection's id
	 * @throws CapabilityException
	 */
	@Path("/makeXConnection")
	@POST
	@Produces(MediaType.APPLICATION_XML)
	public String makeXConnection(XConnection xconnect) throws CapabilityException;

	/**
	 * Deletes a XConnection in the ROADM
	 * 
	 * @param the id of the desired XConnection to delete
	 * @throws CapabilityException
	 */
	@Path("/removeXConnection/{id}")
	@DELETE
	@Produces(MediaType.APPLICATION_XML)
	public void removeXConnection(@PathParam("id") String id) throws CapabilityException;	
	
	/**
	 * Returns all XConnection in the ROADM
	 * 
	 * @return the list of XConnection's ids in the ROADM
	 * @throws CapabilityException
	 */
	@Path("/getXConnections")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public List<String> listXConnections() throws CapabilityException;
	
	/**
	 * Returns all available EndPoints in the ROADM
	 * 
	 * @return the list of EndPoint's ids
	 * @throws CapabilityException
	 */
	@Path("/getEndPoints")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public List<String> listEndPoints() throws CapabilityException;
	
	/**
	 * Returns all available Labels from an EndPoint in the ROADM
	 * 
	 * @param the id of the desired EndPoint
	 * @return the list of Labels' ids from the chosen EndPoint
	 * @throws CapabilityException
	 */
	@Path("/getLabels/{id}")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public List<String> listLabels(@PathParam("id") String id) throws CapabilityException;
	
	/**
	 * Returns a specific XConnection
	 * 
	 * @param the id of the desired XConnection
	 * @return XConnection
	 * @throws CapabilityException
	 */	
	@Path("/getXConnection/{id}")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public XConnection getXConnection(@PathParam("id") String id) throws CapabilityException;		
}
