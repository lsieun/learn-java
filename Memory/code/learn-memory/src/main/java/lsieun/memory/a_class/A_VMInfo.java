package lsieun.memory.a_class;

import org.openjdk.jol.vm.VM;
import org.openjdk.jol.vm.VirtualMachine;

public class A_VMInfo {
    public static void main(String[] args) {
        VirtualMachine vm = VM.current();
        String details = vm.details();
        System.out.println(details);
    }
}
