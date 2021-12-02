package machine;

import java.util.Scanner;

public class CoffeeMachine {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int money = 550;
        int water = 400;
        int milk = 540;
        int beans = 120;
        int cups = 9;
        boolean exit = false;

        while (!exit) {
            System.out.println("Write action (buy, fill, take, remaining, exit): ");
            final String action = sc.next();
            System.out.println();

            switch (action) {
                case "buy":
                    System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, "
                            + "back - to main menu: ");
                    final String choice = sc.next();
                    int waterUsed = 0;
                    int milkUsed = 0;
                    int beansUsed = 0;
                    int cupsUsed = 0;
                    int price = 0;

                    switch (choice) {
                        case "1":
                            waterUsed = 250;
                            beansUsed = 16;
                            price = 4;
                            cupsUsed = 1;
                            break;
                        case "2":
                            waterUsed = 350;
                            milkUsed = 75;
                            beansUsed = 20;
                            price = 7;
                            cupsUsed = 1;
                            break;
                        case "3":
                            waterUsed = 200;
                            milkUsed = 100;
                            beansUsed = 12;
                            price = 6;
                            cupsUsed = 1;
                            break;
                        case "back":
                            break;
                    }

                    String errorText = "";
                    if (waterUsed > water) {
                        errorText = "water";
                    } else if (milkUsed > milk) {
                        errorText = "milk";
                    } else if (beansUsed > beans) {
                        errorText = "coffee beans";
                    } else if (cupsUsed > cups) {
                        errorText = "disposable cups";
                    }

                    if (errorText.equals("")) {
                        System.out.println("I have enough resources, making you a coffee!");
                        water -= waterUsed;
                        milk -= milkUsed;
                        beans -= beansUsed;
                        cups -= cupsUsed;
                        money += price;
                    } else {
                        System.out.println("Sorry, not enough " + errorText + "!");
                    }

                    break;

                case "fill":
                    System.out.println("\nWrite how many ml of water you want to add: ");
                    water += sc.nextInt();
                    System.out.println("Write how many ml of milk you want to add: ");
                    milk += sc.nextInt();
                    System.out.println("Write how many grams of coffee beans you want to add: ");
                    beans += sc.nextInt();
                    System.out.println("Write how many disposable cups of coffee you want to add: ");
                    cups += sc.nextInt();
                    break;

                case "take":
                    System.out.println("I gave you: $" + money);
                    money = 0;
                    break;

                case "remaining":
                    System.out.println("The coffee machine has:\n" + water + " ml of water\n" + milk + " ml of milk\n"
                            + beans + " g of coffee beans\n" + cups + " disposable cups\n$" + money + " of money");
                    break;

                case "exit":
                    exit = true;
                    break;
            }
            System.out.println();
        }
    }
}
