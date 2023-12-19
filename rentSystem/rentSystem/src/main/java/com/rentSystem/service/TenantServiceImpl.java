package com.rentSystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rentSystem.model.Tenant;
import com.rentSystem.repository.TenantRepository;

@Service
public class TenantServiceImpl implements TenantService {

    @Autowired
    private TenantRepository tenantRepository;

    @Override
    public List<Tenant> getAllTenants() {
        return tenantRepository.findAll();
    }

    @Override
    public void saveTenant(Tenant tenant) {
        tenantRepository.save(tenant);
    }

    @Override
    public Tenant getTenantById(long id) {
        Optional<Tenant> optional = tenantRepository.findById(id);
        return optional.orElse(null);
    }

    @Override
    public void deleteTenantById(long id) {
        tenantRepository.deleteById(id);
    }
}
