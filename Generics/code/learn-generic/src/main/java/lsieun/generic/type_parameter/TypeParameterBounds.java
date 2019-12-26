package lsieun.generic.type_parameter;

import java.util.List;
import java.util.Map;

public class TypeParameterBounds {
    // primitive type
//    class X0 <T extends int> {  }      // error

    // array
//    class X1 <T extends Object[]> {  } // error

    // Class
    class X2 <T extends Number> {  }
    class X3 <T extends String> {  }

    // Interface
    class X4 <T extends Runnable> {  }

    // Enum
    class X5 <T extends Thread.State> {  }

    // Generic Type
    class X6 <T extends List> {  } // raw type
    class X7 <T extends List<String>> {  } // concrete parameterized type
    class X8 <T extends List<? extends Number>> {  } // upper bounded wildcard parameterized type
    class X9 <T extends Comparable<? super Number>> {  } // lower bounded wildcard parameterized type
    class X10<T extends Map.Entry<?,?>> {  } // unbounded wildcard parameterized type
}
