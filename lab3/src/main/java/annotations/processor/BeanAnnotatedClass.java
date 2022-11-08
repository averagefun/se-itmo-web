package annotations.processor;

import javax.lang.model.element.TypeElement;

public class BeanAnnotatedClass {
    private final TypeElement annotatedClassElement;

    public BeanAnnotatedClass(TypeElement classElement) {
        this.annotatedClassElement = classElement;
    }

    /**
     * The original element that was annotated with @MyBean
     */
    public TypeElement getTypeElement() {
        return annotatedClassElement;
    }
}
