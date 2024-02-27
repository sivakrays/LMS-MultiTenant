package com.LMS.userManagement.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

public class Home {

    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @Column(columnDefinition = "TEXT")
    public String title;

    @Column(columnDefinition = "TEXT")
    public String image;
    @Column(columnDefinition = "TEXT")
    public String imageContent;

    public String tenantId;

    public String standard;

}
