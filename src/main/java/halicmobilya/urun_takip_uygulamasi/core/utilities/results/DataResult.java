package halicmobilya.urun_takip_uygulamasi.core.utilities.results;

public class DataResult<T> extends Result{

    private T data;
    private String token;

    public DataResult(T data, boolean success, String message) {
        super(success, message);
        this.data = data;
    }

    public DataResult(T data, boolean success, String message, String token) {
        super(success, message);
        this.data = data;
        this.token = token;
    }

    public DataResult(T data, boolean success) {
        super(success);
        this.data = data;
    }

    public T getData(){
        return this.data;
    }

    public String getToken(){
        return this.token;
    }

}
