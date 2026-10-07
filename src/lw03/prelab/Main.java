package lw03.prelab;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws Exception {
        problem1();
        System.out.println();
        problem2();
        System.out.println();
        problem3();
    }

    // ===== Problem 1: Playlist (List) =====
    static void problem1() throws Exception {
        System.out.println("===== Problem 1 =====");

        List<String> playlist = new ArrayList<>();
        Scanner scanner = new Scanner(Main.class.getResourceAsStream("playlist.txt"));

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) continue;

            String[] parts = line.split(" ", 2);
            String command = parts[0];

            if (command.equals("ADD")) {
                String song = parts[1];
                playlist.add(song);
            } else if (command.equals("INSERT")) {
                String[] rest = parts[1].split(" ", 2);
                int index = Integer.parseInt(rest[0]);
                String song = rest[1];
                playlist.add(index, song);
            } else if (command.equals("REMOVE")) {
                String song = parts[1];
                playlist.remove(song);
            }
        }
        scanner.close();

        System.out.println("Total songs: " + playlist.size());
        for (int i = 0; i < playlist.size(); i++) {
            System.out.println((i + 1) + ": " + playlist.get(i));
        }
    }

    // ===== Problem 2: Workshop Participants (Set) =====
    static void problem2() throws Exception {
        System.out.println("===== Problem 2 =====");

        Set<String> participants = new LinkedHashSet<>();
        int duplicateCount = 0;

        Scanner scanner = new Scanner(Main.class.getResourceAsStream("participants.txt"));
        while (scanner.hasNextLine()) {
            String name = scanner.nextLine().trim();
            if (name.isEmpty()) continue;

            if (participants.contains(name)) {
                duplicateCount++;
            } else {
                participants.add(name);
            }
        }
        scanner.close();

        System.out.println("Unique participants: " + participants.size());
        int i = 1;
        for (String name : participants) {
            System.out.println(i + ". " + name);
            i++;
        }
        System.out.println("Duplicate registrations: " + duplicateCount);
    }

    // ===== Problem 3: Inventory (Map) =====
    static void problem3() throws Exception {
        System.out.println("===== Problem 3 =====");

        Map<String, Integer> inventory = new LinkedHashMap<>();
        int failedSales = 0;

        Scanner scanner = new Scanner(Main.class.getResourceAsStream("inventory.txt"));
        while (scanner.hasNext()) {
            String type = scanner.next();
            String product = scanner.next();
            int quantity = scanner.nextInt();

            if (type.equals("ADD")) {
                if (inventory.containsKey(product)) {
                    inventory.put(product, inventory.get(product) + quantity);
                } else {
                    inventory.put(product, quantity);
                }
            } else if (type.equals("SELL")) {
                if (inventory.containsKey(product) && inventory.get(product) >= quantity) {
                    inventory.put(product, inventory.get(product) - quantity);
                } else {
                    failedSales++;
                }
            }
        }
        scanner.close();

        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        System.out.println("Failed sales: " + failedSales);
    }
}