package com.methodknowledgy.events.dispatch;

import com.methodknowledgy.events.Event;

public class MockSubscriber implements Subscriber {

    public boolean handledEvent;

    public Action getAction() {
        return null;
    }

    public Filter getFilter() {
        return null;
    }

    public <E extends Event<?, ?>> void handle(E event) {
        handledEvent = true;
    }

    public <E extends Event<?, ?>> boolean handles(E event) {
        return true;
    }

}
