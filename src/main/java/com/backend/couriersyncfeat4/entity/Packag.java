package com.backend.couriersyncfeat4.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "package")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Packag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private SystemUser user;

    @ManyToOne
    @JoinColumn(name="status_id", nullable = false)
    private PackageStatus status;

    @Column(name = "tracking_code", columnDefinition = "UUID DEFAULT gen_random_uuid()", updatable = false, nullable = false)
    private UUID tracking_code;

    private String description;
    private LocalDateTime registered_at;
    private String destination;

}
