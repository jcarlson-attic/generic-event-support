package com.methodknowledgy.events.dispatch.impl;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.methodknowledgy.events.Event;
import com.methodknowledgy.events.dispatch.IEventDispatcher;
import com.methodknowledgy.events.dispatch.Subscriber;

public class SimpleEventDispatcher implements IEventDispatcher {

    private static Set<Subscriber> subscribers = new HashSet<Subscriber>();

    public <E extends Event<?, ?>> void dispatch(E event) {
        for (Subscriber subscription : subscribers) {
            if (subscription.handles(event)) {
                subscription.handle(event);
            }
        }
    }

    public void register(Subscriber subscription) {
        subscribers.add(subscription);
    }

    public void unregister(Subscriber subscription) {
        subscribers.remove(subscription);
    }
    
    public void unregisterAllSubscribers() {
        subscribers.clear();
    }

    public Collection<Subscriber> getSubscriptions() {
        return subscribers;
    }

}
