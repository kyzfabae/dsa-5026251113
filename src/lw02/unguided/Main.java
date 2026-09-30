package lw02.unguided;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws Exception {

        // Inisiasi
        LinkedList<String[]> orders = new LinkedList<>();
        LinkedList<String[]> foodStock = new LinkedList<>();
        LinkedList<String[]> drinkStock = new LinkedList<>();
        LinkedList<String[]> successfulOrders = new LinkedList<>();
        Queue<String[]> queue = new LinkedList<>();
        Stack<String[]> failedOrders = new Stack<>();
        Scanner scanner = new Scanner(Main.class.getResourceAsStream("orders.txt"));
        while (scanner.hasNext()) {
            String name = scanner.next();
            String food = scanner.next();
            String drink = scanner.next();
            String table = scanner.next();
            orders.add(new String[]{name, food, drink, table});
        }
        scanner.close();

        // Isi Makan
        foodStock.add(new String[]{"Bakso", "2"});
        foodStock.add(new String[]{"Sate", "1"});
        foodStock.add(new String[]{"Soto", "2"});

        // Isi Minum
        drinkStock.add(new String[]{"EsTeh", "4"});
        drinkStock.add(new String[]{"EsJeruk", "2"});


        
        for (String[] order : orders) {
            queue.offer(order);
        }        

        while (!queue.isEmpty()) {
            String[] order = queue.poll();
            String food = order[1];
            String drink = order[2];

            String[] foodItem = null;
            String[] drinkItem = null;

            boolean foodAvailable = true;
            boolean drinkAvailable = true;

            if (!food.equals("-")) {
                for (String[] item : foodStock) {
                    if (item[0].equals(food)) {
                        foodItem = item;
                        break;
                    }
                }
                if (Integer.parseInt(foodItem[1]) <= 0) {
                    foodAvailable = false;
                }
            }

            if (!drink.equals("-")) {
                for (String[] item : drinkStock) {
                    if (item[0].equals(drink)) {
                        drinkItem = item;
                        break;
                    }
                }
                if (Integer.parseInt(drinkItem[1]) <= 0) {
                    drinkAvailable = false;
                }
            }

            if (foodAvailable && drinkAvailable) {
                if (foodItem != null) {
                    int stock = Integer.parseInt(foodItem[1]);
                    foodItem[1] = String.valueOf(stock - 1);
                }
                if (drinkItem != null) {
                    int stock = Integer.parseInt(drinkItem[1]);
                    drinkItem[1] = String.valueOf(stock - 1);
                }
                successfulOrders.add(order);
            } else {
                failedOrders.push(order);
            }
        }

        System.out.println("=== Successfully Processed Orders ===");
        for (String[] order : successfulOrders) {
            System.out.println(order[0] + " " + order[1] + " " + order[2] + " " + order[3]);
        }

        System.out.println();
        System.out.println("=== Remaining Food Stock ===");
        for (String[] item : foodStock) {
            System.out.println(item[0] + " : " + item[1]);
        }

        System.out.println();
        System.out.println("=== Remaining Drink Stock ===");
        for (String[] item : drinkStock) {
            System.out.println(item[0] + " : " + item[1]);
        }

        System.out.println();
        System.out.println("=== Failed Orders ===");
        while (!failedOrders.isEmpty()) {
            String[] order = failedOrders.pop();
            System.out.println(order[0] + " " + order[1] + " " + order[2] + " " + order[3]);
        }
    }
}