package interfaces;

public interface ClientOperations extends ServerOperations {
    void getMessage(String message);
    String getName();
}
