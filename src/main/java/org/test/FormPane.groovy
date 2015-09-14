package org.test

import com.vaadin.ui.FormLayout

/**
 * Created by Tamás on 2015. 09. 14..
 */
class FormPane extends FormLayout{
    private List<Object> components

    public FormPane(Object[] components) {
        this.components = components
        addComponents()
        setMargin(true)
        setSpacing(true)
    }

    private void addComponents() {
        for(Object o : components) {
            addComponent(o);
        }
    }

    public Object removeComponent(Object o) {
        if(components.contains(o)) {
            components.remove(o)
            return o
        }
        return -1
    }
}
