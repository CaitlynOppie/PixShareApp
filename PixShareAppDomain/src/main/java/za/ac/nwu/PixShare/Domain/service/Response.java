package za.ac.nwu.PixShare.Domain.service;

import java.io.Serializable;
import java.util.Objects;

public class Response<T> implements Serializable {

    private final boolean successful;

    public Response(boolean successful){
        this.successful = successful;
    }

    public boolean isSuccessful() {
        return successful;
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Response<?> that = (Response<?>) o;
        return successful == that.successful;
    }

    @Override
    public int hashCode() {return Objects.hash(successful);}

    @Override
    public String toString() {
        return "GeneralResponse{" +
                "successful =" + successful +
                '}';
    }
}
