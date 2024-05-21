import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

class Client {
    String name;
    String subscriptionType;

    public Client(String name, String subscriptionType) {
        this.name = name;
        this.subscriptionType = subscriptionType;
    }

    @Override
    public String toString() {
        return name + " - " + subscriptionType;
    }
}

class Trainer {
    String name;

    public Trainer(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}

public class SportsComplexGUI extends JFrame {
    private ArrayList<Client> clients = new ArrayList<>();
    private ArrayList<Trainer> trainers = new ArrayList<>();
    private DefaultListModel<Client> clientListModel = new DefaultListModel<>();
    private DefaultListModel<Trainer> trainerListModel = new DefaultListModel<>();
    private JList<Client> clientList;
    private JList<Trainer> trainerList;

    public SportsComplexGUI() {
        setTitle("Спортивный комплекс");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(new GridLayout(1, 2));
        
        // Client Panel
        JPanel clientPanel = new JPanel(new BorderLayout());
        clientPanel.setBorder(BorderFactory.createTitledBorder("Клиенты"));
        
        clientList = new JList<>(clientListModel);
        clientPanel.add(new JScrollPane(clientList), BorderLayout.CENTER);

        JPanel clientControlPanel = new JPanel(new GridLayout(3, 1));
        JButton addClientButton = new JButton("Добавить клиента");
        addClientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addClient();
            }
        });
        clientControlPanel.add(addClientButton);

        JButton removeClientButton = new JButton("Удалить клиента");
        removeClientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeClient();
            }
        });
        clientControlPanel.add(removeClientButton);

        clientPanel.add(clientControlPanel, BorderLayout.SOUTH);

        mainPanel.add(clientPanel);

        // Trainer Panel
        JPanel trainerPanel = new JPanel(new BorderLayout());
        trainerPanel.setBorder(BorderFactory.createTitledBorder("Тренеры"));

        trainerList = new JList<>(trainerListModel);
        trainerPanel.add(new JScrollPane(trainerList), BorderLayout.CENTER);

        JPanel trainerControlPanel = new JPanel(new GridLayout(3, 1));
        JButton addTrainerButton = new JButton("Добавить тренера");
        addTrainerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addTrainer();
            }
        });
        trainerControlPanel.add(addTrainerButton);

        JButton removeTrainerButton = new JButton("Удалить тренера");
        removeTrainerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeTrainer();
            }
        });
        trainerControlPanel.add(removeTrainerButton);

        trainerPanel.add(trainerControlPanel, BorderLayout.SOUTH);

        mainPanel.add(trainerPanel);

        add(mainPanel, BorderLayout.CENTER);

        // Report Panel
        JPanel reportPanel = new JPanel();
        JButton generateReportButton = new JButton("Сформировать отчет");
        generateReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateReport();
            }
        });
        reportPanel.add(generateReportButton);
        add(reportPanel, BorderLayout.SOUTH);
    }

    private void addClient() {
        String name = JOptionPane.showInputDialog(this, "Введите имя клиента:");
        if (name != null && !name.trim().isEmpty()) {
            String[] subscriptionTypes = {"Месячный", "Годовой"};
            String subscriptionType = (String) JOptionPane.showInputDialog(this, "Выберите тип абонемента:", 
                    "Абонемент", JOptionPane.QUESTION_MESSAGE, null, subscriptionTypes, subscriptionTypes[0]);
            if (subscriptionType != null) {
                Client client = new Client(name, subscriptionType);
                clients.add(client);
                clientListModel.addElement(client);
            }
        }
    }

    private void removeClient() {
        Client selectedClient = clientList.getSelectedValue();
        if (selectedClient != null) {
            clients.remove(selectedClient);
            clientListModel.removeElement(selectedClient);
        } else {
            JOptionPane.showMessageDialog(this, "Выберите клиента для удаления.");
        }
    }

    private void addTrainer() {
        String name = JOptionPane.showInputDialog(this, "Введите имя тренера:");
        if (name != null && !name.trim().isEmpty()) {
            Trainer trainer = new Trainer(name);
            trainers.add(trainer);
            trainerListModel.addElement(trainer);
        }
    }

    private void removeTrainer() {
        Trainer selectedTrainer = trainerList.getSelectedValue();
        if (selectedTrainer != null) {
            trainers.remove(selectedTrainer);
            trainerListModel.removeElement(selectedTrainer);
        } else {
            JOptionPane.showMessageDialog(this, "Выберите тренера для удаления.");
        }
    }

    private void generateReport() {
        StringBuilder report = new StringBuilder("Отчет:\n\nКлиенты:\n");
        for (Client client : clients) {
            report.append(client).append("\n");
        }
        report.append("\nТренеры:\n");
        for (Trainer trainer : trainers) {
            report.append(trainer).append("\n");
        }
        JOptionPane.showMessageDialog(this, report.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SportsComplexGUI().setVisible(true);
            }
        });
    }
}
