package com.LMS.userManagement.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CurrentTimestamp;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Schema(description = "cartId",example = "08a56153-2d7f-46a6-8e03-a1d94fbb5ee6")
    private UUID cartId;
    @Schema(description = "courseId",example = "a95410fb-673a-436e-82da-6ed07a43dcee")
    private UUID courseId;
    @Schema(description = "userId",example = "1")
    private Long userId;
    @CurrentTimestamp
    @Schema(description = "createDate",example = "2024-02-21 11:48:19.918662")
    private Timestamp createDate;
}
