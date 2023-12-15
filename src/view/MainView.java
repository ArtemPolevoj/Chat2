package view;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.*;
import java.net.Socket;
import java.util.Date;

public abstract class MainView extends JFrame{
    protected final int HEIGHT = 400;
    protected final int WIDTH = 300;
    protected final int POS_X = 100;
    protected final int POS_Y = 200;
    protected final Border BORDER = new LineBorder(Color.LIGHT_GRAY);
    protected final String HOST = "127.0.0.1";
    protected final int PORT_NUMBER = 4444;
    protected final JTextArea LOG_TEXT_AREA = new JTextArea();
    protected final JScrollPane SCROLL_CHAT_LOG = new JScrollPane(LOG_TEXT_AREA);

    MainView() {
        LOG_TEXT_AREA.setBorder(BORDER);
        LOG_TEXT_AREA.setLineWrap(true);
        LOG_TEXT_AREA.setEditable(false);
        LOG_TEXT_AREA.setWrapStyleWord(true);
        SCROLL_CHAT_LOG.setVerticalScrollBarPolicy(20);
        SCROLL_CHAT_LOG.setVisible(true);
        add(SCROLL_CHAT_LOG, BorderLayout.CENTER);
        setBounds(POS_X, POS_Y, WIDTH, HEIGHT);
    }
 }
