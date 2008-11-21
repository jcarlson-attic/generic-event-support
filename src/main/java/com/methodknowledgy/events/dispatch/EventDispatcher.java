package com.methodknowledgy.events.dispatch;

import java.util.List;

import com.methodknowledgy.events.Event;
import com.methodknowledgy.events.dispatch.impl.SimpleEventDispatcher;
import com.methodknowledgy.events.impl.ChangeEvent;

public abstract class EventDispatcher {

    private static EventDispatcher instance;

    /**
     * Generally speaking, an application will only ever have one active
     * EventDispatcher. When an EventDispatcher is created, it should generally
     * register itself as the default by passing <code>true</code> to this
     * constructor. However, since in some instances, applications may wish to
     * have multiple EventDispatchers, this behavior can be customized.
     * 
     * @param setAsEventDispatcher
     *            <code>true</code> if this instance of an EventDispatcher
     *            should become the default EventDispatcher.
     */
    protected EventDispatcher(boolean setAsEventDispatcher) {
        if (setAsEventDispatcher) {
            setInstance(this);
        }
    }

    /**
     * @return The active EventDispatcher, or a new SimpleEventDispatcher if no
     *         other EventDispatcher has been created.
     */
    public static final EventDispatcher getInstance() {
        if (instance == null) {
            setInstance(new SimpleEventDispatcher());
        }
        return instance;
    }

    /**
     * <p>
     * An application can provide a customized implementation of the
     * EventDispatcher, although the SimpleEventDispatcher will work for most
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
     * @param instance
     */
    public static final void setInstance(EventDispatcher instance) {
        if (EventDispatcher.instance != null) {
            for (Subscriber subscription : getInstance().getSubscriptions()) {
                instance.registerImpl(subscription);
            }
        }
        EventDispatcher.instance = instance;
    }

    public final void register(Subscriber subscription) {
        registerImpl(subscription);
    }

    public final void unregister(Subscriber subscription) {
        unregisterImpl(subscription);
    }

    public final <E extends Event<?, ?>> void dispatch(E event) {
        dispatchImpl(event);
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

    public final List<Subscriber> getSubscriptions() {
        return getSubscriptionsImpl();
    }

    protected abstract void registerImpl(Subscriber subscription);

    protected abstract void unregisterImpl(Subscriber subscription);

    protected abstract List<Subscriber> getSubscriptionsImpl();

    protected abstract <E extends Event<?, ?>> void dispatchImpl(E event);

}
