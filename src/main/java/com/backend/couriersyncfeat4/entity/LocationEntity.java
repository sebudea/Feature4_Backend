package com.backend.couriersyncfeat4.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "location")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LocationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="handler_user_id", nullable = false)
    private UserEntity handlerUser;

    @ManyToOne
    @JoinColumn(name="package_id", nullable = false)
    private PackageEntity packageEntity;

    private Float latitude;
    private Float longitude;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @Column(nullable = false)
    private String address;
}
