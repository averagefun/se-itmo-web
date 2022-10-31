package validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("validatorX")
public class ValidatorX implements Validator {
    private static final float MAX_X = 3;
    private static final float MIN_X = -5;

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        if (o == null) {
            throw new ValidatorException(
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, null,
                            "Поле X не может быть пустым!"));
        }
        double yValue = Double.parseDouble(String.valueOf(o));
        if (yValue < MIN_X || yValue > MAX_X) throw new ValidatorException(
                new FacesMessage(FacesMessage.SEVERITY_ERROR, null,
                        String.format("Значение X должно находиться в отрезке [%s ... %s].", MIN_X, MAX_X))
        );
    }
}