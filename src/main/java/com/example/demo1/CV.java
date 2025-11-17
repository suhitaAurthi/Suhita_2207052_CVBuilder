package com.example.demo1;

public class CV {
    private String fullName;
    private String email;
    private String phone;
    private String address;
    private String education;
    private String skills;
    private String workExperience;
    private String projects;
    private String photoPath;

    public CV() {
    }

    public CV(String fullName, String email, String phone, String address,
              String education, String skills, String workExperience, String projects, String photoPath) {
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.education = education;
        this.skills = skills;
        this.workExperience = workExperience;
        this.projects = projects;
        this.photoPath = photoPath;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getWorkExperience() {
        return workExperience;
    }

    public void setWorkExperience(String workExperience) {
        this.workExperience = workExperience;
    }

    public String getProjects() {
        return projects;
    }

    public void setProjects(String projects) {
        this.projects = projects;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }
}
