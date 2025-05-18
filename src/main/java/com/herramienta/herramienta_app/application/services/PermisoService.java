package com.herramienta.herramienta_app.application.services;

public interface PermisoService {
    boolean hasPermission(String permission);

    boolean hasAnyPermission(String... permissions);

    boolean hasAllPermissions(String... permissions);

    boolean hasRole(String role);

    boolean hasAnyRole(String... roles);

    boolean hasAllRoles(String... roles);
}
