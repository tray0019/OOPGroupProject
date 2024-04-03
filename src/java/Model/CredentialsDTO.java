package Model;

public abstract class CredentialsDTO {
    
    protected String userType;
    protected String emailAddress; 
    protected String password;
    protected String location;
    private String phoneNumber; //Phone number should be string
    
    
    public String getUserType(){
        return userType;
    }
    
    public void setUserType(String userType){
        this.userType = userType;
    }
    
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @param phoneNumber the phoneNumber to set
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
