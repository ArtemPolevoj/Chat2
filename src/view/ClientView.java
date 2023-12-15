package view;

import interfaces.ClientGUI;
import model.Client;
import interfaces.ClientOperations;
import interfaces.ServerOperations;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ClientView extends MainView implements Runnable, ClientGUI {

    private final JPanel OUT_MASSAGE = new JPanel(new BorderLayout());
    private final JTextField IP_ADDRESS = new JTextField(HOST);
    private final JTextField PORT = new JTextField(String.valueOf(PORT_NUMBER));
    private final JTextField NAME = new JTextField();
    private final JPasswordField PASSWORD_FIELD = new JPasswordField();
    private final JTextField TEXT_MESSAGE_FIELD = new JTextField();
    private final int shift;
    private final ServerOperations SERVER;
    private ClientOperations client;

    public ClientView(ServerOperations server, int shift) {
        this.SERVER = server;
        this.shift = shift;
    }

    private boolean checkSetting() {
        if (IP_ADDRESS.getText().isEmpty()) {
            LOG_TEXT_AREA.setText("IP_ADDRESS is empty.");
            return false;
        } else if (PORT.getText().isEmpty()) {
            LOG_TEXT_AREA.setText("PORT is empty.");
            return false;
        } else if (NAME.getText().isEmpty()) {
            LOG_TEXT_AREA.setText("NAME is empty.");
            return false;
        } else if (PASSWORD_FIELD.getPassword().length == 0) {
            LOG_TEXT_AREA.setText("PASSWORD is empty.");
            return false;
        } else {
            return true;
        }
    }

    private void sendMessage() {
        if (!TEXT_MESSAGE_FIELD.getText().isEmpty()) {
            String message = NAME.getText() + ": " + TEXT_MESSAGE_FIELD.getText();
            client.sendMessage(message);
            TEXT_MESSAGE_FIELD.setText(null);
        }
    }

    @Override
    public void run() {
        setTitle("Chat client");

        LOG_TEXT_AREA.setText(null);

        JPanel settingPanel = new JPanel(new GridLayout(1, 2));
        settingPanel.add(IP_ADDRESS);
        settingPanel.add(PORT);

        JPanel registrationPanel = new JPanel(new GridLayout(1, 3));
        registrationPanel.add(NAME);
        registrationPanel.add(PASSWORD_FIELD);
        JButton btnLogin = new JButton("Login");
        registrationPanel.add(btnLogin);

        JPanel head = new JPanel(new GridLayout(2, 3));
        head.setBorder(BORDER);
        head.add(settingPanel);
        head.add(registrationPanel);

        OUT_MASSAGE.add(TEXT_MESSAGE_FIELD, BorderLayout.CENTER);
        JButton btnSend = new JButton("send");
        OUT_MASSAGE.add(btnSend, BorderLayout.EAST);
        OUT_MASSAGE.setVisible(false);
        btnLogin.addActionListener(e -> {
            if (checkSetting()) {
                LOG_TEXT_AREA.setText(null);
                OUT_MASSAGE.setVisible(true);
                head.setVisible(false);
                client = new Client(SERVER, this);
            }
        });
        btnSend.addActionListener(e -> sendMessage());

        TEXT_MESSAGE_FIELD.addActionListener(e -> sendMessage());
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                client.disconnect(client);
            }
        });

        add(head, BorderLayout.NORTH);
        add(OUT_MASSAGE, BorderLayout.SOUTH);
        setBounds(POS_X + WIDTH + shift * 50, POS_Y + shift * 50, WIDTH, HEIGHT);
        setVisible(true);
    }

    @Override
    public void showMessage(String message) {
        LOG_TEXT_AREA.append(message + "\n");
    }

    @Override
    public String getName() {
        return NAME.getText();
    }
}
