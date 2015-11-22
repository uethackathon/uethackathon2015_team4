package com.thangtv.surrounding.network.model.subject;

/**
 * Created by Ngo Van Tan on 11/22/2015.
 */
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class GetAllSubject {
    @SerializedName("status")
    @Expose
    private int status;
    @SerializedName("data")
    @Expose
    private List<List<Data>> data = new ArrayList<List<Data>>();
    @SerializedName("message")
    @Expose
    private String message;

    /**
     *
     * @return
     * The status
     */
    public int getStatus() {
        return status;
    }

    /**
     *
     * @param status
     * The status
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     *
     * @return
     * The data
     */
    public List<List<Data>> getData() {
        return data;
    }

    /**
     *
     * @param data
     * The data
     */
    public void setData(List<List<Data>> data) {
        this.data = data;
    }

    /**
     *
     * @return
     * The message
     */
    public String getMessage() {
        return message;
    }

    /**
     *
     * @param message
     * The message
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
