
package classes.converters;

import classes.model.Vesture;
import java.lang.annotation.Annotation;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;
import javax.persistence.Converter;

@FacesConverter(value="vestureConverter", forClass=Vesture.class)
public class VestureConverter implements Converter {
    
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Vesture vesture = new Vesture();
        vesture.setDescription(value);
        return vesture;
    }

    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return ((Vesture) value).getId().toString();
    }

    @Override
    public boolean autoApply() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}