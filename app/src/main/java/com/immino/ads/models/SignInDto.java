package com.immino.ads.models;

public class SignInDto {

    private String Username;

    private String PasswordHash;

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public SignInDto(String username, String passwordHash) {
        Username = username;
        PasswordHash = passwordHash;
    }
}
