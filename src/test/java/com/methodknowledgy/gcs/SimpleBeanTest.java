package com.methodknowledgy.gcs;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import beans.SimpleBean;

public class SimpleBeanTest {

    private boolean passTest;
    
    @Before
    public void setup() {
        passTest = false;
    }
    
    @Test
    public void objectLevelListenerTest() {
        SimpleBean b = new SimpleBean();
        
        GenericChangeListener<SimpleBean, Object> l = new GenericChangeListener<SimpleBean, Object>() {

            public void onChange(GenericChangeEvent<SimpleBean, Object> change) {
                Assert.assertEquals(null, change.getOldValue());
                Assert.assertEquals("New Title", change.getNewValue());
                passTest = true;
            }

            public Class<Object> clazz() {
                return Object.class;
            }
            
        };
        
        b.addChangeListener(l);
        b.setTitle("New Title");
        Assert.assertTrue(passTest);
    }
    
    @Test
    public void listenerRegistrationTest() {
        SimpleBean b = new SimpleBean();
        
        b.addChangeListener(new GenericChangeListener<SimpleBean, Object>() {

            public void onChange(GenericChangeEvent<SimpleBean, Object> change) {
            }

            public Class<Object> clazz() {
                return Object.class;
            }
            
        });
        b.addChangeListener("title", new GenericChangeListener<SimpleBean, String>() {

            public void onChange(GenericChangeEvent<SimpleBean, String> change) {
            }

            public Class<String> clazz() {
                return String.class;
            }
            
        });
        b.setTitle("New title");
        passTest = true;
        Assert.assertTrue(passTest);
    }
    
    @Test
    public void listenerRemovalTest() {
    	SimpleBean b = new SimpleBean();
    	GenericChangeListener<SimpleBean, Object> listener = new GenericChangeListener<SimpleBean, Object>() {
            public void onChange(GenericChangeEvent<SimpleBean, Object> change) {
            	Assert.fail();
            }
            public Class<Object> clazz() {
                return Object.class;
            }
        };
        
        b.addChangeListener(listener);
        b.addChangeListener("title", listener);
        b.removeChangeListener(listener);
        b.removeChangeListener("title", listener);
        b.setTitle("New Title");
    }
    
    @Test
    public void multipleRegistrationsTest() {
    	
    }
    
    @Test
    public void incorrectSourceTypeTest() {
    	SimpleBean b = new SimpleBean();
    	GenericChangeListener<SimpleBean, Integer> listener = new GenericChangeListener<SimpleBean, Integer>() {
            public void onChange(GenericChangeEvent<SimpleBean, Integer> change) {
            	Assert.assertNotNull(change.getNewValue());
            	passTest = true;
            }
            public Class<Integer> clazz() {
                return Integer.class;
            }
        };
        b.addChangeListener("title", listener);
        
        b.setTitle("New Title");
        Assert.assertFalse(passTest);
        
        // TODO: What happens when you register for notification on an interface, but an impl sub-class is changed?
    }
}
