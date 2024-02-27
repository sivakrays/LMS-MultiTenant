package com.LMS.userManagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EduContent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @Column(columnDefinition = "TEXT")
    public String image;

    @Column(columnDefinition = "TEXT")
    public String imageContent;

    public String tenantId;

    public String standard;

}
