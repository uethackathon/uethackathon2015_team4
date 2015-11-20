
package com.thangtv.surrounding.network.model;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Datum {

    @SerializedName("subject_id")
    @Expose
    private String subjectId;
    @SerializedName("subject_group_id")
    @Expose
    private String subjectGroupId;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;

    /**
     * 
     * @return
     *     The subjectId
     */
    public String getSubjectId() {
        return subjectId;
    }

    /**
     * 
     * @param subjectId
     *     The subject_id
     */
    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    /**
     * 
     * @return
     *     The subjectGroupId
     */
    public String getSubjectGroupId() {
        return subjectGroupId;
    }

    /**
     * 
     * @param subjectGroupId
     *     The subject_group_id
     */
    public void setSubjectGroupId(String subjectGroupId) {
        this.subjectGroupId = subjectGroupId;
    }

    /**
     * 
     * @return
     *     The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * 
     * @param title
     *     The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 
     * @return
     *     The description
     */
    public String getDescription() {
        return description;
    }

    /**
     * 
     * @param description
     *     The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

}
