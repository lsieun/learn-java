package lsieun.java8.functional_thinking;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TheCompanyProcess {
    public String cleanNames_imperative(List<String> listOfNames) {
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < listOfNames.size(); i++) {
            if (listOfNames.get(i).length() > 1) {
                result.append(capitalize(listOfNames.get(i))).append(",");
            }
        }
        return result.substring(0, result.length() - 1).toString();
    }

    public String cleanNames(List<String> names) {
        if (names == null) return "";
        return names
                .stream()
                .filter(name -> name.length() > 1)
                .map(name -> capitalize(name))
                .collect(Collectors.joining(","));
    }

    private String capitalize(String e) {
        return e.substring(0, 1).toUpperCase() + e.substring(1, e.length());
    }

    public static void main(String[] args) {
        String[] employees = new String[]{"neal", "s", "stu", "j", "rich", "bob", "aiden", "j", "ethan",
                "liam", "mason", "noah", "lucas", "jacob", "jayden", "jack"};
        List<String> list = Arrays.asList(employees);

        TheCompanyProcess instance = new TheCompanyProcess();
        String result1 = instance.cleanNames_imperative(list);
        System.out.println(result1);

    }
}
