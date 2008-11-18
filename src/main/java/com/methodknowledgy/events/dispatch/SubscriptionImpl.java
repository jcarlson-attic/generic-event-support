package com.methodknowledgy.events.dispatch;

import com.methodknowledgy.events.Event;


public class SubscriptionImpl implements Subscription {

	private Action action;
	private Filter filter;
	private boolean handleOnFilterAbstain;

	public <E extends Event<?, ?>> boolean handles(E event) {
		if (filter == null) {
			return true;
		}
		Boolean match = filter.isMatch(event, action);
		if (match == null) {
			return handleOnFilterAbstain;
		}
		return match;
	}

	public <E extends Event<?, ?>> void handle(E event) {
		if (action != null) {
			action.execute(event);
		}
	}

	public boolean getHandleOnFilterAbstain() {
		return handleOnFilterAbstain;
	}

	public void setHandleOnFilterAbstain(boolean handleOnFilterAbstain) {
		this.handleOnFilterAbstain = handleOnFilterAbstain;
	}

	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}

	public Filter getFilter() {
		return filter;
	}

	public void setFilter(Filter filter) {
		this.filter = filter;
	}

}
