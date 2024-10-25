package example.org;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.printf("Hello world!");
        System.out.printf("How are you?");

        var scanner = new Scanner(System.in);
        var answer = scanner.nextLine();

        System.out.printf("User answered: " + answer);
        }
    }
