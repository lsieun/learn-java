package lsieun.sys;


import java.io.Console;
import java.util.Scanner;

public class ConsoleRead {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String username = scanner.nextLine();
        System.out.println(username);
    }
}
