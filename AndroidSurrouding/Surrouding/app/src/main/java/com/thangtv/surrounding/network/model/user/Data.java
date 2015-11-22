
package com.thangtv.surrounding.network.model.user;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

@Generated("org.jsonschema2pojo")
public class Data {

    @SerializedName("userid")
    @Expose
    private String userid;
    @SerializedName("user")
    @Expose
    private String user;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("avatar")
    @Expose
    private String avatar;
    @SerializedName("cover")
    @Expose
    private String cover;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("career")
    @Expose
    private String career;
    @SerializedName("location_id")
    @Expose
    private String locationId;
    @SerializedName("chatting_id")
    @Expose
    private String chattingId;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("description")
    @Expose
    private String description;

    /**
     * 
     * @return
     *     The userid
     */
    public String getUserid() {
        return userid;
    }

    /**
     * 
     * @param userid
     *     The userid
     */
    public void setUserid(String userid) {
        this.userid = userid;
    }

    /**
     * 
     * @return
     *     The user
     */
    public String getUser() {
        return user;
    }

    /**
     * 
     * @param user
     *     The user
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * 
     * @return
     *     The password
     */
    public String getPassword() {
        return password;
    }

    /**
     * 
     * @param password
     *     The password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 
     * @return
     *     The firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * 
     * @param firstName
     *     The first_name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * 
     * @return
     *     The lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * 
     * @param lastName
     *     The last_name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * 
     * @return
     *     The date
     */
    public String getDate() {
        return date;
    }

    /**
     * 
     * @param date
     *     The date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * 
     * @return
     *     The address
     */
    public String getAddress() {
        return address;
    }

    /**
     * 
     * @param address
     *     The address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 
     * @return
     *     The avatar
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * 
     * @param avatar
     *     The avatar
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /**
     * 
     * @return
     *     The cover
     */
    public String getCover() {
        return cover;
    }

    /**
     * 
     * @param cover
     *     The cover
     */
    public void setCover(String cover) {
        this.cover = cover;
    }

    /**
     * 
     * @return
     *     The phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 
     * @param phone
     *     The phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 
     * @return
     *     The career
     */
    public String getCareer() {
        return career;
    }

    /**
     * 
     * @param career
     *     The career
     */
    public void setCareer(String career) {
        this.career = career;
    }

    /**
     * 
     * @return
     *     The locationId
     */
    public String getLocationId() {
        return locationId;
    }

    /**
     * 
     * @param locationId
     *     The location_id
     */
    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    /**
     * 
     * @return
     *     The chattingId
     */
    public String getChattingId() {
        return chattingId;
    }

    /**
     * 
     * @param chattingId
     *     The chatting_id
     */
    public void setChattingId(String chattingId) {
        this.chattingId = chattingId;
    }

    /**
     * 
     * @return
     *     The email
     */
    public String getEmail() {
        return email;
    }

    /**
     * 
     * @param email
     *     The email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 
     * @return
     *     The gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * 
     * @param gender
     *     The gender
     */
    public void setGender(String gender) {
        this.gender = gender;
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

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(userid).append(user).append(password).append(firstName).append(lastName).append(date).append(address).append(avatar).append(cover).append(phone).append(career).append(locationId).append(chattingId).append(email).append(gender).append(description).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Data) == false) {
            return false;
        }
        Data rhs = ((Data) other);
        return new EqualsBuilder().append(userid, rhs.userid).append(user, rhs.user).append(password, rhs.password).append(firstName, rhs.firstName).append(lastName, rhs.lastName).append(date, rhs.date).append(address, rhs.address).append(avatar, rhs.avatar).append(cover, rhs.cover).append(phone, rhs.phone).append(career, rhs.career).append(locationId, rhs.locationId).append(chattingId, rhs.chattingId).append(email, rhs.email).append(gender, rhs.gender).append(description, rhs.description).isEquals();
    }

}
