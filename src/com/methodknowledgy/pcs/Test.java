package com.methodknowledgy.pcs;

public class Test {

    private PropertyChangeSupport<Test> changes = new PropertyChangeSupport<Test>();
    
    private String title;
    
    public Test() {
        
    }
    
    public void setTitle(String title) { 
        changes.firePropertyChange("title", this.title, this.title = title);
    }
    
    public String getTitle() {
        return title;
    }
    
    public void addTitleListener(PropertyChangeListener<Test, String> listener) {
        changes.addChangeListener(listener);
    }
    
    
    
    public static void main(String[] args) {
        Test test = new Test();
        PropertyChangeListener<Test, String> l = new PropertyChangeListener<Test, String>() {

            public void onChange(PropertyChangeEvent<Test, String> change) {
                String v = change.getNewValue();
                System.out.println(v);
            }

        };
        test.addTitleListener(l);
        test.setTitle("Something new");
    }
    
}
