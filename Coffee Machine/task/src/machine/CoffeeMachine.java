package machine;

import java.util.Scanner;

public class CoffeeMachine {
    static Scanner scanner = new Scanner(System.in);
    static int water = 400;
    static int milk = 540;
    static int coffee = 120;
    static int disposableCups = 9;
    static int money = 550;

    interface CoffeeActions {
        public void buyCupOfTheCoffee();
    }

    static class CoffeeType implements CoffeeActions {
        int waterPerCup = 0;
        int milkPerCup = 0;
        int coffeePerCup = 0;
        int costPerCup = 0;

        public void buyCupOfTheCoffee() {
            water -= this.waterPerCup;
            milk -= this.milkPerCup;
            coffee -= this.coffeePerCup;
            disposableCups--;
            money += this.costPerCup;
        }
    }

    static class Espresso extends CoffeeType {
        public Espresso() {
            this.waterPerCup = 250;
            this.milkPerCup = 0;
            this.coffeePerCup = 16;
            this.costPerCup = 4;
        }
    }

    static class Latte extends CoffeeType {
        public Latte() {
            this.waterPerCup = 350;
            this.milkPerCup = 75;
            this.coffeePerCup = 20;
            this.costPerCup = 7;
        }
    }

    static class Cappuccino extends CoffeeType {
        public Cappuccino() {
            this.waterPerCup = 200;
            this.milkPerCup = 100;
            this.coffeePerCup = 12;
            this.costPerCup = 6;
        }
    }

    public static void main(String[] args) {
        do {
            System.out.println("Write action (buy, fill, take, remaining, exit):");
            ;
        } while (coffeeMachineAction(scanner.nextLine()));
    }

    private static void printCoffeeMachineStatus() {
        System.out.println("The coffee machine has:\n" +
                water + " of water\n" +
                milk + " of milk\n" +
                coffee + " of coffee beans\n" +
                disposableCups + " of disposable cups\n" +
                money + " of money\n");
    }

    private static boolean coffeeMachineAction(String action) {
        System.out.println();
        switch (action) {
            case "buy":
                buyCupOfCoffee();
                break;
            case "fill":
                fillCoffeeMachine();
                break;
            case "take":
                takeMoney();
                break;
            case "remaining":
                printCoffeeMachineStatus();
                break;
            case "exit":
                return false;
            default:
                System.out.println("You can't do it!");
        }
        System.out.println();
        return true;
    }

    private static void initCoffeeMachine() {
        System.out.println("Write how many " + "ml" + " of water the coffee machine has:");
        water = Integer.parseInt(scanner.nextLine());

        System.out.println("Write how many " + "ml" + " of milk the coffee machine has:");
        milk = Integer.parseInt(scanner.nextLine());

        System.out.println("Write how many " + "g" + " of coffee the coffee machine has:");
        coffee = Integer.parseInt(scanner.nextLine());

        System.out.println("Write how many " + "disposable cups" + " the coffee machine has:");
        disposableCups = Integer.parseInt(scanner.nextLine());

        System.out.println("Write how many " + "dollars" + " the coffee machine has:");
        money = Integer.parseInt(scanner.nextLine());
    }

    private static boolean enoughResourcesCheck(CoffeeType coffeeCup) {
        boolean enoughResources = true;

        if (water < coffeeCup.waterPerCup) {
            enoughResources = false;
            System.out.println("Sorry, not enough " + "water" + "!");
        } if (milk < coffeeCup.milkPerCup) {
            enoughResources = false;
            System.out.println("Sorry, not enough " + "milk" + "!");
        } if (coffee < coffeeCup.coffeePerCup) {
            enoughResources = false;
            System.out.println("Sorry, not enough " + "milk" + "!");
        } if (disposableCups < 1) {
            enoughResources = false;
            System.out.println("Sorry, not enough " + "disposable cups" + "!");
        } if (enoughResources) {
            System.out.println("I have enough resources, making you a coffee!");
        }
        return enoughResources;
    }

    private static void buyCupOfCoffee() {
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino:");
        String coffeeType = scanner.nextLine();
        CoffeeType coffee = null;
        switch (coffeeType) {
            case "1":
                coffee = new Espresso();
                break;
            case "2":
                coffee = new Latte();
                break;
            case "3":
                coffee = new Cappuccino();
                break;
            default:
                break;
        }
        if (coffee != null && enoughResourcesCheck(coffee)) {
            coffee.buyCupOfTheCoffee();
        }
    }

    private static void fillCoffeeMachine() {
        System.out.println("Write how many " + "ml" + " of " + "water" + " do you want to add:");
        water += Integer.parseInt(scanner.nextLine());

        System.out.println("Write how many " + "ml" + " of " + "milk" + " do you want to add:");
        milk += Integer.parseInt(scanner.nextLine());

        System.out.println("Write how many " + "grams" + " of " + "coffee beans" + " do you want to add:");
        coffee += Integer.parseInt(scanner.nextLine());

        System.out.println("Write how many " + "disposable cups" + " of " + "coffee" + " do you want to add:");
        disposableCups += Integer.parseInt(scanner.nextLine());

    }

    private static void takeMoney() {
        System.out.println("I gave you $" + money);
        money -= money;
    }

    private static void takeMoney(int neededMoney) {
        if (neededMoney <= money) {
            System.out.println("I gave you $" + neededMoney);
            money -= neededMoney;
        } else {
            System.out.println("You need to earn $" + (neededMoney - money));
        }
    }

    private static void possibleCups(CoffeeType coffeeCup) {
        System.out.println("Write how many cups of coffee you will need:");
        int demandedCups = Integer.parseInt(scanner.nextLine());
        int maxCups = maxPossibleCups(coffeeCup);

        if (demandedCups == maxCups) {
            System.out.println("Yes, I can make that amount of coffee");
        } else if (demandedCups < maxCups) {
            System.out.println("Yes, I can make that amount of coffee (and even " + (maxCups - demandedCups) + " more than that)");
        } else {
            System.out.println("No, I can make only " + maxCups + " cup(s) of coffee");
        }
    }

    private static int maxPossibleCups(CoffeeType coffeeCup) {
        int waterCups = water / coffeeCup.waterPerCup;
        int milkCups = milk / coffeeCup.milkPerCup;
        int coffeeCups = coffee / coffeeCup.coffeePerCup;

        return Integer.min(waterCups, Integer.min(milkCups, coffeeCups));
    }

    private static void calculateResources(CoffeeType coffeeType) {
        System.out.println("Write how many cups of coffee you will need:");
        int cupsCount = Integer.parseInt(scanner.nextLine());

        int waterMl = coffeeType.waterPerCup * cupsCount;
        int milkMl = coffeeType.milkPerCup * cupsCount;
        int coffeeG = coffeeType.coffeePerCup * cupsCount;

        System.out.println("For " + cupsCount + " cups of coffee you will need:\n" +
                waterMl + " ml of water\n" +
                milkMl + " ml of milk\n" +
                coffeeG + " g of coffee beans");
    }

    private static void makeCoffeeProcess() {
        System.out.println("Starting to make a coffee\n" +
                "Grinding coffee beans\n" +
                "Boiling water\n" +
                "Mixing boiled water with crushed coffee beans\n" +
                "Pouring coffee into the cup\n" +
                "Pouring some milk into the cup\n" +
                "Coffee is ready!");
    }
}
