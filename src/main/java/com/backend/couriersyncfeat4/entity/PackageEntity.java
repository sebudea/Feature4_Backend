package com.backend.couriersyncfeat4.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "package")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PackageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="owner_user_id", nullable = false)
    private UserEntity ownerUser;

    @ManyToOne
    @JoinColumn(name="status_id", nullable = false)
    private PackageStatusEntity status;

    @Column(name = "tracking_code", updatable = false, nullable = false, columnDefinition = "UUID", unique = true)
    private UUID trackingCode;

    private String description;

    @Column(nullable = false, updatable = false)
    private LocalDateTime registeredAt;

    @Column(nullable = false)
    private String origin;

    @Column(nullable = false)
    private String destination;

    @OneToMany(mappedBy = "packageEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LocationEntity> locationEntities;

    @OneToMany(mappedBy = "packageEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AlertEntity> alertEntities;

    // TODO: Revisar la Pre Persistencia
    @PrePersist
    public void prePersist() {
        if (trackingCode == null) {
            trackingCode = UUID.randomUUID();
        }
        if (registeredAt == null) {
            registeredAt = LocalDateTime.now();
        }
    }

}
