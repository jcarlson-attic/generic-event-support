package com.methodknowledgy.events.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.methodknowledgy.events.Event;
import com.methodknowledgy.events.dispatch.Action;
import com.methodknowledgy.events.dispatch.impl.Subscription;

public class AutoEventLogger extends Subscription {

    private Log log = LogFactory.getLog(AutoEventLogger.class);

    public AutoEventLogger() {
        setAction( new Action() {
            public <E extends Event<?, ?>> void execute(E event) {
                log.info(event.toString());
            }
        });
    }

}
