import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TournamentApp {
    private static List<Tournament> tournamentList = new ArrayList<>();
    private static int tournamentIdCounter = 1;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. View upcoming tournaments");
            System.out.println("2. Register for a tournament");
            System.out.println("3. Cancel tournament registration");
            System.out.println("4. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    viewUpcomingTournaments();
                    break;
                case 2:
                    registerForTournament(scanner);
                    break;
                case 3:
                    cancelTournamentRegistration(scanner);
                    break;
                case 4:
                    System.out.println("Thank you for using the Tournament App. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void viewUpcomingTournaments() {
        if (tournamentList.isEmpty()) {
            System.out.println("No upcoming tournaments.");
        } else {
            System.out.println("Upcoming Tournaments:");
            for (Tournament tournament : tournamentList) {
                System.out.println(tournament);
            }
        }
    }

    private static void registerForTournament(Scanner scanner) {
        if (tournamentList.isEmpty()) {
            System.out.println("No upcoming tournaments to register for.");
            return;
        }

        System.out.print("Enter the ID of the tournament you want to register for: ");
        int tournamentId = scanner.nextInt();
        scanner.nextLine();

        Tournament selectedTournament = null;
        for (Tournament tournament : tournamentList) {
            if (tournament.getId() == tournamentId) {
                selectedTournament = tournament;
                break;
            }
        }

        if (selectedTournament == null) {
            System.out.println("Invalid tournament ID.");
            return;
        }

        System.out.println("Tournament Details:");
        System.out.println(selectedTournament);

        System.out.print("Enter team name: ");
        String teamName = scanner.nextLine();

        System.out.print("Enter the number of team members: ");
        int numTeamMembers = scanner.nextInt();
        scanner.nextLine();

        List<String> teamMembers = new ArrayList<>();
        for (int i = 0; i < numTeamMembers; i++) {
            System.out.print("Enter name of team member " + (i + 1) + ": ");
            String teamMember = scanner.nextLine();
            teamMembers.add(teamMember);
        }

        // Calculate tournament entry fee and display payment details
        double entryFee = selectedTournament.getEntryFee() * numTeamMembers;
        System.out.println("Total entry fee: $" + entryFee);

        System.out.print("Enter payment details: ");
        String paymentDetails = scanner.nextLine();

        System.out.println("Registration successful. Participation ID: " + tournamentIdCounter);
        tournamentIdCounter++;

        // Add the registration to the tournament
        selectedTournament.addRegistration(teamName, teamMembers);
    }

    private static void cancelTournamentRegistration(Scanner scanner) {
        if (tournamentList.isEmpty()) {
            System.out.println("No upcoming tournaments to cancel registration.");
            return;
        }

        System.out.print("Enter the ID of the tournament for which you want to cancel registration: ");
        int tournamentId = scanner.nextInt();
        scanner.nextLine();

        Tournament selectedTournament = null;
        for (Tournament tournament : tournamentList) {
if (tournament.getId() == tournamentId) {
selectedTournament = tournament;
break;
}
}
if (selectedTournament == null) {
        System.out.println("Invalid tournament ID.");
        return;
    }

    // Remove the registration from the tournament
    selectedTournament.removeRegistration(tournamentId);
    System.out.println("Tournament registration cancelled successfully.");
}

private static class Tournament {
    private int id;
    private String name;
    private double entryFee;
    private String dates;
    private int ageLimit;
    private String sport;
    private String court;
    private String rules;
    private List<Registration> registrations;

    public Tournament(int id, String name, double entryFee, String dates, int ageLimit, String sport, String court, String rules) {
        this.id = id;
        this.name = name;
        this.entryFee = entryFee;
        this.dates = dates;
        this.ageLimit = ageLimit;
        this.sport = sport;
        this.court = court;
        this.rules = rules;
        this.registrations = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getEntryFee() {
        return entryFee;
    }

    public String getDates() {
        return dates;
    }

    public int getAgeLimit() {
        return ageLimit;
    }

    public String getSport() {
        return sport;
    }

    public String getCourt() {
        return court;
    }

    public String getRules() {
        return rules;
    }

    public void addRegistration(String teamName, List<String> teamMembers) {
        Registration registration = new Registration(teamName, teamMembers);
        registrations.add(registration);
    }

    public void removeRegistration(int registrationId) {
        for (Registration registration : registrations) {
            if (registration.getId() == registrationId) {
                registrations.remove(registration);
                break;
            }
        }
    }

    @Override
    public String toString() {
        return "Tournament ID: " + id + "\n" +
                "Name: " + name + "\n" +
                "Entry Fee: $" + entryFee + "\n" +
                "Dates: " + dates + "\n" +
                "Age Limit: " + ageLimit + "\n" +
                "Sport: " + sport + "\n" +
                "Court: " + court + "\n" +
                "Rules: " + rules;
    }
}

private static class Registration {
    private static int registrationIdCounter = 1;
    private int id;
    private String teamName;
    private List<String> teamMembers;

    public Registration(String teamName, List<String> teamMembers) {
        this.id = registrationIdCounter;
        registrationIdCounter++;
        this.teamName = teamName;
        this.teamMembers = teamMembers;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Registration ID: " + id + "\n" +
                "Team Name: " + teamName + "\n" +
                "Team Members: " + teamMembers;
    }
}
}
