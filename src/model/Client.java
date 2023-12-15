package model;

import interfaces.ClientOperations;
import interfaces.ServerOperations;
import interfaces.ClientGUI;

public class Client implements ClientOperations {
    private final ServerOperations SERVER;
    private final ClientGUI CLIENT_GUI;
    private final String NAME;

    public Client(ServerOperations server, ClientGUI clientGUI) {
        this.SERVER = server;
        this.CLIENT_GUI = clientGUI;
        this.NAME = CLIENT_GUI.getName();
        connect(this);
    }

    @Override
    public void connect(ClientOperations client) {
        SERVER.connect(client);
    }

    @Override
    public void disconnect(ClientOperations client) {
        SERVER.disconnect(client);
    }

    @Override
    public void sendMessage(String message) {
        SERVER.sendMessage(message);
    }

    @Override
    public void getMessage(String message) {
        CLIENT_GUI.showMessage(message);
    }

    @Override
    public String getName() {
        return NAME;
    }
}
