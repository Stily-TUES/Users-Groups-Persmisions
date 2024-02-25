package com.example.tuesdb.dtos;

import com.example.tuesdb.models.Group;
import jakarta.validation.constraints.NotBlank;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class GroupDto {

    private long id;
    @NotBlank
    private String name;

    private Set<PermissionDto> permissions;

    public GroupDto(Group group) {
        this.id = group.getId();
        this.name = group.getName();
        this.permissions = Optional.ofNullable(group.getPermissions())
                .orElse(Collections.emptySet())
                .stream()
                .map(PermissionDto::new)
                .collect(Collectors.toSet());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<PermissionDto> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<PermissionDto> permissions) {
        this.permissions = permissions;
    }
}
