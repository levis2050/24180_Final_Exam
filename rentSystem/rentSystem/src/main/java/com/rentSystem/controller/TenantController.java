package com.rentSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.rentSystem.model.House;
import com.rentSystem.model.Tenant;
import com.rentSystem.service.HouseService;
import com.rentSystem.service.TenantService;

@Controller
public class TenantController {

    @Autowired
    private TenantService tenantService;

    @Autowired
    private HouseService houseService;

    @GetMapping("/tenant")
    public String viewTenantPage(Model model) {
        List<Tenant> tenants = tenantService.getAllTenants();
        model.addAttribute("tenants", tenants);
        return "tenant";
    }

    @GetMapping("/showNewTenantForm")
    public String showNewTenantForm(Model model) {
        List<House> houses = houseService.getAllHouses();
        model.addAttribute("houses", houses);
        model.addAttribute("tenant", new Tenant());
        return "new_tenant";
    }

    @PostMapping("/saveTenant")
    public String saveTenant(@ModelAttribute("tenant") Tenant tenant) {
        tenantService.saveTenant(tenant);
        return "redirect:/tenant";
    }

    @GetMapping("/showFormForUpdateTenant/{id}")
    public String showFormForUpdateTenant(@PathVariable(value = "id") long id, Model model) {
        Tenant tenant = tenantService.getTenantById(id);
        List<House> houses = houseService.getAllHouses();
        model.addAttribute("tenant", tenant);
        model.addAttribute("houses", houses);
        return "update_tenant";
    }

    @GetMapping("/deleteTenant/{id}")
    public String deleteTenant(@PathVariable(value = "id") long id) {
        tenantService.deleteTenantById(id);
        return "redirect:/tenant";
    }
}
