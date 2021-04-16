package com.gvs.framework.data.pojo;

public class AdminOrDirector {
    private String firstName;
    private String password;
    private String contactNumber;
    private String recoveryPassword;
    private String emailConfirmationCode;
    private String fullName;
    private String contactSupportId;
    private String emailId;
    private String govGatewayIdentityNumber;
    private String secondName;

    public String getFirstName () { return firstName; }
    public void setFirstName (String firstName) { this.firstName = firstName; }

    public String getPassword () { return password; }
    public void setPassword (String password) { this.password = password; }

    public String getContactNumber () { return contactNumber; }
    public void setContactNumber (String contactNumber) { this.contactNumber = contactNumber; }

    public String getRecoveryPassword () { return recoveryPassword; }
    public void setRecoveryPassword (String recoveryPassword) { this.recoveryPassword = recoveryPassword; }

    public String getEmailConfirmationCode () { return emailConfirmationCode; }
    public void setEmailConfirmationCode (String emailConfirmationCode) { this.emailConfirmationCode = emailConfirmationCode; }

    public String getFullName () { return fullName; }
    public void setFullName (String fullName) { this.fullName = fullName; }

    public String getContactSupportId () { return contactSupportId; }
    public void setContactSupportId (String contactSupportId) { this.contactSupportId = contactSupportId; }

    public String getEmailId () { return emailId; }
    public void setEmailId (String emailId) { this.emailId = emailId; }

    public String getGovGatewayIdentityNumber () { return govGatewayIdentityNumber; }
    public void setGovGatewayIdentityNumber (String govGatewayIdentityNumber) { this.govGatewayIdentityNumber = govGatewayIdentityNumber; }

    public String getSecondName () { return secondName; }
    public void setSecondName (String secondName) { this.secondName = secondName; }

    @Override
    public String toString()
    {
        return "ClassPojo [firstName = "+firstName+", password = "+password+", contactNumber = "+contactNumber+", recoveryPassword = "+recoveryPassword+", emailConfirmationCode = "+emailConfirmationCode+", fullName = "+fullName+", contactSupportId = "+contactSupportId+", emailId = "+emailId+", govGatewayIdentityNumber = "+govGatewayIdentityNumber+", second Name = "+secondName+"]";
    }
}