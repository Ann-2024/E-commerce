package com.example.Ecommerce.Model.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {

    ADMIN_READ("admin:read"),
    ADMIN_CREATE("admin:create"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_DELETE("admin:delete"),
    CUSTOMER_READ("customer:read"),
    CUSTOMER_CREATE("customer:create"),
    CUSTOMER_UPDATE("customer:update"),
    CUSTOMER_DELETE("customer:delete"),
    SELLER_READ("seller:read"),
    SELLER_CREATE("seller:create"),
    SELLER_UPDATE("seller:update"),
    SELLER_DELETE("seller:delete"),

    ;

    @Getter
    private final String permission;
}

