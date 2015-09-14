
public class DictionaryException extends Exception {
    String message;

    public DictionaryException() {
        super();
    }

    public DictionaryException(String message) {
        super();
        this.message = message;
    }

    public DictionaryException(Throwable cause) {
        //super(cause);
        initCause(cause);
    }

    @Override
    public String toString() {
        return message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
