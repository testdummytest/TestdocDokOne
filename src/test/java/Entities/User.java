package Entities;

public class User {

    private String firstName;
    private String lastName;
    private String birthDate;
    private String email;
    private String mobileNumber;
    private String password;
    private String firstChildName;
    private String lastChildName;
    private String lastChildSal;
    private String childMobbNo;
    private String childemailis;
    private String clinicStreetName;
    private String clinicStreetNo;
    private String clinicCity;
    private String clinicZipcode;
    private String blankparam;

    public User(String firstName, String lastName, String birthDate, String email, String mobileNumber) {
        
        setFirstName(firstName);
        setLastName(lastName);
        setBirthDate(birthDate);
        setEmail(email);
        setMobileNumber(mobileNumber);
    }

    //new created
    public User(String firstName, String lastName, String birthDate, String lastChildSal, String firstChildName, String lastChildName, String childMobbNo, String childemailis) {
        
        setChildSalutation(lastChildSal);
        setChildFirstName(firstChildName);
        setChildLasttName(lastChildName);
        setChildMobileNumber(childMobbNo);
        setChildEmail(childemailis);

        setFirstName(firstName);
        setLastName(lastName);
        setBirthDate(birthDate);
        //setEmail(email);
        //setMobileNumber(mobileNumber);
    }//end


    //for doc assistant
    public User(String email, String password, String lastChildSal, String firstChildName, String lastChildName, String childMobbNo, String childemailis) {
        
        setEmail(email);
        setPassword(password);
        setChildSalutation(lastChildSal);
        setChildFirstName(firstChildName);
        setChildLasttName(lastChildName);
        setChildMobileNumber(childMobbNo);
        setChildEmail(childemailis);
    }//end

    //for create clinic
    public User(String email, String password, String firstChildName, String childMobbNo, String clinicStreetName, String streetNumber, String city, String clinicZipcode, String blankparam) {
        
        setEmail(email);
        setPassword(password);
        setClinicName(firstChildName);
        setClinicMobileNumber(childMobbNo);
        setClinicStreet(clinicStreetName);

        setClinicStreetNumber(streetNumber);
        setClinicity(city);
        setClinicZipcode(clinicZipcode);

    }//end


    public User(String email, String password) {
        setEmail(email);
        setPassword(password);
    }

    public User(String email) {
        setEmail(email);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    //get child fname
    public String getChildFirstName() {
        return firstChildName;
    }

    //set child fname
    public void setChildFirstName(String firstChildName) {
        this.firstChildName = firstChildName;
    }

    //set clinic name
    public void setClinicName(String firstChildName) {
        this.firstChildName = firstChildName;
    }

    //set clinic mobno
    public void setClinicMobileNumber(String childMobbNo) {
        this.childMobbNo = childMobbNo;
    }

    public void setClinicStreet(String clinicStreetName) {
        this.clinicStreetName = clinicStreetName;
    }


    public void setClinicStreetNumber(String clinicStreetNo) {
        this.clinicStreetNo = clinicStreetNo;
    }

    public void setClinicity(String clinicCity) {
        this.clinicCity = clinicCity;
    }

    public void setClinicZipcode(String clinicZipcode) {
        this.clinicZipcode = clinicZipcode;
    }



    //get child lname
    public String getChildLastName() {
        return lastChildName;
    }

    //set child lname
    public void setChildLasttName(String lastChildName) {
        this.lastChildName = lastChildName;
    }


    //get child mobno
    public String getChildMobileNumber() {
        return childMobbNo;
    }

    //get child mobno
    public String getStreet() {
        return clinicStreetName;
    }


    //get child mobno
    public String getClinicStreetNumber() {
        return clinicStreetNo;
    }

    //get child mobno
    public String getClinicCity() {
        return clinicCity;
    }

    //get child mobno
    public String getClinicZipcode() {
        return clinicZipcode;
    }



    //set child mobno
    public void setChildMobileNumber(String childMobbNo) {
        this.childMobbNo = childMobbNo;
    }


    //get child email
    public String getChildEmail() {
        return childemailis;
    }

    //set child email
    public void setChildEmail(String childemailis) {
        this.childemailis = childemailis;
    }



    //get child salutation
    public String getChildSalutation() {
        System.out.println("set salutation physician");
        return lastChildSal;
        
    }

    //set child salutation
    public void setChildSalutation(String lastChildSal) {
        this.lastChildSal = lastChildSal;
        System.out.println("set func");
    }




    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
