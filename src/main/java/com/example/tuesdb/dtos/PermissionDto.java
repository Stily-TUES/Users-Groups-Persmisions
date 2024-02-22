package com.example.tuesdb.dtos;

import com.example.tuesdb.models.Group;
import com.example.tuesdb.models.Permission;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class PermissionDto {

    private long id;
    @NotBlank
    private String permissionType;

    private Set<GroupDto> groups;

    public PermissionDto(Permission permission) {
        this.id = permission.getId();
        this.permissionType = permission.getPermissionType();
        this.groups = Optional.ofNullable(permission.getGroups())
                .orElse(Collections.emptySet())
                .stream()
                .map(GroupDto::new)
                .collect(Collectors.toSet());
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String setPermissionType() {
        return permissionType;
    }

    public void setPermissionType(String permissionType) {
        this.permissionType = permissionType;
    }

    public Set<GroupDto> getGroups() {
        return groups;
    }

    public void setGroups(Set<GroupDto> groups) {
        this.groups = groups;
    }

}
