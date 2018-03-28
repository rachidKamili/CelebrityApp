package me.kamili.rachid.celebrityapp.model;

/**
 * Created by Admin on 3/28/2018.
 */

public class Celebrity {
    private String firstName;
    private String lastName;
    private String occupation;
    private Boolean isFavorite;

    public Celebrity(String firstName, String lastName, String occupation, Boolean isFavorite) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.occupation = occupation;
        this.isFavorite = isFavorite;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public Boolean getFavorite() {
        return isFavorite;
    }

    public void setFavorite(Boolean favorite) {
        isFavorite = favorite;
    }
}
