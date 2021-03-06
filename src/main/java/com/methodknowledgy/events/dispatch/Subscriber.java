package com.methodknowledgy.events.dispatch;

import com.methodknowledgy.events.Event;

public interface Subscriber {

	Filter getFilter();
	
	Action getAction();
	
	<E extends Event<?, ?>> boolean handles(E event);
	
	<E extends Event<?, ?>> void handle(E event);
	
}
