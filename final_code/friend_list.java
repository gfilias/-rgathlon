import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class FriendListApp extends JFrame {
    private JButton addFriendsButton;
    private JButton friendRequestsButton;
    private JButton deleteRequestsButton;
    private List<String> friendsList;
    private List<String> friendRequestsList;
    private List<String> pendingRequestsList;

    public FriendListApp() {
        setTitle("Friend List");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initComponents();
        friendsList = new ArrayList<>();
        friendRequestsList = new ArrayList<>();
        pendingRequestsList = new ArrayList<>();
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initComponents() {
        // Create main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        add(mainPanel);

        // Create header panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JLabel headerLabel = new JLabel("Friend List");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 18));
        headerPanel.add(headerLabel);
        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // Create button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 3, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        addFriendsButton = new JButton("Add Friends");
        friendRequestsButton = new JButton("Friend Requests");
        deleteRequestsButton = new JButton("Delete Requests");
        buttonPanel.add(addFriendsButton);
        buttonPanel.add(friendRequestsButton);
        buttonPanel.add(deleteRequestsButton);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        // Add action listeners
        addFriendsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openSearchForm();
            }
        });

        friendRequestsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewFriendRequests();
            }
        });

        deleteRequestsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewPendingRequests();
            }
        });
    }

    private void openSearchForm() {
        // Code to open the search form for finding friends
        String friendName = JOptionPane.showInputDialog(this, "Enter the name of the friend you want to add:");
        if (friendName != null && !friendName.isEmpty()) {
            if (friendsList.contains(friendName)) {
                JOptionPane.showMessageDialog(this, "You are already friends with " + friendName);
            } else if (friendRequestsList.contains(friendName)) {
                JOptionPane.showMessageDialog(this, "Friend request to " + friendName + " is already sent");
            } else {
                int choice = JOptionPane.showConfirmDialog(this, "Send friend request to " + friendName + "?",
                        "Confirmation", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    friendRequestsList.add(friendName);
                    JOptionPane.showMessageDialog(this, "Friendship request has been sent to " + friendName);
                }
            }
        }
    }

    private void viewFriendRequests() {
        // Code to view the friend requests
        if (friendRequestsList.isEmpty()) {
            JOptionPane.showMessageDialog(this, "You have no friend requests");
        } else {
            StringBuilder requests = new StringBuilder();
            for (String friendRequest : friendRequestsList) {
                requests.append(friendRequest).append("\n");
            }
            JOptionPane.showMessageDialog(this, "Friend Requests JOptionPane.showMessageDialog(this, "Friend Requests:\n" + requests.toString());
}
}
private void viewPendingRequests() {
    // Code to view the pending requests
    if (pendingRequestsList.isEmpty()) {
        JOptionPane.showMessageDialog(this, "You have no pending requests");
    } else {
        StringBuilder requests = new StringBuilder();
        for (String pendingRequest : pendingRequestsList) {
            requests.append(pendingRequest).append("\n");
        }
        JOptionPane.showMessageDialog(this, "Pending Requests:\n" + requests.toString());
    }
}

public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
        public void run() {
            new FriendListApp();
        }
    });
}
}