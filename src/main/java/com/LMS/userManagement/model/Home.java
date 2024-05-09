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

    @Column(columnDefinition = "VARCHAR(255) default ''")
    public String tenantId;

    @Column(columnDefinition = "VARCHAR(255) default ''")
    public String homeTitle;

    @Column(columnDefinition = "VARCHAR(255) default ''")
    public String courseTitle;

    @Column(columnDefinition = "VARCHAR(255) default ''")
    public String courseTitle2;

    @Column(columnDefinition = "TEXT default ''")
    public String theme;

    @Column(columnDefinition = "VARCHAR(255) default ''")
    public String standard;

    @Column(columnDefinition = "VARCHAR(255) default ''")
    public String educationTitle;

    @Column(columnDefinition = "TEXT default ''")
    public String promoTitle;

    @Column(columnDefinition = "TEXT default ''")
    public String promoVideo;

    @Column(columnDefinition = "TEXT default ''")
    public String promoDescription;

    @Column(columnDefinition = "TEXT default ''")
     public String bannerImage;

    @Column(columnDefinition = "VARCHAR(255) default ''")
    public String supportNumber;

    @Column(columnDefinition = "TEXT default ''")
    public String bannerHeading;

    @Column(columnDefinition = "TEXT default ''")
    public String bannerSubHeading;

    @Column(columnDefinition = "TEXT default ''")
    public String bannerParagraph;

    @Column(columnDefinition = "VARCHAR(255) default ''")
    public String supportEmail;

    @OneToMany(targetEntity = EduContent.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "home_id",referencedColumnName = "id")
    public List<EduContent> eduContents;
}
