package com.example.littlemarketplaceapp;

class ForOwner {
    private String fullname;
    private String username;
    private String email;
    private String mobile;
    private String password;
    private String shopname;
    private final char type;

    public ForOwner(String fullname, String username, String email, String mobile, String password, String shopname) {
        this.fullname = fullname;
        this.username = username;
        this.email = email;
        this.mobile = mobile;
        this.password = password;
        this.shopname = shopname;
        type='O';
    }

    public char getType() {
        return type;
    }

    public String getFullname() {
        return fullname;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getMobile() {
        return mobile;
    }

    public String getPassword() {
        return password;
    }

    public String getShopname() {
        return shopname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
    }
}
