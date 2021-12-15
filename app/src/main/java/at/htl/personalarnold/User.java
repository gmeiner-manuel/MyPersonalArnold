package at.htl.personalarnold;

import java.io.Serializable;

public class User implements Serializable {
    private String email;
    private String username;
    private String password;
    private int age;

    /**
     *
     * @param email E-Mail Adress of the user
     * @param username Name of the user
     * @param password Password of the user
     * @param age age of the User
     */
    public User(String email, String username, String password, int age) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.age = age;
    }

    /**
     *
     * @return Returns the email
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @return Returns the username
     */
    public String getUsername() {
        return username;
    }

    /**
     *
     * @return Returns the password
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @return returns the Age
     */
    public int getAge() {
        return age;
    }

    /**
     *
     * @return Returns a String with all the User information
     */
    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                '}';
    }
}
