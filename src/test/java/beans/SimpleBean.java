package beans;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.methodknowledgy.events.Bindable;
import com.methodknowledgy.events.ChangeListener;
import com.methodknowledgy.events.ChangeSupport;

public class SimpleBean implements Bindable<SimpleBean> {
    
    private static Log log = LogFactory.getLog(SimpleBean.class);

    private ChangeSupport<SimpleBean> changes = new ChangeSupport<SimpleBean>(this);
    
    private String title;

    public void setTitle(String title) { 
        log.info("Setting new value for 'title'");
        changes.firePropertyChange("title", this.title, this.title = title);
    }
    
    public String getTitle() {
        return title;
    }
    
    public void addChangeListener(String propertyName, ChangeListener<SimpleBean, ?> listener) {
        changes.addChangeListener(propertyName, listener);
    }
    
    public void addChangeListener(ChangeListener<SimpleBean, Object> listener) {
        changes.addChangeListener(listener);
    }

    public void removeChangeListener(String propertyName, ChangeListener<SimpleBean, ?> listener) {
        changes.removeChangeListener(propertyName, listener);
    }
    
    public void removeChangeListener(ChangeListener<SimpleBean, Object> listener) {
        changes.removeChangeListener(listener);
    }
}
