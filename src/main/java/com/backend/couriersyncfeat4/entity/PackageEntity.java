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
public class PackageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private SystemUser user;

    @ManyToOne
    @JoinColumn(name="status_id", nullable = false)
    private PackageStatus status;

    @Column(name = "tracking_code", updatable = false, nullable = false)
    private UUID trackingCode;

    private String description;

    @Column(nullable = false, insertable = false, updatable = false)
    private LocalDateTime registeredAt;

    private String destination;

    // TODO: Revisar la Pre Persistencia
    @PrePersist
    public void prePersist() {
        if (trackingCode == null) {
            trackingCode = UUID.randomUUID();
        }
    }

}
