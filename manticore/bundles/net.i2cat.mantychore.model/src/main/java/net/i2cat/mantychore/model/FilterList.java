/**
 * This file was auto-generated by mofcomp -j version 1.0.0 on Wed Jan 12
 * 09:21:06 CET 2011.
 */

package net.i2cat.mantychore.model;

import java.io.Serializable;

/**
 * This Class contains accessor and mutator methods for all properties defined in the CIM class FilterList as well as methods comparable to the
 * invokeMethods defined for this class. This Class implements the FilterListBean Interface. The CIM class FilterList is described as follows:
 *
 * A FilterList is used by network devices to identify routes by aggregating a set of FilterEntries into a unit, called a FilterList. FilterLists can
 * also be used to accept or deny routing updates. A FilterList is weak to the network device (i.e., the ComputerSystem) that contains it. Hence, the
 * ComputerSystem keys are propagated to this class.
 */
public class FilterList extends LogicalElement implements Serializable {

	/**
	 * This constructor creates a FilterListBeanImpl Class which implements the FilterListBean Interface, and encapsulates the CIM class FilterList in
	 * a Java Bean. The CIM class FilterList is described as follows:
	 *
	 * A FilterList is used by network devices to identify routes by aggregating a set of FilterEntries into a unit, called a FilterList. FilterLists
	 * can also be used to accept or deny routing updates. A FilterList is weak to the network device (i.e., the ComputerSystem) that contains it.
	 * Hence, the ComputerSystem keys are propagated to this class.
	 */
	public FilterList() {
	};

	/**
	 * The following constants are defined for use with the ValueMap/Values qualified property systemCreationClassName.
	 */
	private String	systemCreationClassName;

	/**
	 * This method returns the FilterList.systemCreationClassName property value. This property is described as follows:
	 *
	 * The scoping ComputerSystem's CreationClassName.
	 *
	 * @return String current systemCreationClassName property value
	 * @exception Exception
	 */
	public String getSystemCreationClassName() {

		return this.systemCreationClassName;
	} // getSystemCreationClassName

	/**
	 * This method sets the FilterList.systemCreationClassName property value. This property is described as follows:
	 *
	 * The scoping ComputerSystem's CreationClassName.
	 *
	 * @param String
	 *            new systemCreationClassName property value
	 * @exception Exception
	 */
	public void setSystemCreationClassName(String systemCreationClassName) {

		this.systemCreationClassName = systemCreationClassName;
	} // setSystemCreationClassName

	/**
	 * The following constants are defined for use with the ValueMap/Values qualified property systemName.
	 */
	private String	systemName;

	/**
	 * This method returns the FilterList.systemName property value. This property is described as follows:
	 *
	 * The scoping ComputerSystem's Name.
	 *
	 * @return String current systemName property value
	 * @exception Exception
	 */
	public String getSystemName() {

		return this.systemName;
	} // getSystemName

	/**
	 * This method sets the FilterList.systemName property value. This property is described as follows:
	 *
	 * The scoping ComputerSystem's Name.
	 *
	 * @param String
	 *            new systemName property value
	 * @exception Exception
	 */
	public void setSystemName(String systemName) {

		this.systemName = systemName;
	} // setSystemName

	/**
	 * The following constants are defined for use with the ValueMap/Values qualified property creationClassName.
	 */
	private String	creationClassName;

	/**
	 * This method returns the FilterList.creationClassName property value. This property is described as follows:
	 *
	 * The type of class that this instance is.
	 *
	 * @return String current creationClassName property value
	 * @exception Exception
	 */
	public String getCreationClassName() {

		return this.creationClassName;
	} // getCreationClassName

	/**
	 * This method sets the FilterList.creationClassName property value. This property is described as follows:
	 *
	 * The type of class that this instance is.
	 *
	 * @param String
	 *            new creationClassName property value
	 * @exception Exception
	 */
	public void setCreationClassName(String creationClassName) {

		this.creationClassName = creationClassName;
	} // setCreationClassName

	// /**
	// * The following constants are defined for use with the ValueMap/Values
	// * qualified property name.
	// */
	// private String name;
	/**
	 * This method returns the FilterList.name property value. This property is described as follows:
	 *
	 * This is the name of the FilterList.
	 *
	 * @return String current name property value
	 * @exception Exception
	 */
	@Override
	public String getName() {
		return super.getName();
	} // getName

	/**
	 * This method sets the FilterList.name property value. This property is described as follows:
	 *
	 * This is the name of the FilterList.
	 *
	 * @param String
	 *            new name property value
	 * @exception Exception
	 */
	@Override
	public void setName(String name) {
		super.setName(name);
	} // setName

	/**
	 * The following constants are defined for use with the ValueMap/Values qualified property Direction.
	 */

	public enum Direction {
		NOT_APPLICABLE,
		INPUT,
		OUTPUT,
		BOTH,
		MIRRORED
	}

	private Direction	direction;

	/**
	 * This method returns the FilterList.direction property value. This property is described as follows:
	 *
	 * This defines whether the FilterList is used for input, output, or both input and output filtering. All values are used with respect to the
	 * interface for which the FilterList applies. "Not Applicable" (0) is used when there is no direction applicable to the FilterList. "Input" (1)
	 * is used when the FilterList applies to packets that are inbound on the related interface. "Output" (2) is used when the FilterList applies to
	 * packets that are outbound on the related interface. "Both" (3) is used to indicate that the direction is immaterial, e.g., to filter on a
	 * source subnet regardless of whether the flow is inbound or outbound. "Mirrored" (4) is also applicable to both inbound and outbound flow
	 * processing, but indicates that the filter criteria are applied asymmetrically to traffic in both directions and, thus, specifies the reversal
	 * of source and destination criteria (as opposed to the equality of these criteria as indicated by "Both"). The match conditions in the
	 * aggregated FilterEntryBase subclass instances are defined from the perspective of outbound flows and applied to inbound flows as well by
	 * reversing the source and destination criteria. So, for example, consider a FilterList with 3 FilterEntries indicating destination port = 80,
	 * and source and destination addresses of a and b, respectively. Then, for the outbound direction, the filter entries match as specified and the
	 * 'mirror' (for the inbound direction) matches on source port = 80 and source and destination addresses of b and a, respectively.
	 *
	 * @return int current direction property value
	 * @exception Exception
	 */
	public Direction getDirection() {

		return this.direction;
	} // getDirection

	/**
	 * This method sets the FilterList.direction property value. This property is described as follows:
	 *
	 * This defines whether the FilterList is used for input, output, or both input and output filtering. All values are used with respect to the
	 * interface for which the FilterList applies. "Not Applicable" (0) is used when there is no direction applicable to the FilterList. "Input" (1)
	 * is used when the FilterList applies to packets that are inbound on the related interface. "Output" (2) is used when the FilterList applies to
	 * packets that are outbound on the related interface. "Both" (3) is used to indicate that the direction is immaterial, e.g., to filter on a
	 * source subnet regardless of whether the flow is inbound or outbound. "Mirrored" (4) is also applicable to both inbound and outbound flow
	 * processing, but indicates that the filter criteria are applied asymmetrically to traffic in both directions and, thus, specifies the reversal
	 * of source and destination criteria (as opposed to the equality of these criteria as indicated by "Both"). The match conditions in the
	 * aggregated FilterEntryBase subclass instances are defined from the perspective of outbound flows and applied to inbound flows as well by
	 * reversing the source and destination criteria. So, for example, consider a FilterList with 3 FilterEntries indicating destination port = 80,
	 * and source and destination addresses of a and b, respectively. Then, for the outbound direction, the filter entries match as specified and the
	 * 'mirror' (for the inbound direction) matches on source port = 80 and source and destination addresses of b and a, respectively.
	 *
	 * @param int new direction property value
	 * @exception Exception
	 */
	public void setDirection(Direction direction) {

		this.direction = direction;
	} // setDirection

} // Class FilterList