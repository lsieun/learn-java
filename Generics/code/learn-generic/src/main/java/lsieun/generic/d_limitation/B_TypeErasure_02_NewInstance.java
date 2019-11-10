package lsieun.generic.d_limitation;

public class B_TypeErasure_02_NewInstance {

    public <T> void performAction(final T action) throws IllegalAccessException, InstantiationException {
        String instance = String.class.newInstance();

        //T obj = T.class.newInstance();
    }

}
