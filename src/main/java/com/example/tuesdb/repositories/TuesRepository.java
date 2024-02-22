package com.example.tuesdb.repositories;

import com.example.tuesdb.dtos.GroupDto;
import com.example.tuesdb.dtos.PermissionDto;
import com.example.tuesdb.dtos.UserDto;
import com.example.tuesdb.models.Group;
import com.example.tuesdb.models.Permission;
import com.example.tuesdb.models.User;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@org.springframework.stereotype.Repository
public class TuesRepository {
    private final EntityManager entityManager;

    public TuesRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<UserDto> getAllUsers() {
        return entityManager.createQuery("SELECT u FROM User u", User.class)
                .getResultList()
                .stream()
                .map(UserDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public UserDto createUser(UserDto userInputDto) {
        User newUser = new User();

        newUser.setUsername(userInputDto.getUsername());
        newUser.setPassword(new BCryptPasswordEncoder().encode(userInputDto.getPassword()));
        newUser.setFirstName(userInputDto.getFirstName());
        newUser.setLastName(userInputDto.getLastName());
        newUser.setPermissions(userInputDto.getPermissions()
                .stream()
                .map(permissionDto -> entityManager.find(Permission.class, permissionDto.getId()))
                .collect(Collectors.toSet()));
        newUser.setGroups(userInputDto.getGroups()
                .stream()
                .map(groupDto -> entityManager.find(Group.class, groupDto.getId()))
                .collect(Collectors.toSet()));

        entityManager.persist(newUser);

        return new UserDto(newUser);
    }

    public List<GroupDto> getAllGroups() {
        return entityManager.createQuery("SELECT g FROM Group g", Group.class)
                .getResultList()
                .stream()
                .map(GroupDto::new)
                .collect(Collectors.toList());
    }

    public List<PermissionDto> getAllPermissions() {
        return entityManager.createQuery("SELECT p FROM Permission p", Permission.class)
                .getResultList()
                .stream()
                .map(PermissionDto::new)
                .collect(Collectors.toList());
    }
}
