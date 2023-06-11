import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ChatApp extends JFrame {
    private JButton createGroupChatButton;
    private JButton blockUserButton;
    private JButton removeFriendButton;
    private List<String> groupChatList;
    private List<String> blockedUsersList;
    private List<String> groupMembersList;

    public ChatApp() {
        setTitle("Chat");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initComponents();
        groupChatList = new ArrayList<>();
        blockedUsersList = new ArrayList<>();
        groupMembersList = new ArrayList<>();
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
        JLabel headerLabel = new JLabel("Chat");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 18));
        headerPanel.add(headerLabel);
        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // Create button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 3, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        createGroupChatButton = new JButton("Create Group Chat");
        blockUserButton = new JButton("Block User");
        removeFriendButton = new JButton("Remove Friend");
        buttonPanel.add(createGroupChatButton);
        buttonPanel.add(blockUserButton);
        buttonPanel.add(removeFriendButton);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        // Add action listeners
        createGroupChatButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                createGroupChat();
            }
        });

        blockUserButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                blockUser();
            }
        });

        removeFriendButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeFriendFromGroup();
            }
        });
    }

    private void createGroupChat() {
        // Code to create a new group chat
        String groupName = JOptionPane.showInputDialog(this, "Enter a name for the group chat:");
        if (groupName != null && !groupName.isEmpty()) {
            groupChatList.add(groupName);
            groupMembersList.clear();
            groupMembersList.add("User"); // Adding the user as the first member
            addGroupMembers();
            JOptionPane.showMessageDialog(this, "Group chat '" + groupName + "' created successfully");
        }
    }

    private void addGroupMembers() {
        // Code to add members to the group chat
        if (groupMembersList.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No friends available to add to the group chat");
        } else {
            String[] friends = groupMembersList.toArray(new String[groupMembersList.size()]);
            String selectedFriends = (String) JOptionPane.showInputDialog(this, "Select friends to add:",
                    "Add Members", JOptionPane.QUESTION_MESSAGE, null, friends, friends[0]);
            if (selectedFriends != null && !selectedFriends.isEmpty()) {
                groupMembersList.add(selectedFriends);
                JOptionPane.showMessageDialog(this, "Friend '" + selectedFriends + "' added to the group chat");
            }
        }
    }

    private void blockUser() {
        // Code to block a user
        if (blockedUsersList.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No unsolicited messages are currently received");
} else {
String[] users = blockedUsersList.toArray(new String[blockedUsersList.size()]);
String selectedUser = (String) JOptionPane.showInputDialog(this, "Select user to block:",
"Block User", JOptionPane.QUESTION_MESSAGE, null, users, users[0]);
if (selectedUser != null && !selectedUser.isEmpty()) {
int choice = JOptionPane.showConfirmDialog(this, "Block user '" + selectedUser + "'?",
"Confirmation", JOptionPane.YES_NO_OPTION);
if (choice == JOptionPane.YES_OPTION) {
blockedUsersList.add(selectedUser);
JOptionPane.showMessageDialog(this, "User '" + selectedUser + "' blocked successfully");
}
}
}
}
private void removeFriendFromGroup() {
    // Code to remove a friend from the group chat
    if (groupMembersList.isEmpty()) {
        JOptionPane.showMessageDialog(this, "No friends in the group chat");
    } else {
        String[] members = groupMembersList.toArray(new String[groupMembersList.size()]);
        String selectedMember = (String) JOptionPane.showInputDialog(this, "Select member to remove:",
                "Remove Friend", JOptionPane.QUESTION_MESSAGE, null, members, members[0]);
        if (selectedMember != null && !selectedMember.isEmpty()) {
            int choice = JOptionPane.showConfirmDialog(this, "Remove member '" + selectedMember + "' from the group chat?",
                    "Confirmation", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                groupMembersList.remove(selectedMember);
                JOptionPane.showMessageDialog(this, "Member '" + selectedMember + "' removed from the group chat");
            }
        }
    }
}

public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
        public void run() {
            new ChatApp();
        }
    });
}
}