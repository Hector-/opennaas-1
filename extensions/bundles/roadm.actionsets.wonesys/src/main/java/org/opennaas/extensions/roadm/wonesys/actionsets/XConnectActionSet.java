package org.opennaas.extensions.roadm.wonesys.actionsets;

import java.util.ArrayList;
import java.util.List;

import org.opennaas.core.resources.action.ActionSet;
import org.opennaas.extensions.roadm.wonesys.actionsets.actions.RefreshModelConnectionsAction;

public class XConnectActionSet extends ActionSet {
	public XConnectActionSet() {
		super.setActionSetId("xconnectActionSet");
		this.putAction(ActionConstants.REFRESHCONNECTIONS, RefreshModelConnectionsAction.class);
		this.refreshActions.add(ActionConstants.REFRESHCONNECTIONS);
	}

	@Override
	public List<String> getActionNames() {
		List<String> actionNames = new ArrayList<String>();
		actionNames.add(ActionConstants.REFRESHCONNECTIONS);
		return actionNames;
	}
}
