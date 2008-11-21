package com.methodknowledgy.events.dispatch.impl;

import java.util.ArrayList;
import java.util.List;

import com.methodknowledgy.events.Event;
import com.methodknowledgy.events.dispatch.EventDispatcher;
import com.methodknowledgy.events.dispatch.Subscriber;

public class SimpleEventDispatcher extends EventDispatcher {

    private static List<Subscriber> subscribers = new ArrayList<Subscriber>();

    /**
     * Calling this constructor will cause this implementation to set itself as
     * the default EventDispatcher automatically.
     */
    public SimpleEventDispatcher() {
        this(true);
    }

    /**
     * Calling this constructor will allow the caller to decide whether this
     * implementation should register itself as the default EventDispatcher or
     * simply create and return an instance of itself.
     * 
     * @param setAsEventDispatcher
     *            <code>true</code> if this should become the default
     *            EventDispatcher.
     */
    public SimpleEventDispatcher(boolean setAsEventDispatcher) {
        super(setAsEventDispatcher);
    }

    @Override
    protected <E extends Event<?, ?>> void dispatchImpl(E event) {
        for (Subscriber subscription : subscribers) {
            if (subscription.handles(event)) {
                subscription.handle(event);
            }
        }
    }

    @Override
    protected void registerImpl(Subscriber subscription) {
        subscribers.add(subscription);
    }

    @Override
    protected void unregisterImpl(Subscriber subscription) {
        subscribers.remove(subscription);
    }

    @Override
    protected List<Subscriber> getSubscriptionsImpl() {
        return subscribers;
    }

}
