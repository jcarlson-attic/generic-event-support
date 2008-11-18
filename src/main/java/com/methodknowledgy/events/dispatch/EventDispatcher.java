package com.methodknowledgy.events.dispatch;

import java.util.ArrayList;
import java.util.List;

import com.methodknowledgy.events.Event;
import com.methodknowledgy.events.impl.ChangeEvent;

public class EventDispatcher {

	private static List<Subscription> instance = new ArrayList<Subscription>();

	private EventDispatcher() {
	}

	public static void register(Subscription subscription) {
		instance.add(subscription);
	}

	public static void unregister(Subscription subscription) {
		instance.remove(subscription);
	}

	public static <E extends Event<?, ?>> void dispatch(E event) {
		for (Subscription subscription : instance) {
		    if (subscription.handles(event)) {
		        subscription.handle(event);
		    }
		}
	}

	public static <S, T> void dispatch(S source, String propertyName, T oldValue, T newValue) {
		dispatch(source, propertyName, oldValue, newValue, null);
	}

	public static <S, T> void dispatch(S source, String propertyName, T oldValue, T newValue, String message) {
		ChangeEvent<S, T> event = new ChangeEvent<S, T>(
				source, propertyName, oldValue, newValue, message);
		dispatch(event);
	}
}
