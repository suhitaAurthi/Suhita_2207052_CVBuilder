package com.example.demo1;

public class Profile {

    private int id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String summary;

    public Profile() {}

    public Profile(int id, String name, String email, String phone, String address, String summary) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.summary = summary;
    }

    // getters & setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getSummary() { return summary; }
    public void setSummary(String summary) { this.summary = summary; }
}
