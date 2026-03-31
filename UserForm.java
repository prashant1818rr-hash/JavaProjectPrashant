import javax.swing.*;
import java.util.List;

public class UserForm {
    public static void main(String[] args) {
        JFrame frame = new JFrame("User Form (CRUD with JDBC)");
        frame.setSize(500, 400);

        JLabel idLabel = new JLabel("ID:");
        JTextField idField = new JTextField(5);
        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField(20);
        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField(20);
        JLabel countryLabel = new JLabel("Country:");
        JTextField countryField = new JTextField(20);

        JButton insertBtn = new JButton("Insert");
        JButton viewBtn = new JButton("View Users");
        JButton updateBtn = new JButton("Update");
        JButton deleteBtn = new JButton("Delete");

        UserDAO dao = new UserDAO();

insertBtn.addActionListener(_ -> {
    try {
        int id = Integer.parseInt(idField.getText());
        dao.insertUser(id, nameField.getText(), emailField.getText(), countryField.getText());
        JOptionPane.showMessageDialog(frame, "✅ User Inserted with ID: " + id);
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(frame, "❌ Enter valid ID and other details!");
    }
});


        // VIEW
        viewBtn.addActionListener(_ -> {
            List<String> users = dao.getAllUsers();
            if (users.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "❌ No users found!");
            } else {
                StringBuilder sb = new StringBuilder("Users:\n\n");
                for (String u : users) {
                    sb.append(u).append("\n");
                }
                JOptionPane.showMessageDialog(frame, sb.toString());
            }
        });

        // UPDATE
        updateBtn.addActionListener(_ -> {
            try {
                int id = Integer.parseInt(idField.getText());
                dao.updateUser(id, nameField.getText(), emailField.getText(), countryField.getText());
                JOptionPane.showMessageDialog(frame, "✅ User Updated!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "❌ Enter valid ID to update!");
            }
        });

        // DELETE
        deleteBtn.addActionListener(_ -> {
            try {
                int id = Integer.parseInt(idField.getText());
                dao.deleteUser(id);
                JOptionPane.showMessageDialog(frame, "✅ User Deleted!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "❌ Enter valid ID to delete!");
            }
        });

        JPanel panel = new JPanel();
        panel.add(idLabel); panel.add(idField);
        panel.add(nameLabel); panel.add(nameField);
        panel.add(emailLabel); panel.add(emailField);
        panel.add(countryLabel); panel.add(countryField);
        panel.add(insertBtn);
        panel.add(viewBtn);
        panel.add(updateBtn);
        panel.add(deleteBtn);

        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
