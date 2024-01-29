package com.LMS.userManagement.enumFile;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.LMS.userManagement.enumFile.Permission.*;

@RequiredArgsConstructor
public enum Roles {

    USER(Set.of(USER_READ)),
    ADMIN(Set.of(
            ADMIN_READ,
            ADMIN_CREATE,
            ADMIN_DELETE,
            ADMIN_UPDATE
    ));

    @Getter
    private final Set<Permission> permissions;

    public List<SimpleGrantedAuthority> getAuthorities(){
      List<SimpleGrantedAuthority> authorities=  getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.name()))
                .collect(Collectors.toList());

      authorities.add(new SimpleGrantedAuthority("ROLE_"+ this.name()));

      return authorities;
    }
}
