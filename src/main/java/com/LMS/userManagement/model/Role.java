
package com.LMS.userManagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {



        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;

        private String role;



    }


