package com.LMS.userManagement.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @Column(columnDefinition = "TEXT")
    public String comment;

    public long userId;

    public int rating;

    //@Column(name = "isActive")
    public boolean active;

   // @Column(name = "isReviewed")
    public boolean reviewed;

    @JsonFormat(pattern="yyyy-MM-dd")
    public Timestamp createdDate;
}
