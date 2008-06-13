package com.methodknowledgy.gcs;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import beans.TestBean;

public class ChangeTest {

    private boolean passTest;
    
    @Before
    public void setup() {
        passTest = false;
    }
    
    @Test
    @Ignore
    public void objectListenerTest() {
        TestBean b = new TestBean();
        
        GenericChangeListener<TestBean, Object> l = new GenericChangeListener<TestBean, Object>() {

            public void onChange(GenericChangeEvent<TestBean, Object> change) {
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
        TestBean b = new TestBean();
        
        b.addChangeListener(new GenericChangeListener<TestBean, Object>() {

            public void onChange(GenericChangeEvent<TestBean, Object> change) {
            }

            public Class<Object> clazz() {
                return Object.class;
            }
            
        });
        b.addChangeListener("title", new GenericChangeListener<TestBean, String>() {

            public void onChange(GenericChangeEvent<TestBean, String> change) {
            }

            public Class<String> clazz() {
                return String.class;
            }
            
        });
        b.setTitle("New title");
        passTest = true;
        Assert.assertTrue(passTest);
    }
    
}
