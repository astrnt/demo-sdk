package co.astrnt.demosdk.dao;

/**
 * Created by deni rohimat on 26/11/18
 */
public class BaseApiDao {

    private String status;
    private String message;

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
}
