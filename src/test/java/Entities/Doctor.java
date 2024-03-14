package Entities;

public class Doctor extends User{

    private String smsCode;
    private String confirmPassword;

    public Doctor(String email, String password) {
        super(email, password);
    }

    public Doctor(String email, String password, String smsCode, String confirmPassword) {
        super(email, password);
        setSmsCode(smsCode);
        setPassword(password);
        setConfirmPassword(confirmPassword);
    }

    public String getSmsCode() {
        return smsCode;
    }
    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }
    public String getConfirmPassword() {
        return confirmPassword;
    }
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

}
