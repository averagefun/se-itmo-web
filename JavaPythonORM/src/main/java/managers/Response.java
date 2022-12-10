package managers;

public class Response<T> {
    private boolean success;
    private T body;

    public boolean isSuccess() {
        return success;
    }

    public T getBody() {
        return body;
    }
}
