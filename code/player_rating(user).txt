import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PlayerRatingApp {
    private List<Player> playerList;

    public PlayerRatingApp() {
        playerList = new ArrayList<>();
        // Populate the player list with sample players
        playerList.add(new Player(1, "John Doe"));
        playerList.add(new Player(2, "Jane Smith"));
        playerList.add(new Player(3, "David Johnson"));
        playerList.add(new Player(4, "Emily Davis"));
        playerList.add(new Player(5, "Michael Wilson"));
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Player Rating App");
            System.out.println("1. Rate Player");
            System.out.println("2. Provide Additional Feedback");
            System.out.println("3. Cancel Evaluation");
            System.out.println("4. View User's Score");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    displayPlayerList();
                    System.out.print("Enter the ID of the player you want to rate: ");
                    int playerId = scanner.nextInt();
                    ratePlayer(playerId);
                    break;
                case 2:
                    displayPlayerList();
                    System.out.print("Enter the ID of the player you want to provide feedback: ");
                    playerId = scanner.nextInt();
                    provideAdditionalFeedback(playerId);
                    break;
                case 3:
                    displayPlayerList();
                    System.out.print("Enter the ID of the player you want to cancel evaluation: ");
                    playerId = scanner.nextInt();
                    cancelEvaluation(playerId);
                    break;
                case 4:
                    viewUserScore();
                    break;
                case 5:
                    System.out.println("Thank you for using Player Rating App. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void displayPlayerList() {
        System.out.println("Player List:");
        for (Player player : playerList) {
            System.out.println(player.getId() + ". " + player.getName());
        }
    }

    private void ratePlayer(int playerId) {
        Player selectedPlayer = getPlayerById(playerId);

        if (selectedPlayer == null) {
            System.out.println("Invalid player ID.");
            return;
        }

        System.out.println("Evaluation Criteria:");
        System.out.println("1. Sportsmanship");
        System.out.println("2. Skill");
        System.out.println("3. Consistency");
        System.out.println("4. Communication");
        System.out.println("5. Teamwork");

        Scanner scanner = new Scanner(System.in);
        double totalRating = 0.0;
        int criteriaCount = 0;

        for (int i = 1; i <= 5; i++) {
            System.out.print("Rate " + selectedPlayer.getName() + " on criterion " + i + " (Poor, Average, Good, Very Good, Excellent): ");
            String rating = scanner.nextLine();

            switch (rating.toLowerCase()) {
                case "poor":
                    totalRating += 1.0;
                    criteriaCount++;
                    break;
                case "average":
                    totalRating += 2.0;
                    criteriaCount++;
                    break;
                case "good":
                    totalRating += 3.0;
                    criteriaCount++;
                    break;
                case "very good":
                    totalRating += 4.0;
                    criteriaCount++;
                    break;
                case "excellent":
                    totalRating += 5.0;
 criteriaCount++;
                break;
            default:
                System.out.println("Invalid rating. Please try again.");
                i--;
        }
    }

    double averageRating = totalRating / criteriaCount;
    selectedPlayer.addRating(averageRating);

    System.out.println("Thank you for rating " + selectedPlayer.getName() + "!");
    System.out.println("Average Rating: " + averageRating);
}

private void provideAdditionalFeedback(int playerId) {
    Player selectedPlayer = getPlayerById(playerId);

    if (selectedPlayer == null) {
        System.out.println("Invalid player ID.");
        return;
    }

    Scanner scanner = new Scanner(System.in);

    System.out.println("Provide additional feedback for " + selectedPlayer.getName() + ":");
    String feedback = scanner.nextLine();

    selectedPlayer.addFeedback(feedback);

    System.out.println("Additional feedback provided successfully!");
}

private void cancelEvaluation(int playerId) {
    Player selectedPlayer = getPlayerById(playerId);

    if (selectedPlayer == null) {
        System.out.println("Invalid player ID.");
        return;
    }

    selectedPlayer.clearRatings();
    selectedPlayer.clearFeedback();

    System.out.println("Evaluation canceled successfully.");
}

private void viewUserScore() {
    Scanner scanner = new Scanner(System.in);

    System.out.print("Enter your player ID: ");
    int playerId = scanner.nextInt();

    Player player = getPlayerById(playerId);

    if (player == null) {
        System.out.println("Invalid player ID.");
        return;
    }

    System.out.println("Player: " + player.getName());
    System.out.println("Total Score: " + player.getTotalRating());
    System.out.println("Number of Ratings: " + player.getRatingsCount());
    System.out.println("Feedbacks:");
    for (String feedback : player.getFeedbacks()) {
        System.out.println("- " + feedback);
    }
}

private Player getPlayerById(int playerId) {
    for (Player player : playerList) {
        if (player.getId() == playerId) {
            return player;
        }
    }
    return null;
}

public static void main(String[] args) {
    PlayerRatingApp app = new PlayerRatingApp();
    app.run();
}
}