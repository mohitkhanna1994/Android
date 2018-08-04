package model;

/**
 * Created by user on 3/10/2018.
 */

public class UserReg {
    private String address,alterPhone,city,company,country,createdBy,createdDate,dob,docImg,email,eyeColor,fullName,gender,govtIdName,govtIdValue,govtIddoc,houseType,id,maritalStatus,nickName,occupation,primaryPhone,profileImg,registeredDt,secret,state,userName,userRegId;
    int age,longitude,latitude,noOfKids,noOfResidents,rating,zipCode;
    boolean otpVeriFied,userStatus;
    String preferredReq[],preferredTask[];
    Meta meta;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAlterPhone() {
        return alterPhone;
    }

    public void setAlterPhone(String alterPhone) {
        this.alterPhone = alterPhone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getDocImg() {
        return docImg;
    }

    public void setDocImg(String docImg) {
        this.docImg = docImg;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(String eyeColor) {
        this.eyeColor = eyeColor;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGovtIdName() {
        return govtIdName;
    }

    public void setGovtIdName(String govtIdName) {
        this.govtIdName = govtIdName;
    }

    public String getGovtIdValue() {
        return govtIdValue;
    }

    public void setGovtIdValue(String govtIdValue) {
        this.govtIdValue = govtIdValue;
    }

    public String getGovtIddoc() {
        return govtIddoc;
    }

    public void setGovtIddoc(String govtIddoc) {
        this.govtIddoc = govtIddoc;
    }

    public String getHouseType() {
        return houseType;
    }

    public void setHouseType(String houseType) {
        this.houseType = houseType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getPrimaryPhone() {
        return primaryPhone;
    }

    public void setPrimaryPhone(String primaryPhone) {
        this.primaryPhone = primaryPhone;
    }

    public String getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }

    public String getRegisteredDt() {
        return registeredDt;
    }

    public void setRegisteredDt(String registeredDt) {
        this.registeredDt = registeredDt;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserRegId() {
        return userRegId;
    }

    public void setUserRegId(String userRegId) {
        this.userRegId = userRegId;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getLongitude() {
        return longitude;
    }

    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }

    public int getLatitude() {
        return latitude;
    }

    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }

    public int getNoOfKids() {
        return noOfKids;
    }

    public void setNoOfKids(int noOfKids) {
        this.noOfKids = noOfKids;
    }

    public int getNoOfResidents() {
        return noOfResidents;
    }

    public void setNoOfResidents(int noOfResidents) {
        this.noOfResidents = noOfResidents;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public boolean isOtpVeriFied() {
        return otpVeriFied;
    }

    public void setOtpVeriFied(boolean otpVeriFied) {
        this.otpVeriFied = otpVeriFied;
    }

    public boolean isUserStatus() {
        return userStatus;
    }

    public void setUserStatus(boolean userStatus) {
        this.userStatus = userStatus;
    }

    public String[] getPreferredReq() {
        return preferredReq;
    }

    public void setPreferredReq(String[] preferredReq) {
        this.preferredReq = preferredReq;
    }

    public String[] getPreferredTask() {
        return preferredTask;
    }

    public void setPreferredTask(String[] preferredTask) {
        this.preferredTask = preferredTask;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }
}
