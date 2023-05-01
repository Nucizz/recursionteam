package com.example.recursionteamapp.objects;

public class User {
    int id;
    private String username;
    private String email;
    private String hashedPassword;

    public User(int id, String username, String email, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.hashedPassword = encrypt(password);
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public int getId() {
        return id;
    }

    static public String encrypt(String input) {
        StringBuilder encrypted = new StringBuilder();
        int inputLen = input.length();

        //Hashing
        char[] inputArr = input.toCharArray();
        for(int i=0;i<inputLen;i++) {
            encrypted.append( (char) (((i*i + (int) inputArr[(inputLen - 1)]) % 64) + i) );
        }

        //Fill spaces
        char[] baseHash = encrypted.toString().toCharArray();
        for(int i=inputLen;i<64;i++) {
            encrypted.append( (char) ( (i % 32) + (int) baseHash[i % inputLen]) );
        }

        return encrypted.toString();
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }
}
