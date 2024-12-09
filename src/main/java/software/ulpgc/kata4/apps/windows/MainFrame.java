package software.ulpgc.kata4.apps.windows;



import software.ulpgc.kata4.architecture.control.Command;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class MainFrame extends JFrame {
    private final JFreeBarchartDisplay display;
    private final Map<String, Command> commandMap;
    public MainFrame() throws HeadlessException {
        this.display = new JFreeBarchartDisplay();
        this.setTitle("Film Industry People stats");
        this.setSize(1200,1000);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        this.add(display);
        this.add(BorderLayout.NORTH, createActionBar());
        this.commandMap = new HashMap<>();
    }

    public JFreeBarchartDisplay getDisplay() {
        return display;
    }

    public Command put(String key, Command value) {
        return commandMap.put(key, value);
    }

    private Component createActionBar() {
        JPanel jPanel = new JPanel();
        jPanel.add(toggleButton());
        return jPanel;
    }

    private Component toggleButton() {
        Button button = new Button("toggle");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                commandMap.get("toggle").execute();
            }
        });
        return button;
    }
}
