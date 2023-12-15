package view;

import interfaces.ServerGUI;
import model.Server;
import interfaces.ServerOperations;

import javax.swing.*;
import java.awt.*;

public class ServerView extends MainView implements Runnable, ServerGUI {

    private int count = 0;
    private final JButton BTN_START = new JButton("Start");
    private final JButton BTN_STOP = new JButton("Stop");
    private final JPanel PAN_BUTTON =
            new JPanel(new GridLayout(1, 2));
    private ServerOperations server;


    @Override
    public void run() {
        setTitle("Chat server");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        PAN_BUTTON.add(BTN_START);
        PAN_BUTTON.add(BTN_STOP);
        BTN_START.addActionListener(e -> {
            if (count == 0) {
                server = new Server(this);
            }
            count++;
            SwingUtilities.invokeLater(new ClientView(server, count));

        });
        BTN_STOP.addActionListener(e -> System.exit(0));
        add(PAN_BUTTON, BorderLayout.SOUTH);
        setVisible(true);
    }
@Override
    public void showMessage(String message) {
        LOG_TEXT_AREA.append(message + "\n");
    }
}
