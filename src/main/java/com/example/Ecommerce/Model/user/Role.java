package com.example.Ecommerce.Model.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.Ecommerce.Model.user.Permission.*;

@RequiredArgsConstructor
public enum Role {
    ADMIN(Set.of(
            ADMIN_READ,ADMIN_CREATE,ADMIN_UPDATE,ADMIN_DELETE,
            CUSTOMER_READ,CUSTOMER_CREATE,CUSTOMER_UPDATE, CUSTOMER_DELETE,
            SELLER_READ,SELLER_CREATE,SELLER_UPDATE, SELLER_DELETE

    )),
    CUSTOMER(Set.of(
            CUSTOMER_READ, CUSTOMER_CREATE, CUSTOMER_UPDATE, CUSTOMER_DELETE
    )),
    SELLER(Set.of(
            SELLER_READ, SELLER_CREATE, SELLER_UPDATE, SELLER_DELETE

    ));

    @Getter
    private final Set<Permission> permissions;

    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = getPermissions()
                .stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getPermission()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
//    public List<SimpleGrantedAuthority> getAuthorities() {
//        // Return only the role as a SimpleGrantedAuthority
//        return List.of(new SimpleGrantedAuthority(this.name()));
//    }
}
