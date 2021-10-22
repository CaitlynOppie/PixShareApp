package za.ac.nwu.PixShare.Domain.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import za.ac.nwu.PixShare.Domain.persistence.User;

import java.util.Objects;

@ApiModel(value = "User", description = "A DTO that represents the User")
public class UserDTO {

    private Integer userID;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;

    public UserDTO() {
    }

    public UserDTO(Integer userID, String firstName, String lastName, String email, String username, String password) {
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public UserDTO(String firstName, String lastName, String email, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public UserDTO(User user) {
        this.setUserID(user.getUserID());
        this.setFirstName(user.getFirstName());
        this.setLastName(user.getLastName());
        this.setEmail(user.getEmail());
        this.setUsername(user.getUsername());
        this.setPassword(user.getPassword());
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    @ApiModelProperty(position = 1,
        value = "User firstName",
        name = "firstName",
        notes = "Identifies the name of the user",
        dataType = "java.lang.String",
        example = "Caitlyn",
        required = true)

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @ApiModelProperty(position = 2,
        value = "User lastName",
        name = "lastName",
        notes = "Identifies the surname of the user",
        dataType = "java.lang.String",
        example = "Opperman",
        required = true)

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @ApiModelProperty(position = 3,
        value = "User email",
        name = "email",
        notes = "Identifies the email of the user",
        dataType = "java.lang.String",
        example = "caitlyn.opperman@gmail.com",
        required = true)

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @ApiModelProperty(position = 4,
        value = "User username",
        name = "username",
        notes = "Identifies the username of the user",
        dataType = "java.lang.String",
        example = "CaitlynOppie",
        required = true)

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @ApiModelProperty(position = 5,
        value = "User password",
        name = "password",
        notes = "Identifies the password of the user",
        dataType = "java.lang.String",
        example = "Yjig@465t",
        required = true)

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @JsonIgnore
    public User getUser(){
        return new User(getUserID(), getFirstName(), getLastName(), getEmail(), getUsername(), getPassword());
    }

    @JsonIgnore
    public UserDTO getUserDTO(){
        return new UserDTO(
                getUserID(), getFirstName(), getLastName(), getEmail(), getUsername(), getPassword());
    }

    @JsonIgnore
    public User getUID(){
        return new User(getUserID());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return Objects.equals(userID, userDTO.userID) && Objects.equals(firstName, userDTO.firstName) && Objects.equals(lastName, userDTO.lastName) && Objects.equals(email, userDTO.email) && Objects.equals(username, userDTO.username) && Objects.equals(password, userDTO.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userID, firstName, lastName, email, username, password);
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "userID=" + userID +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
