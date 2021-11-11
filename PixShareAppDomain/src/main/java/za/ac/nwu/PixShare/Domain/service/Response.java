package za.ac.nwu.PixShare.Domain.service;


import java.io.Serializable;
import java.util.Objects;

public class Response<T> implements Serializable {

    private final boolean successful;
    private final transient T data;

    public Response(boolean successful, T payload){
        this.successful = successful;
        this.data = payload;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public T getData() {
        return data;
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Response<?> that = (Response<?>) o;
        return successful == that.successful && Objects.equals(data, that.data);
    }

    @Override
    public int hashCode() {return Objects.hash(successful, data);}

    @Override
    public String toString() {
        return "GeneralResponse{" +
                "successful =" + successful +
                ", payload =" + data +
                '}';
    }
}
