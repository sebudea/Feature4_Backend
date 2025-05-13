package com.backend.couriersyncfeat4.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name="alert")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Alert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private SystemUser user;

    @ManyToOne
    @JoinColumn(name="package_id", nullable = false)
    private PackageEntity packag;

    @ManyToOne
    @JoinColumn(name="alert_type_id", nullable = false)
    private AlertType alertType;

    private String description;
    private LocalDateTime registeredAt;
}
