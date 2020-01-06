package lsieun.reflection.f_generic;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;

public class E_WildcardType {
    public static void main(String[] args) {
        //Get all the methods of the TestWildcardType class (in this case, the testWildcardType method)
        Method[] methods = ExampleE.class.getDeclaredMethods();
        for (Method method: methods){
            //Get all the parameter types of the method
            Type[] types = method.getGenericParameterTypes();
            for (Type paramsType: types){
                System.out.println("type: " + paramsType.toString());
                //If it is not a parameterized type, continue directly and execute the next loop condition
                if (!(paramsType instanceof ParameterizedType)){
                    continue;
                }
                //Strongly convert the current type to a parameterized type and obtain its actual parameter type (that is, generic type with wildcards)
                Type type = ((ParameterizedType) paramsType).getActualTypeArguments()[0];
                //Output whether it is a wildcard type
                System.out.println("type instanceof WildcardType : " +
                        ( type instanceof WildcardType));
                if (type instanceof WildcardType){
                    int lowIndex = ((WildcardType) type).getLowerBounds().length - 1;
                    int upperIndex = ((WildcardType) type).getUpperBounds().length - 1;
                    //Output upper and lower boundaries
                    System.out.println("getLowerBounds(): "
                            +
                            (lowIndex >= 0 ? ((WildcardType) type).getLowerBounds()[lowIndex] : "String ")
                            + "; getUpperBounds(): "
                            +
                            (upperIndex >=0 ? ((WildcardType) type).getUpperBounds()[upperIndex]:"Object"));
                }
            }
        }
    }
}
