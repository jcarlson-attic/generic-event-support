package com.methodknowledgy.events.dispatch;

import com.methodknowledgy.events.Event;

public interface Action {

	<E extends Event<?, ?>> void execute(E event);
	
}
