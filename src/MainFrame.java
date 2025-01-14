import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private JPanel taskListPanel;

    public void initialize(User user) {
        // User Info Panel
        JPanel infoPanel = new JPanel();
        infoPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        infoPanel.setLayout(new GridLayout(0, 2, 5, 5));
        infoPanel.add(new JLabel("Name"));
        infoPanel.add(new JLabel(user.name));
        infoPanel.add(new JLabel("Email"));
        infoPanel.add(new JLabel(user.email));
        infoPanel.add(new JLabel("Phone"));
        infoPanel.add(new JLabel(user.phone));
        infoPanel.add(new JLabel("Address"));
        infoPanel.add(new JLabel(user.address));

        Component[] labels = infoPanel.getComponents();
        for (Component label : labels) {
            label.setFont(new Font("Segoe print", Font.BOLD, 18));
        }

        // Task List Panel
        JLabel toDoLabel = new JLabel("To-Do List", SwingConstants.CENTER);
        toDoLabel.setFont(new Font("Segoe print", Font.BOLD, 18));

        taskListPanel = new JPanel();
        taskListPanel.setLayout(new BoxLayout(taskListPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(taskListPanel);

        // Add Task Button
        JButton btnAddTask = new JButton("Add Task");
        btnAddTask.setFont(new Font("Segoe print", Font.BOLD, 16));
        btnAddTask.addActionListener(e -> openAddTaskModal());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(btnAddTask);

        // Task Panel
        JPanel taskPanel = new JPanel();
        taskPanel.setLayout(new BorderLayout());
        taskPanel.add(toDoLabel, BorderLayout.NORTH);
        taskPanel.add(scrollPane, BorderLayout.CENTER);
        taskPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Main Frame Layout
        setLayout(new BorderLayout());
        add(infoPanel, BorderLayout.NORTH);
        add(taskPanel, BorderLayout.CENTER);

        setTitle("Dashboard");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(1100, 650);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void openAddTaskModal() {
        String task = JOptionPane.showInputDialog(
                this,
                "Enter your task:",
                "Add Task",
                JOptionPane.PLAIN_MESSAGE
        );

        if (task != null && !task.trim().isEmpty()) {
            addTaskToPanel(task);
        }
    }

    private void addTaskToPanel(String taskText) {
        JPanel taskPanel = new JPanel();
        taskPanel.setLayout(new GridBagLayout());
        taskPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY, 1),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        gbc.gridx = 0;

        JLabel taskLabel = new JLabel(taskText);
        taskLabel.setFont(new Font("Segoe print", Font.PLAIN, 16));
        taskLabel.setHorizontalAlignment(SwingConstants.LEFT);
        taskPanel.add(taskLabel, gbc);

        gbc.weightx = 0;
        gbc.gridx = 1;

        JButton btnEdit = new JButton("Edit");
        btnEdit.setFont(new Font("Segoe print", Font.BOLD, 14));
        btnEdit.addActionListener(e -> {
            String newTask = JOptionPane.showInputDialog(
                    this,
                    "Edit Task:",
                    taskText
            );

            if (newTask != null && !newTask.trim().isEmpty()) {
                taskLabel.setText(newTask);
            }
        });
        taskPanel.add(btnEdit, gbc);

        gbc.gridx = 2;
        JButton btnDelete = new JButton("Delete");
        btnDelete.setFont(new Font("Segoe print", Font.BOLD, 14));
        btnDelete.addActionListener(e -> {
            taskListPanel.remove(taskPanel);
            taskListPanel.revalidate();
            taskListPanel.repaint();
        });
        taskPanel.add(btnDelete, gbc);

        // Add task panel to the main task list
        taskListPanel.add(taskPanel);
        taskListPanel.add(Box.createVerticalStrut(10));
        taskListPanel.revalidate();
        taskListPanel.repaint();
    }
}
