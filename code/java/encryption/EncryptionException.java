package project.name.encryption; //NOSONAR

public class EncryptionException extends RuntimeException {

    public EncryptionException(String message, Exception exception) {
        super(message, exception);
    }

    public EncryptionException(String message) {
        super(message);
    }
}
