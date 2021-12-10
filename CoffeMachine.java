package machine;

import java.util.Scanner;

public class CoffeeMachine {

  enum Status {
    MAIN, BUY, FILL_WATER, FILL_MILK, FILL_BEANS, FILL_CUPS
  }

  int money;
  int water;
  int milk;
  int beans;
  int cups;
  Status presentStatus;

  public CoffeeMachine(int money, int water, int milk, int beans, int cups) {
    this.money = money;
    this.water = water;
    this.milk = milk;
    this.beans = beans;
    this.cups = cups;
    this.presentStatus = Status.MAIN;
  }

  public CoffeeMachine() {
    this(550, 400, 540, 120, 9);
  }

  void takeMoney() {
    System.out.println("\nI gave you: $" + this.money);
    this.money = 0;
  }

  void printMachineInfo() {
    System.out.println("The coffee machine has:\n" + water + " ml of water\n" + milk + " ml of milk\n"
            + beans + " g of coffee beans\n" + cups + " disposable cups\n$" + money + " of money");
  }

  void makeCoffee(String choice) {
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
      default:
        return;
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
  }

  boolean userCommand(String userInput) {
    boolean quit = false;

    switch (this.presentStatus) {
      case MAIN:
        switch (userInput) {
          case "buy":
            this.presentStatus = Status.BUY;
            System.out.println("\nWhat do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, "
                    + "back - to main menu: ");
            break;

          case "fill":
            this.presentStatus = Status.FILL_WATER;
            System.out.println("\nWrite how many ml of water you want to add: ");
            break;

          case "take":
            takeMoney();
            break;

          case "remaining":
            System.out.println();
            printMachineInfo();
            break;

          case "exit":
            quit = true;
            break;

          default:
            break;
        }
        break;

      case BUY:
        makeCoffee(userInput);
        this.presentStatus = Status.MAIN;
        break;

      case FILL_WATER:
        this.water += Integer.parseInt(userInput);
        this.presentStatus = Status.FILL_MILK;
        System.out.println("Write how many ml of milk you want to add: ");
        break;

      case FILL_MILK:
        this.milk += Integer.parseInt(userInput);
        this.presentStatus = Status.FILL_BEANS;
        System.out.println("Write how many grams of coffee beans you want to add: ");
        break;

      case FILL_BEANS:
        this.beans += Integer.parseInt(userInput);
        this.presentStatus = Status.FILL_CUPS;
        System.out.println("Write how many disposable cups of coffee you want to add: ");
        break;

      case FILL_CUPS:
        this.cups += Integer.parseInt(userInput);
        this.presentStatus = Status.MAIN;
        break;
    }
    return quit;
  }

  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);
    CoffeeMachine machine = new CoffeeMachine();
    boolean exit = false;


    do {
      if(machine.presentStatus == Status.MAIN) {
        System.out.println("\nWrite action (buy, fill, take, remaining, exit): ");
      }
      exit = machine.userCommand(sc.next());
    } while (!exit && sc.hasNextLine());
  }

}
