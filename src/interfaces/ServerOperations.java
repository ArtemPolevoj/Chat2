package interfaces;

import interfaces.ClientOperations;

public interface ServerOperations {
    void connect(ClientOperations clientOperations);
    void disconnect(ClientOperations clientOperations);
    void sendMessage(String message);
}
