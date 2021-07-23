package io.muic.ssc.WeFit.model;

import java.sql.Date;

public class AppUser {


    long id;
    String username;
    String password;
    String confirm_password;
    String display_name;
    String gender;
    int consumedCal;
    int burnedCal;
    int suggestedCal;
    int age;
    int weight;
    int height;

    String inputDateString;
    Date date;

    public AppUser() {

    }

    public AppUser(String username, String display_name) {
        this.username = username;
        this.display_name = display_name;
    }

    public AppUser(Long userId, String userName) {
        this.id = userId;
        this.username = userName;
    }

    public AppUser(String username, String password, String confirmPassword, String display_name, String gender, int age, int weight, int height) {
        this.username = username;
        this.password = password;
        this.confirm_password = confirmPassword;
        this.display_name = display_name;
        this.gender = gender;
        this.age = age;
        this.weight = weight;
        this.height = height;
    }

    public AppUser(String username, String password, String confirmPassword, String display_name, String gender) {
        this.username = username;
        this.password = password;
        this.confirm_password = confirmPassword;
        this.display_name = display_name;
        this.gender = gender;
    }

    public String getInputDateString() {
        return inputDateString;
    }

    public void setInputDateString(String inputDateString) {
        this.inputDateString = inputDateString;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getHeight() {
        return height;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

    public String getDisplayName() {
        return display_name;
    }

    public void setDisplayName(String displayName) {
        this.display_name = displayName;
    }

    public String getConfirmPassword() {
        return confirm_password;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirm_password = confirmPassword;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }


    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String username) {
        this.username = username;
    }






    public int getConsumedCal() {// get

        return consumedCal;
    }

    public String getConsumedCalString() {// get string

        String get = Integer.toString(consumedCal);
        return get;
    }

    public void setConsumedCal(String consumedCal) {// set

        int i = Integer.parseInt(consumedCal);
        this.consumedCal = i;

    }


    public void setConsumedCal(int consumedCal) {
        this.consumedCal = consumedCal;
    }

    public String getBurnedCalString() {/// get  string

        String get = Integer.toString(burnedCal);
        return get;
    }


    public int getBurnedCal() {/// get

        return burnedCal;
    }


    public void setBurnedCal(String burnedCal) {//// set

        int i = Integer.parseInt(burnedCal);
        this.burnedCal = i;

    }

    public void setBurnedCal(int burnedCal) {
        this.burnedCal = burnedCal;
    }



    public String getSuggestedCalString() {// get string
        String get = Integer.toString(suggestedCal);
        return get;
    }

    public void setSuggestedCal(int suggestedCal) {
        this.suggestedCal = suggestedCal;
    }


    public int getSuggestedCal() { /// get

        return suggestedCal;
    }

    public void setSuggestedCal(String suggestedCal) { //// set

        int get = Integer.parseInt(suggestedCal);

        this.suggestedCal = get;
    }

    public void setSuggestedCalInt(int suggestedCal) { //// set

        this.suggestedCal = suggestedCal;
    }




    @Override
    public String toString() {
        return this.username + "/" + this.password;
    }

}
