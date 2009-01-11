package com.methodknowledgy.events.dispatch;

import java.util.Collection;

import com.methodknowledgy.events.Event;
import com.methodknowledgy.events.dispatch.impl.SimpleEventDispatcher;
import com.methodknowledgy.events.impl.ChangeEvent;

public final class EventDispatcher implements IEventDispatcher {

	private static EventDispatcher instance;
	private IEventDispatcher impl;

	/**
	 * Generally speaking, an application will only ever have one active
	 * EventDispatcher. However, in some instances, applications may wish to
	 * have multiple EventDispatchers.
	 * 
	 */
	private EventDispatcher() {
	}

	/**
	 * @return The active EventDispatcher, or a new SimpleEventDispatcher if no
	 *         other EventDispatcher has been created.
	 */
	public static final EventDispatcher getInstance() {
		if (instance == null) {
			instance = new EventDispatcher();
		}
		return instance;
	}

	private final IEventDispatcher getImplementation() {
		if (impl == null) {
			impl = new SimpleEventDispatcher();
		}
		return impl;
	}

	/**
	 * <p>
	 * An application can provide a customized implementation of the
	 * IEventDispatcher, although the SimpleEventDispatcher will work for most
	 * small to medium sized applications.
	 * </p>
	 * 
	 * <p>
	 * As a side note, if an EventDispatcher instance already exists, all
	 * existing Subscriptions registered with it will be transferred to the new
	 * instance. In this way, new implementations can be swapped in at any time
	 * without fear of losing existing Subscriptions that may have occurred in
	 * advance of the new instance registration.
	 * </p>
	 * 
	 * @param impl
	 */
	public final void setImplementation(IEventDispatcher impl) {
		if (this.impl != null) {
			for (Subscriber subscription : this.impl.getSubscriptions()) {
				impl.register(subscription);
			}
		}
		this.impl = impl;
	}

	public final void register(Subscriber subscription) {
		getImplementation().register(subscription);
	}

	public final void unregister(Subscriber subscription) {
		getImplementation().unregister(subscription);
	}

	public final void unregisterAllSubscribers() {
		getImplementation().unregisterAllSubscribers();
	}

	public final <E extends Event<?, ?>> void dispatch(E event) {
		getImplementation().dispatch(event);
	}

	public final <S, T> void dispatch(S source, String propertyName,
			T oldValue, T newValue) {
		dispatch(source, propertyName, oldValue, newValue, null);
	}

	public final <S, T> void dispatch(S source, String propertyName,
			T oldValue, T newValue, String message) {
		ChangeEvent<S, T> event = new ChangeEvent<S, T>(source, propertyName,
				oldValue, newValue, message);
		dispatch(event);
	}

	public final Collection<Subscriber> getSubscriptions() {
		return getImplementation().getSubscriptions();
	}

	public static final void reset() {
		getInstance().unregisterAllSubscribers();
		getInstance().impl = null;
	}

}
