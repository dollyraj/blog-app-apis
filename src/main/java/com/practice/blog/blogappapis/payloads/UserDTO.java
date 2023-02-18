package com.practice.blog.blogappapis.payloads;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class UserDTO {
    private int id;

   @NotEmpty
   @Size(min=4,message="User Name must be of minimum 4 characters!")
    private String name;

   @NotEmpty(message = "Email id cannot be empty")
   @Email(message = "Email address is not valid!")
   private String emailId;
   @NotEmpty
   @Size(min=3,max=10,message = "Password must be of minimum 3 character and maximum of 10 characters")
    private String password;
   @NotEmpty(message="About can be empty")
    private String about;

    public UserDTO(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", emailId='" + emailId + '\'' +
                ", password='" + password + '\'' +
                ", about='" + about + '\'' +
                '}';
    }
}
