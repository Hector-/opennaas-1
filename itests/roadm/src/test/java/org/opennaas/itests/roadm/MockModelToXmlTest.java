package org.opennaas.itests.roadm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.openengsb.labs.paxexam.karaf.options.KarafDistributionOption.keepRuntimeFolder;
import static org.opennaas.itests.helpers.OpennaasExamOptions.includeFeatures;
import static org.opennaas.itests.helpers.OpennaasExamOptions.includeTestHelper;
import static org.opennaas.itests.helpers.OpennaasExamOptions.noConsole;
import static org.opennaas.itests.helpers.OpennaasExamOptions.openDebugSocket;
import static org.opennaas.itests.helpers.OpennaasExamOptions.opennaasDistributionConfiguration;
import static org.ops4j.pax.exam.CoreOptions.options;

import java.util.List;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.inject.Inject;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.opennaas.core.resources.IModel;
import org.opennaas.core.resources.IResource;
import org.opennaas.core.resources.IResourceManager;
import org.opennaas.core.resources.ObjectSerializer;
import org.opennaas.core.resources.descriptor.ResourceDescriptor;
import org.opennaas.core.resources.helpers.ResourceHelper;
import org.opennaas.core.resources.protocol.IProtocolManager;
import org.opennaas.core.resources.protocol.ProtocolSessionContext;
import org.opennaas.extensions.router.model.LogicalDevice;
import org.opennaas.extensions.router.model.LogicalPort;
import org.opennaas.extensions.router.model.NetworkPort;
import org.opennaas.extensions.router.model.ProtocolEndpoint;
import org.opennaas.extensions.router.model.opticalSwitch.dwdm.proteus.ProteusOpticalSwitch;
import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.junit.Configuration;
import org.ops4j.pax.exam.junit.JUnit4TestRunner;

@Ignore
@RunWith(JUnit4TestRunner.class)
public class MockModelToXmlTest {	

	/**
	 * @uml.property  name="centralRM"
	 * @uml.associationEnd  readOnly="true"
	 */
	@Inject
	private IResourceManager centralRM;
	// Sin la I peta
	
	/**
	 * @uml.property  name="centralPM"
	 * @uml.associationEnd  readOnly="true"
	 */
	@Inject
	private IProtocolManager centralPM;
	// Sin la I peta
	
	/**
	 * @uml.property  name="roadm"
	 * @uml.associationEnd  
	 */
	private IResource roadm;
	
	@Configuration
	public static Option[] configuration() {
		return options(opennaasDistributionConfiguration(),
				includeFeatures("opennaas-luminis", "opennaas-roadm-proteus"),
				includeTestHelper(),
				noConsole(),
				keepRuntimeFolder(),
				openDebugSocket());
	}
	
	@Before
	public void initializeResource() throws Exception {	
		//crear el recurs
		//ResourceDescriptor descriptor = generateROADMDescriptor();
		ResourceDescriptor descriptor = ResourceHelper.newResourceDescriptorProteus("roadm");		

		roadm = centralRM.createResource(descriptor);
		
		//afegir-hi un context
		//ProtocolSessionContext context = generateROADMMockContext();
		ProtocolSessionContext protocolSessionContext = new ProtocolSessionContext();
		protocolSessionContext.addParameter("protocol.mock", "true");
		protocolSessionContext.addParameter(ProtocolSessionContext.PROTOCOL, "wonesys");
		protocolSessionContext.addParameter(ProtocolSessionContext.AUTH_TYPE, "password");		
				
		//centralPM.registerContext(roadm.getResourceIdentifier(), context);
		centralPM.getProtocolSessionManagerWithContext(roadm.getResourceIdentifier().getId(), protocolSessionContext);
		
		//arrencar el recurs
		//això inicialitza el seu model utilitzant el context anterior
		centralRM.startResource(roadm.getResourceIdentifier());				
	}
	
	@Test
	public void ToXmlTest() throws Exception {									
				
		IModel model = roadm.getModel();							
		
		String xml = model.toXml();
		
		/** 
		 * 
		 * Save Xml model on a file
		 * 
		 **/
		BufferedWriter out;
        try {            
            out = new BufferedWriter(new FileWriter("/home/sito/OutputXml.txt"));            
            
            // Append new content
	        //out = new BufferedWriter(new FileWriter("helloworld.txt", true))
 
            // Write out a string to the file                      
            out.write(xml);
 
            // Write a new line to the file so the next time you write
	        // to the file it does it on the next line
	        //out.newLine();
            
 
            // Flushes and closes the stream
            out.close();
            
        }catch(IOException e){
            System.out.println("There was a problem:" + e);
	    }		
		
		IModel loadedModel = (IModel) ObjectSerializer.fromXml(xml, ProteusOpticalSwitch.class);					
		
		String loadedXml = loadedModel.toXml();
		
		Assert.assertEquals(xml, loadedXml);
				
		//compareModels((ProteusOpticalSwitch) model, (ProteusOpticalSwitch)loadedModel); 		
					
	}	
	
	private void compareModels(ProteusOpticalSwitch original, ProteusOpticalSwitch loaded){
		assertEquals(original.getName(), loaded.getName());
		assertEquals(original.getElementName(), loaded.getElementName());		
		assertEquals(original.getLogicalDevices().size(), loaded.getLogicalDevices().size());

		for (int i = 0; i < original.getLogicalDevices().size(); i++) {
			// check subtypes are maintained
			assertEquals(original.getLogicalDevices().get(i).getClass(), loaded.getLogicalDevices().get(i).getClass());
			assertEquals(original.getLogicalDevices().get(i).getName(), loaded.getLogicalDevices().get(i).getName());
			if (original.getLogicalDevices().get(i) instanceof NetworkPort) {
				assertEquals(((NetworkPort) original.getLogicalDevices().get(i)).getPortNumber(),
						((NetworkPort) loaded.getLogicalDevices().get(i)).getPortNumber());
			}

			// PETA
			List<LogicalDevice> ld1 = new ArrayList<LogicalDevice>();
			ld1 = (List<LogicalDevice>) original.getLogicalDevices();
			LogicalPort p1 = (LogicalPort)ld1.get(i); //PETA
			List<ProtocolEndpoint> pe1 = new ArrayList<ProtocolEndpoint>();	
			pe1 = p1.getProtocolEndpoint();
			int x = pe1.size();
			
			LogicalPort p2 = ((LogicalPort) loaded.getLogicalDevices().get(i));
			List<ProtocolEndpoint> l2 = new ArrayList<ProtocolEndpoint>();
			l2 = p2.getProtocolEndpoint();
			int y = l2.size();
						
			assertEquals(x,y);
			for (int j = 0; j < ((LogicalPort) original.getLogicalDevices().get(i)).getProtocolEndpoint().size(); j++) {
				// check subtypes are maintained
				assertEquals(((LogicalPort) original.getLogicalDevices().get(i)).getProtocolEndpoint().get(j).getClass(),
						((LogicalPort) loaded.getLogicalDevices().get(i)).getProtocolEndpoint().get(j).getClass());
				// check reverse association is loaded
				assertTrue("Reverse association should be loaded", ((LogicalPort) loaded.getLogicalDevices().get(i)).getProtocolEndpoint().get(j)
						.getLogicalPorts()
						.contains(loaded.getLogicalDevices().get(i)));
			}
		}	
	}
}