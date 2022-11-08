package annotations.processor;

import annotations.MyBean;
import com.google.auto.service.AutoService;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;
import java.util.LinkedHashSet;
import java.util.Set;

@AutoService(Processor.class)
public class BeanProcessor extends AbstractProcessor {

    private Types typeUtils;
    private Elements elementUtils;
    private Messager messager;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        typeUtils = processingEnv.getTypeUtils();
        elementUtils = processingEnv.getElementUtils();
        messager = processingEnv.getMessager();
    }

    /**
     * Support only MyBean annotation
     */
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> annotations = new LinkedHashSet<>();
        annotations.add(MyBean.class.getCanonicalName());
        return annotations;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    /**
     * Checks if MyBean observes rules
     */
    private void checkValidClass(BeanAnnotatedClass item) throws ProcessingException {

        // Cast to TypeElement -> has more type specific methods
        TypeElement classElement = item.getTypeElement();
        String className = classElement.getQualifiedName().toString();

        // 1. JavaBean class must be public
        if (!isClassPublic(classElement)) {
            throw new ProcessingException(classElement, "The bean class %s must be public.", className);
        }

        // 2. JavaBean cannot be abstract class
        if (isClassAbstract(classElement)) {
            throw new ProcessingException(classElement,
                    "The bean class %s cannot be abstract.", className);
        }

        // 3. JavaBean must have empty public constructor
        if (!isHaveEmptyConstructor(classElement)) {
            throw new ProcessingException(classElement,
                    "The bean class %s must provide an public empty default constructor.", className);
        }

        // 4. JavaBean must implements java.io.Serializable
        if (!isSerializable(classElement)) {
            throw new ProcessingException(classElement,
                    "The bean class %s must implements Serializable.", className);
        }

        // todo: check for getters and setters
    }

    private boolean isClassPublic(TypeElement classElement) {
        return classElement.getModifiers().contains(Modifier.PUBLIC);
    }

    private boolean isClassAbstract(TypeElement classElement) {
        return classElement.getModifiers().contains(Modifier.ABSTRACT);
    }

    private boolean isHaveEmptyConstructor(TypeElement classElement) {
        for (Element enclosed : classElement.getEnclosedElements()) {
            if (enclosed.getKind() == ElementKind.CONSTRUCTOR) {
                ExecutableElement constructorElement = (ExecutableElement) enclosed;
                if (constructorElement.getParameters().size() == 0 && constructorElement.getModifiers()
                        .contains(Modifier.PUBLIC)) {
                    // Found an empty public constructor
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isSerializable(TypeElement classElement) {
        TypeMirror serializable = elementUtils.getTypeElement("java.io.Serializable").asType();
        return typeUtils.isAssignable(classElement.asType(), serializable);
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

        try {
            // Scan classes
            for (Element annotatedElement : roundEnv.getElementsAnnotatedWith(MyBean.class)) {

                // Check if annotatedElement is a class
                if (annotatedElement.getKind() != ElementKind.CLASS) {
                    throw new ProcessingException(annotatedElement, "Only classes can be annotated with @%s.",
                            MyBean.class.getSimpleName());
                }

                TypeElement typeElement = (TypeElement) annotatedElement;

                BeanAnnotatedClass annotatedClass = new BeanAnnotatedClass(typeElement);
                checkValidClass(annotatedClass);
            }
        } catch (ProcessingException e) {
            error(e.getElement(), e.getMessage());
        }

        return true;
    }

    /**
     * Prints an error message
     *
     * @param e The element which has caused the error. Can be null
     * @param msg The error message
     */
    public void error(Element e, String msg) {
        messager.printMessage(Diagnostic.Kind.ERROR, msg, e);
    }
}