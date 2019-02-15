package lsieun.chat;

import java.util.ArrayList;
import java.util.List;

import lsieun.utils.FileUtils;
import lsieun.utils.IOUtils;

public class ChatUtils {
    public static List<Talker> getTalkerList() {
        List<Talker> talkerList = new ArrayList();
        talkerList.add(new Talker(1, "唐太宗"));
        talkerList.add(new Talker(2, "魏征"));
        return talkerList;
    }

    public static List<Say> getSayList() {
        List<Say> sayList = new ArrayList();
        sayList.add(new Say(1,1, "人主何为而明，何为而暗?"));
        sayList.add(new Say(2,2, "兼听则明，偏听则暗。"));
        sayList.add(new Say(3,1, "作为皇帝怎样才能做到明察秋毫，而不被臣下所蒙蔽？"));
        sayList.add(new Say(4,2, "多听取各方面的意见就会明察秋毫，偏听偏信就会被人蒙蔽。"));
        return sayList;
    }

    public static void main(String[] args) {
        System.out.println("===========================");
        System.out.println(Talker.PATTERN);
        List<Talker> talkerList = getTalkerList();
        printList(talkerList);
        System.out.println("===========================");


        System.out.println(Say.PATTERN);
        List<Say> sayList = getSayList();
        printList(sayList);
        System.out.println("===========================");

        String filepath = FileUtils.getFilePath("XXX.bin");
        System.out.println("filepath = " + filepath);
        System.out.println("===========================");
    }


    public static void printList(List list) {
        for(int i=0; i<list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
}
