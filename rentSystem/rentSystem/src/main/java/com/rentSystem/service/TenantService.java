package com.rentSystem.service;

import java.util.List;

import com.rentSystem.model.Tenant;

public interface TenantService {
    List<Tenant> getAllTenants();
    void saveTenant(Tenant tenant);
    Tenant getTenantById(long id);
    void deleteTenantById(long id);
}
