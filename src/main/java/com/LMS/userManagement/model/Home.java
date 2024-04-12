package com.LMS.userManagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Home {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    public String tenantId;

    public String homeTitle;

    public String courseTitle;

    public String courseTitle2;

    @Column(columnDefinition = "TEXT")
    public String theme;

    public String standard;

    public String educationTitle;

    @Column(columnDefinition = "TEXT")
    public String promoTitle;

    @Column(columnDefinition = "TEXT")
    public String promoVideo;

    @Column(columnDefinition = "TEXT")
    public String promoDescription;

    @Column(columnDefinition = "TEXT")
     public String bannerImage;

    public String supportNumber;

    @Column(columnDefinition = "TEXT")
    public String bannerHeading;

    @Column(columnDefinition = "TEXT")
    public String bannerSubHeading;

    @Column(columnDefinition = "TEXT")
    public String bannerParagraph;
}
