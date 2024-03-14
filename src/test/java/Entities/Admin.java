package Entities;

public class Admin extends User{

    //private String smsCode;
    //private String confirmPassword;

    public Admin(String email, String password) {
        super(email,password);
    }
    public Admin(String email, String password, String lastChildSal, String firstChildName, String lastChildName, String childMobbNo, String childemailis) {
        super(email, password, lastChildSal, firstChildName, lastChildName, childMobbNo, childemailis);
        //setSmsCode(smsCode);
        //setPassword(password);
        //setConfirmPassword(confirmPassword);
    }

    public Admin(String email, String password, String firstChildName, String childMobbNo, String clinicStreetName, String clinicStreetNo, String clinicCity, String clinicZipcode, String blankparam) {
        super(email, password, firstChildName, childMobbNo, clinicStreetName, clinicStreetNo, clinicCity, clinicZipcode, blankparam);
    }

    // public String getSmsCode() {
    //     return smsCode;
    // }
    // public void setSmsCode(String smsCode) {
    //     this.smsCode = smsCode;
    // }
    // public String getConfirmPassword() {
    //     return confirmPassword;
    // }
    // public void setConfirmPassword(String confirmPassword) {
    //     this.confirmPassword = confirmPassword;
    // }

}
