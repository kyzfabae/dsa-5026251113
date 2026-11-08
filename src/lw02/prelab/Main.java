package lw02.prelab;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

// Main Class getResourceAsStream()
// Array store tiap data
// Raisa di 1 dan 3 harus jadi as a 1 customer
// initial balance 0

public class Main {
    public static void main(String[] args) throws Exception {

        // Read transaction
        LinkedList<String[]> transactions = new LinkedList<>();
        LinkedList<String[]> customers = new LinkedList<>();
        Queue<String[]> queue = new LinkedList<>();
        Stack<String[]> failedTransactions = new Stack<>();
        Scanner scanner = new Scanner(Main.class.geResourceAsStream("transactions.txt"));



        while (scanner.hasNext()) {
            String name = scanner.next();
            String type = scanner.next();
            String amount = scanner.next();
            transactions.add(new String[]{name, type, amount});
        }
        scanner.close();

        // 2. Build customer list (first-appearance order, balance starts at 0)
        
        for (String[] tx : transactions) {
            String name = tx[0];
            boolean found = false;
            for (String[] customer : customers) {
                if (customer[0].equals(name)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                customers.add(new String[]{name, "0"});
            }
        }

        // 3. Move transactions into a Queue (FIFO)
        
        for (String[] tx : transactions) {
            queue.offer(tx);
        }

        // 4. Stack for failed withdrawals
        

        // 5. Process the queue in FIFO order
        while (!queue.isEmpty()) {
            String[] tx = queue.poll();
            String name = tx[0];
            String type = tx[1];
            int amount = Integer.parseInt(tx[2]);

            String[] customer = null;
            for (String[] c : customers) {
                if (c[0].equals(name)) {
                    customer = c;
                    break;
                }
            }

            int balance = Integer.parseInt(customer[1]);

            if (type.equals("DEPOSIT")) {
                balance += amount;
                customer[1] = String.valueOf(balance);
            } else if (type.equals("WITHDRAW")) {
                if (amount > balance) {
                    failedTransactions.push(tx);
                } else {
                    balance -= amount;
                    customer[1] = String.valueOf(balance);
                }
            }
        }

        // 6. Display final balances
        System.out.println("=== Final Balances ===");
        for (String[] customer : customers) {
            System.out.println(customer[0] + " : " + customer[1]);
        }

        System.out.println();
        System.out.println("=== Failed Transactions ===");
        while (!failedTransactions.isEmpty()) {
            String[] tx = failedTransactions.pop();
            System.out.println(tx[0] + " " + tx[1] + " " + tx[2]);
        }
    }
}