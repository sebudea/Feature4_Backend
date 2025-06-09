package com.backend.couriersyncfeat4.config;

import com.backend.couriersyncfeat4.entity.*;
import com.backend.couriersyncfeat4.repository.*;
import com.backend.couriersyncfeat4.service.*;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Component
@Profile("dev")
public class DatabaseSeeder implements CommandLineRunner {

    private final LocationRepository locationRepository;
    private final PackageRepository packageRepository;
    private final UserService userService;
    private final RoleRepository roleRepository;
    private final PackageStatusRepository packageStatusRepository;
    private final Faker faker = new Faker();

    @Autowired
    public DatabaseSeeder(PackageStatusRepository packageStatusRepository, LocationRepository locationRepository, RoleRepository roleRepository, PackageRepository packageRepository, UserService userService) {
        this.locationRepository = locationRepository;
        this.packageRepository = packageRepository;
        this.userService = userService;
        this.roleRepository = roleRepository;
        this.packageStatusRepository = packageStatusRepository;
    }

    @Value("${app.seed-database}")
    private boolean seedDatabase;

    @Override
    public void run(String... args) {
        if (seedDatabase) {
            List<String> ROLES = Arrays.asList("ADMIN", "SUPERVISOR", "WAREHOUSE_STAFF", "LOGISTICS_STAFF", "CUSTOMER");
            for (String roleName : ROLES) {
                RoleEntity roleEntity = new RoleEntity();
                roleEntity.setName(roleName);
                roleRepository.save(roleEntity);
            }

            List<String> statuses = Arrays.asList("PENDING","IN_TRANSIT","DELIVERED","CANCELLED","RETURNED");
            for (String statusName : statuses) {
                PackageStatusEntity statusEntity = new PackageStatusEntity();
                statusEntity.setName(statusName);
                packageStatusRepository.save(statusEntity);
            }
            List<PackageStatusEntity> allStatuses = packageStatusRepository.findAll();

            for (int i = 0; i < 10; i++) {
                UserEntity user = new UserEntity();
                int roleId = faker.number().numberBetween(1, 5);
                RoleEntity roleEntity = new RoleEntity();
                roleEntity.setId(roleId);
                user.setName(faker.name().fullName());
                user.setEmail(faker.internet().emailAddress());
                user.setRoleEntity(roleEntity);
                userService.addUser(user);
            }

            List<UserEntity> allUsers = userService.findAllUsers();

            for (int i = 0; i < 10; i++) {
                PackageEntity packageEntity = new PackageEntity();
                packageEntity.setOwnerUser(allUsers.get(faker.random().nextInt(allUsers.size())));
                packageEntity.setStatus(allStatuses.get(faker.random().nextInt(statuses.size())));
                packageEntity.setDescription(faker.lorem().sentence());
                packageEntity.setOrigin(faker.address().city());
                packageEntity.setDestination(faker.address().city());
                packageEntity.setRegisteredAt(LocalDateTime.now());
                packageRepository.save(packageEntity);
            }

            List<PackageEntity> allPackages = packageRepository.findAll();

            for (int i = 0; i < 10; i++) {
                LocationEntity locationEntity = new LocationEntity();
                locationEntity.setHandlerUser(allUsers.get(faker.random().nextInt(allUsers.size())));
                locationEntity.setPackageEntity(allPackages.get(faker.random().nextInt(statuses.size())));
                locationEntity.setAddress(faker.address().streetAddress());
                locationEntity.setLatitude((float) faker.random().nextInt(0, 100));
                locationEntity.setLongitude((float) faker.random().nextInt(0, 100));
                locationEntity.setUpdatedAt(LocalDateTime.now());
                locationRepository.save(locationEntity);
            }

            UserEntity user = new UserEntity();
            user.setName(faker.name().fullName());
            user.setEmail("santiago.tbolivar@udea.edu.co");
            RoleEntity roleEntity = new RoleEntity();
            roleEntity.setId(1);
            user.setRoleEntity(roleEntity);
            userService.addUser(user);

            System.out.println("🚀 Base de datos inicializada con datos de ejemplo");
        }
    }
}
