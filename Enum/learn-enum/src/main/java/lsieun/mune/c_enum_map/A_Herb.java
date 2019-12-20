package lsieun.mune.c_enum_map;

import lsieun.mune.c_enum_map.bean.Herb;

import java.util.EnumMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class A_Herb {
    private static Herb[] garden = new Herb[9];

    static {
        Herb.Type[] values = Herb.Type.values();
        for (int i = 0; i < garden.length; i++) {
            Herb.Type t = values[i % 3];
            String name = String.format("%s_%03d", t, i + 1);
            garden[i] = new Herb(name, t);
        }
    }

    public static void main(String[] args) {

        // Using an EnumMap to associate data with an enum
        Map<Herb.Type, Set<Herb>> herbsByType = new EnumMap<>(Herb.Type.class);
        for (Herb.Type t : Herb.Type.values()) {
            herbsByType.put(t, new HashSet<>());
        }

        for (Herb h : garden) {
            herbsByType.get(h.getType()).add(h);
        }

        System.out.println(herbsByType);
    }
}
