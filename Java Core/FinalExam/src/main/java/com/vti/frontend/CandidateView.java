package com.vti.frontend;

import com.vti.backend.controller.CandidateController;
import com.vti.entity.ExperienceCandidate;
import com.vti.entity.FresherCandidate;
import com.vti.entity.Candidate;
import com.vti.enums.GraduationRank;
import com.vti.utils.ValidationUtil;

import java.util.Scanner;

public class CandidateView {
    private static final CandidateController controller = new CandidateController();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("--- Candidate Registration ---");
        System.out.println("1. Register ExperienceCandidate");
        System.out.println("2. Register FresherCandidate");
        System.out.print("Choose (1-2): ");
        String choice = sc.nextLine();

        boolean registered = false;
        if ("1".equals(choice)) {
            ExperienceCandidate e = new ExperienceCandidate();
            System.out.print("First name: "); e.setFirstName(sc.nextLine());
            System.out.print("Last name: "); e.setLastName(sc.nextLine());
            System.out.print("Phone: "); e.setPhone(sc.nextLine());
            System.out.print("Email: "); e.setEmail(sc.nextLine());
            System.out.print("Password: "); e.setPassword(sc.nextLine());
            System.out.print("ExpInYear (0-10): ");
            try { e.setExpInYear(Integer.parseInt(sc.nextLine())); } catch (Exception ex) { e.setExpInYear(-1); }
            System.out.print("ProSkill: "); e.setProSkill(sc.nextLine());

            registered = controller.register(e);
        } else if ("2".equals(choice)) {
            FresherCandidate f = new FresherCandidate();
            System.out.print("First name: "); f.setFirstName(sc.nextLine());
            System.out.print("Last name: "); f.setLastName(sc.nextLine());
            System.out.print("Phone: "); f.setPhone(sc.nextLine());
            System.out.print("Email: "); f.setEmail(sc.nextLine());
            System.out.print("Password: "); f.setPassword(sc.nextLine());
            System.out.print("GraduationRank (Excellence, Good, Fair, Poor): ");
            String rank = sc.nextLine();
            f.setGraduationRank(ValidationUtil.parseGraduationRank(rank));

            registered = controller.register(f);
        } else {
            System.out.println("Invalid choice.");
            System.exit(0);
        }

        if (registered) {
            System.out.println("Register succeeded. Proceed to login.");
            doLogin(sc);
        } else {
            System.out.println("Register failed due to invalid input or duplicate email.");
        }

        sc.close();
    }

    private static void doLogin(Scanner sc) {
        System.out.println("--- Login ---");
        System.out.print("Email: "); String email = sc.nextLine();
        System.out.print("Password: "); String password = sc.nextLine();

        Candidate c = controller.login(email, password);
        if (c != null) {
            System.out.println("Login successful. Welcome " + c.getFirstName() + " " + c.getLastName() + " (" + c.getRole() + ")");
        } else {
            System.out.println("Login failed. Check credentials and formatting.");
        }
    }
}
