package model;

import interfaces.ClientOperations;
import interfaces.Repository;
import interfaces.ServerOperations;
import interfaces.ServerGUI;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Server implements ServerOperations, Repository {
    private final File FILE = new File("log.txt");
    private final ServerGUI SERVER_GUI;
    private final List<ClientOperations> CLIENT_OPERATIOS_LIST = new LinkedList<>();

    public Server(ServerGUI serverGUI) {
        this.SERVER_GUI = serverGUI;
        SERVER_GUI.showMessage("Server START");
        SERVER_GUI.showMessage(getLog());
    }

    @Override
    public void saveLog(String log) {
        Date date = new Date();
        try (FileWriter writer = new FileWriter(FILE, true)) {
            writer.write(date + "\n" + log+ "\n");
            writer.flush();
        } catch (IOException ex) {
            SERVER_GUI.showMessage("ERROR WRITING FILE.\n");
        }
    }

    @Override
    public String getLog() {
        StringBuilder result = new StringBuilder();
        try (FileReader fr = new FileReader(FILE)) {
            int i = fr.read();
            while (i != -1) {
                result.append((char) i);
                i = fr.read();
            }
               return result.toString();
        } catch (IOException ex) {
               return "ERROR READER FILE.\n";
        }
    }

    @Override
    public void connect(ClientOperations client) {
        CLIENT_OPERATIOS_LIST.add(client);
        client.getMessage(getLog());
        sendMessage(client.getName() + " connect");

    }

    @Override
    public void disconnect(ClientOperations client) {
        String message = client.getName() + " disconnect";
        for (ClientOperations co : CLIENT_OPERATIOS_LIST) {
            if (co.equals(client)) {
                CLIENT_OPERATIOS_LIST.remove(client);
                if (CLIENT_OPERATIOS_LIST.isEmpty()) {
                    SERVER_GUI.showMessage("No connected clients");
                    saveLog(message);
                } else {
                    sendMessage(message);
                }
            }
        }
    }

    @Override
    public void sendMessage(String message) {
        for (ClientOperations client : CLIENT_OPERATIOS_LIST) {
            client.getMessage(message);
        }
        SERVER_GUI.showMessage(message);
        saveLog(message);
    }
}
