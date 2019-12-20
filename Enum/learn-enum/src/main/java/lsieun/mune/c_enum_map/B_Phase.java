package lsieun.mune.c_enum_map;

import lsieun.mune.c_enum_map.bean.Phase;

public class B_Phase {
    public static void main(String[] args) {
        Phase.Transition transition = Phase.Transition.from(Phase.SOLID, Phase.LIQUID);
        System.out.println(transition);
    }
}
