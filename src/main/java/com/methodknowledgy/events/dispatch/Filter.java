package com.methodknowledgy.events.dispatch;

import com.methodknowledgy.events.Event;

public interface Filter {

	/**
	 * The Filter determines whether a given scenario should be dispatched to a
	 * particular Action. The sole job of the Filter is to filter down incoming
	 * Events and allow only matching Events to pass through to the Action
	 * associated with this Subscription.
	 * 
	 * @param <T>
	 * @param source
	 * @param event
	 * @param action
	 * @return True if the Event should propagate through. False if the Event
	 *         should be ignored. Null if the Filter cannot decide and chooses
	 *         to abstain (in which case the Subscription should have a default
	 *         decision for abstaining Filters.
	 */
	<E extends Event<?, ?>> Boolean isMatch(E event, Action action);

}
