package lsieun.reflection.f_generic;

import lsieun.reflection.f_generic.bean.Example_Judge_Method;

import java.lang.reflect.Method;
import java.lang.reflect.TypeVariable;
import java.util.Arrays;

public class Judge_Generic_Method {
    public static void main(String[] args) throws NoSuchMethodException {
        Class<?> clazz = Example_Judge_Method.class;
        String methodName = "test_generic";
        Class<?>[] paramTypes = new Class<?>[] {Object.class};

        Method method = clazz.getDeclaredMethod(methodName, paramTypes);
        TypeVariable<?>[] typeParameters = method.getTypeParameters();
        if (typeParameters != null && typeParameters.length > 0) {
            System.out.println(method.getName()+" is a GENERIC METHOD");
        }
        else {
            System.out.println(method.getName() +" is a NON-GENERIC METHOD");
        }
    }
}
