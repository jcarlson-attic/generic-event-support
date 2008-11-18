package com.methodknowledgy.events.dispatch;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.methodknowledgy.events.Event;

public class SubscriptionImplTest {

	@Before
	public void createMockObjects() {
	}
	
	@Test
	public void subscription_with_positive_filter() {
		SubscriptionImpl s = new SubscriptionImpl();
		s.setFilter(getTrueFilter());
		Assert.assertTrue(s.handles(null));
	}
	
	@Test
	public void subscription_with_negative_filter() {
		SubscriptionImpl s = new SubscriptionImpl();
		s.setFilter(getFalseFilter());
		Assert.assertFalse(s.handles(null));
	}
	
	@Test
	public void subscription_with_abstaining_filter() {
		SubscriptionImpl s = new SubscriptionImpl();
		s.setFilter(getNullFilter());
		
		s.setHandleOnFilterAbstain(true);
		Assert.assertTrue(s.handles(null));

		s.setHandleOnFilterAbstain(false);
		Assert.assertFalse(s.handles(null));
	}
	
	@Test
	public void subscription_with_no_filter() {
		SubscriptionImpl s = new SubscriptionImpl();
		Assert.assertTrue(s.handles(null));
	}
	
	@Test
	public void execute_when_valid() {
		SubscriptionImpl s = new SubscriptionImpl();
		TestAction action = new TestAction();
		s.setAction(action);
		s.handle(null);
		Assert.assertTrue(action.executed);
	}
	
	@Test
	public void execute_when_not_valid() {
		SubscriptionImpl s = new SubscriptionImpl();
		TestAction action = new TestAction();
		s.setAction(action);
		s.setFilter(getFalseFilter());
		if (s.handles(null)) {
	        s.handle(null);
		}
		Assert.assertFalse(action.executed);
	}
	
	
	
	
	
	private Filter getTrueFilter() {
		return new Filter() {
			public <E extends Event<?, ?>> Boolean isMatch(E event, Action action) {
				return Boolean.TRUE;
			}
		};
	}
	
	private Filter getFalseFilter() {
		return new Filter() {
			public <E extends Event<?, ?>> Boolean isMatch(E event, Action action) {
				return Boolean.FALSE;
			}
		};
	}
	
	private Filter getNullFilter() {
		return new Filter() {
			public <E extends Event<?, ?>> Boolean isMatch(E event, Action action) {
				return null;
			}
		};
	}
	
	private class TestAction implements Action {
		public boolean executed = false;
		public <E extends Event<?, ?>> void execute(E event) {
			executed = true;
		}
	}
	
}
