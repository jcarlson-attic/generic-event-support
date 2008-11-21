package com.methodknowledgy.events.dispatch;


import java.util.Collection;

import com.methodknowledgy.events.Event;

public interface IEventDispatcher {

    void register(Subscriber subscription);

    void unregister(Subscriber subscription);

    void unregisterAllSubscribers();

    <E extends Event<?, ?>> void dispatch(E event);

    Collection<Subscriber> getSubscriptions();
    
}
