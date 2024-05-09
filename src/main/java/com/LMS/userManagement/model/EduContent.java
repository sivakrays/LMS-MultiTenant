package com.LMS.userManagement.model;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    @Column(columnDefinition = "VARCHAR(255) default ''")
    public String imageTitle;

    @Column(columnDefinition = "TEXT default ''")
    public String image;

    @Column(columnDefinition = "TEXT default ''")
    public String imageContent;

    @JsonProperty(value = "homeId")
    public Long home_id;
}
