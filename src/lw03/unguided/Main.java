package lw03.unguided;

import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws Exception {

        Set<String> regist = new LinkedHashSet<>();
        Scanner sc1 = new Scanner(Main.class.getResourceAsStream("registrations.txt"));
        while (sc1.hasNextLine()) {
            String id = sc1.nextLine().trim();
            if (id.isEmpty()) continue;
            regist.add(id); // Set otomatis tolak duplikat
        }
        sc1.close();

        Set<String> check = new LinkedHashSet<>();
        int reject = 0;

        System.out.println("===== Event Check-In Results =====");

        Scanner sc2 = new Scanner(Main.class.getResourceAsStream("checkins.txt"));
        while (sc2.hasNextLine()) {
            String id = sc2.nextLine().trim();
            if (id.isEmpty()) continue;

            if (!regist.contains(id)) {
                System.out.println(id + ": Rejected (not registered)");
                reject++;
            } else if (check.contains(id)) {
                System.out.println(id + ": Rejected (already checked in)");
                reject++;
            } else {
                check.add(id);
                System.out.println(id + ": Checked in");
            }
        }
        sc2.close();

        // 3. Summary
        int tR = regist.size();
        int tC = check.size();
        int a = tR - tC;

        System.out.println();
        System.out.println("===== Final Event Summary =====");
        System.out.println("Registered students: " + tR);
        System.out.println("Successful check-ins: " + tC);
        System.out.println("Absent students: " + a);
        System.out.println("Rejected attempts: " + reject);
    }
}