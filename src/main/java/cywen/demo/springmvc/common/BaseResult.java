package cywen.demo.springmvc.common;

import java.io.Serializable;
import java.util.Objects;

public class BaseResult<T> implements Serializable {

    private String status = "0000";
    private String message = "ok";
    private boolean success = true;
    private T data;

    private BaseResult(){}

    private BaseResult(T data){
        this.data = data;
    }

    private BaseResult(String message){
        this.status = "0001";
        this.message = message;
        this.success = false;
    }

    public static BaseResult buildSuccess(){
        return new BaseResult();
    }

    @SuppressWarnings("unchecked")
    public static <T> BaseResult buildSuccess(T data){
        return new BaseResult(Objects.isNull(data) ? new Object() : data);
    }

    public static BaseResult buildFailure(String message){
        return new BaseResult(message);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
