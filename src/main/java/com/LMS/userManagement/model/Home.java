package com.LMS.userManagement.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import java.util.List;

public class PromoVideos {

    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    public String tenantId;

    public String homeTitle;
    public String standard;

    @Column(columnDefinition = "TEXT")
    public String promoTitle;

    public List<String> promoVideo;

    @Column(columnDefinition = "TEXT")
    public String promoDescription;
}
