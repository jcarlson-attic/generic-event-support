package beans;

import com.methodknowledgy.gcs.GenericChangeListener;
import com.methodknowledgy.gcs.GenericChangeSupport;

public class TestBean {

    private GenericChangeSupport<TestBean> changes = new GenericChangeSupport<TestBean>(this);
    
    private String title;

    public void setTitle(String title) { 
        changes.firePropertyChange("title", this.title, this.title = title);
    }
    
    public String getTitle() {
        return title;
    }
    
    public void addChangeListener(String propertyName, GenericChangeListener<TestBean, ?> listener) {
        changes.addChangeListener(propertyName, listener);
    }
    
    public void addChangeListener(GenericChangeListener<TestBean, Object> listener) {
        changes.addChangeListener(listener);
    }

}
