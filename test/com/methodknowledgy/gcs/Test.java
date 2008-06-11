package com.methodknowledgy.gcs;

public class Test {

    private GenericChangeSupport<Test> changes = new GenericChangeSupport<Test>();
    
    private String title;
    
    public Test() {
        
    }
    
    public void setTitle(String title) { 
        changes.firePropertyChange("title", this.title, this.title = title);
    }
    
    public String getTitle() {
        return title;
    }
    
    public void addTitleListener(GenericChangeListener<Test, String> listener) {
        changes.addChangeListener(listener);
    }
    
    
    
    public static void main(String[] args) {
        Test test = new Test();
        GenericChangeListener<Test, String> l = new GenericChangeListener<Test, String>() {

            public void onChange(GenericChangeEvent<Test, String> change) {
                String v = change.getNewValue();
                System.out.println(v);
            }

        };
        test.addTitleListener(l);
        test.setTitle("Something new");
    }
    
}
