import javax.swing.JOptionPane;

class Tournament {
    private String name;
    private String date;
    private String location;
    private String tournamentType;
    private String rules;
    private String entryFee;
    private String prizeMoney;
    private String athletePoints;

    public Tournament(String name, String date, String location, String tournamentType) {
        this.name = name;
        this.date = date;
        this.location = location;
        this.tournamentType = tournamentType;
    }

    public void setRules(String rules) {
        this.rules = rules;
    }

    public void setEntryFee(String entryFee) {
        this.entryFee = entryFee;
    }

    public void setPrizeMoney(String prizeMoney) {
        this.prizeMoney = prizeMoney;
    }

    public void setAthletePoints(String athletePoints) {
        this.athletePoints = athletePoints;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getLocation() {
        return location;
    }

    public String getTournamentType() {
        return tournamentType;
    }

    public String getRules() {
        return rules;
    }

    public String getEntryFee() {
        return entryFee;
    }

    public String getPrizeMoney() {
        return prizeMoney;
    }

    public String getAthletePoints() {
        return athletePoints;
    }
}

class TournamentManager {
    public void createTournament() {
        Tournament tournament = getTournamentDetails();
        if (tournament != null) {
            setTournamentRules(tournament);
            setEntryFeeAndPrize(tournament);
            displayCompletedTournament(tournament);
            int choice = JOptionPane.showOptionDialog(null, "Press (Create) to confirm the creation of the tournament or (Cancel) to cancel:",
                    "Confirmation", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                    new Object[]{"Create", "Cancel"}, "Create");
            if (choice == 0) {
                confirmTournamentCreation(tournament);
                displaySuccessMessage();
            } else {
                displayErrorMessage("Tournament creation canceled.");
            }
        } else {
            displayErrorMessage("Error: Required fields are missing!");
        }
    }

    private Tournament getTournamentDetails() {
        String name = JOptionPane.showInputDialog("Enter the name of the tournament:");
        String date = JOptionPane.showInputDialog("Enter the date of the tournament:");
        String location = JOptionPane.showInputDialog("Enter the location of the tournament:");
        String tournamentType = JOptionPane.showInputDialog("Enter the type of tournament:");

        if (name != null && !name.isEmpty() && date != null && !date.isEmpty() &&
                location != null && !location.isEmpty() && tournamentType != null && !tournamentType.isEmpty()) {
            return new Tournament(name, date, location, tournamentType);
        } else {
            displayErrorMessage("Error: Required fields are missing!");
            return null;
        }
    }

    private void setTournamentRules(Tournament tournament) {
        String rules = JOptionPane.showInputDialog("Enter the rules and regulations of the tournament:");
        tournament.setRules(rules);
    }

    private void setEntryFeeAndPrize(Tournament tournament) {
        while (true) {
            String entryFeeString = JOptionPane.showInputDialog("Enter the entry fee for participating teams:");
            try {
                double entryFee = Double.parseDouble(entryFeeString);
                tournament.setEntryFee(String.valueOf(entryFee));
                break;
            } catch (NumberFormatException e) {
                displayErrorMessage("Error: Invalid input format. Entry fee must be a numeric value.");
            }
        }

        while (true) {
            String prizeMoneyString = JOptionPane.showInputDialog("Enter the prize money for the winners:");
try {
double prizeMoney = Double.parseDouble(prizeMoneyString);
tournament.setPrizeMoney(String.valueOf(prizeMoney));
break;
} catch (NumberFormatException e) {
displayErrorMessage("Error: Invalid input format. Prize money must be a numeric value.");
}
}
 while (true) {
        String athletePointsString = JOptionPane.showInputDialog("Enter the athlete points earned:");
        try {
            double athletePoints = Double.parseDouble(athletePointsString);
            tournament.setAthletePoints(String.valueOf(athletePoints));
            break;
        } catch (NumberFormatException e) {
            displayErrorMessage("Error: Invalid input format. Athlete points must be a numeric value.");
        }
    }
}

private void displayCompletedTournament(Tournament tournament) {
    StringBuilder sb = new StringBuilder();
    sb.append("Completed Tournament Details:\n");
    sb.append("Name: ").append(tournament.getName()).append("\n");
    sb.append("Date: ").append(tournament.getDate()).append("\n");
    sb.append("Location: ").append(tournament.getLocation()).append("\n");
    sb.append("Type: ").append(tournament.getTournamentType()).append("\n");
    sb.append("Rules: ").append(tournament.getRules()).append("\n");
    sb.append("Entry Fee: ").append(tournament.getEntryFee()).append("\n");
    sb.append("Prize Money: ").append(tournament.getPrizeMoney()).append("\n");
    sb.append("Athlete Points: ").append(tournament.getAthletePoints()).append("\n");

    JOptionPane.showMessageDialog(null, sb.toString(), "Completed Tournament Details", JOptionPane.INFORMATION_MESSAGE);
}

private void confirmTournamentCreation(Tournament tournament) {
    // Process and store the tournament in the system
    // Code for tournament creation
}

private void displaySuccessMessage() {
    JOptionPane.showMessageDialog(null, "Tournament created successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
}

private void displayErrorMessage(String message) {
    JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
}
}

public class Main {
public static void main(String[] args) {
TournamentManager manager = new TournamentManager();
manager.createTournament();
}
}