package beans;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.methodknowledgy.gcs.Bindable;
import com.methodknowledgy.gcs.GenericChangeListener;
import com.methodknowledgy.gcs.GenericChangeSupport;

public class SimpleBean implements Bindable<SimpleBean> {
    
    private static Log log = LogFactory.getLog(SimpleBean.class);

    private GenericChangeSupport<SimpleBean> changes = new GenericChangeSupport<SimpleBean>(this);
    
    private String title;

    public void setTitle(String title) { 
        log.info("Setting new value for 'title'");
        changes.firePropertyChange("title", this.title, this.title = title);
    }
    
    public String getTitle() {
        return title;
    }
    
    public void addChangeListener(String propertyName, GenericChangeListener<SimpleBean, ?> listener) {
        changes.addChangeListener(propertyName, listener);
    }
    
    public void addChangeListener(GenericChangeListener<SimpleBean, Object> listener) {
        changes.addChangeListener(listener);
    }

    public void removeChangeListener(String propertyName, GenericChangeListener<SimpleBean, ?> listener) {
        changes.removeChangeListener(propertyName, listener);
    }
    
    public void removeChangeListener(GenericChangeListener<SimpleBean, Object> listener) {
        changes.removeChangeListener(listener);
    }
}
