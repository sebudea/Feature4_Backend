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
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private SystemUser user;

    @ManyToOne
    @JoinColumn(name="package_id", nullable = false)
    private PackageEntity packag;

    private Float latitude;
    private Float longitude;

    @Column(nullable = false, insertable = false, updatable = false)
    private LocalDateTime updatedAt;

    private String address;
}
