package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Please enter how much fuel does your car consume.");

        var scanner = new Scanner(System.in);
        var fuelConsumption = scanner.nextDouble();

        while(true) {
            System.out.println("Please enter how many kilometers did your car ride.");
            var distance = scanner.nextDouble();

            if(distance == 0)
            {
                break;
            }
            var result = distance/100*fuelConsumption;
            System.out.println("Your car consumed: " + result);
        }
    }
}
