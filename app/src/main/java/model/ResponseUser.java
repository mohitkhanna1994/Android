package model;

/**
 * Created by user on 3/12/2018.
 */

public class ResponseUser {
    private int status;
    private String message;
    private String clientMsg;
    private long timestamp;
    private UserReg result;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getClientMsg() {
        return clientMsg;
    }

    public void setClientMsg(String clientMsg) {
        this.clientMsg = clientMsg;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public UserReg getResult() {
        return result;
    }

    public void setResult(UserReg result) {
        this.result = result;
    }
}
