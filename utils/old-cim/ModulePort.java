/**
 * This file was auto-generated by mofcomp -j version 1.0.0 on Mon Apr 18
 * 09:34:15 CEST 2011.
 */

package org.opennaas.extensions.router.model;

import java.io.*;

/**
 * This Class contains accessor and mutator methods for all properties defined in the CIM class ModulePort as well as methods comparable to the
 * invokeMethods defined for this class. This Class implements the ModulePortBean Interface. The CIM class ModulePort is described as follows:
 * 
 * ModulePort associates ports with their hosting modules.
 */
public class ModulePort extends Component implements Serializable {

	/**
	 * This constructor creates a ModulePortBeanImpl Class which implements the ModulePortBean Interface, and encapsulates the CIM class ModulePort in
	 * a Java Bean. The CIM class ModulePort is described as follows:
	 * 
	 * ModulePort associates ports with their hosting modules.
	 */
	public ModulePort() {
	};

	/**
	 * This method create an Association of the type ModulePort between one LogicalModule object and NetworkPort object
	 */
	public static ModulePort link(LogicalModule groupComponent, NetworkPort
			partComponent) {

		return (ModulePort) Association.link(ModulePort.class, groupComponent, partComponent);
	}// link

} // Class ModulePort