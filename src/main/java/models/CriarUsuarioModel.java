package models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"email", "password", "passwordConfirmation"})
public class CriarUsuarioModel {
    private String email;
    private String password;
    private String passwordConfirmation;

    public CriarUsuarioModel() {
        email = "test@testng.com";
        password = "123456";
        passwordConfirmation = "123456";
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @JsonProperty("password_confirmation")
    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    @JsonProperty("password_confirmation")
    public void setPasswordConfirmation(String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
    }
}