/*
package com.LMS.userManagement.model;

import com.LMS.userManagement.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
@Table(name = "token")
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String token;

    private String tokenType;

    private Boolean expired;

    private  Boolean revoked;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
*/
