package com.rentSystem.controller;

import com.rentSystem.model.House;
import com.rentSystem.model.Payment;
import com.rentSystem.model.Tenant;
import com.rentSystem.service.HouseService;
import com.rentSystem.service.PaymentService;
import com.rentSystem.service.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private HouseService houseService;

    @Autowired
    private TenantService tenantService;

    @GetMapping("/payment")
    public String viewPayments(Model model) {
        return findPaginatedPayments(1, "amount", "asc", model);
    }

    @GetMapping("/showNewPaymentForm")
    public String showNewPaymentForm(Model model) {
        List<House> houses = houseService.getAllHouses();
        model.addAttribute("houses", houses);

        List<Tenant> tenants = tenantService.getAllTenants();
        model.addAttribute("tenants", tenants);

        Payment payment = new Payment();
        model.addAttribute("payment", payment);

        return "new_payment";
    }

    @PostMapping("/savePayment")
    public String savePayment(@ModelAttribute("payment") Payment payment) {
        paymentService.savePayment(payment);

        // Get the total number of pages
        int totalPages = paymentService.findPaginated(1, 5, "amount", "asc").getTotalPages();

        // Redirect to the last page
        return "redirect:/payment/pagePayments/" + totalPages + "?sortField=amount&sortDir=asc";
    }


    @GetMapping("/showFormForUpdatePayment/{id}")
    public String showFormForUpdatePayment(@PathVariable(value = "id") long id, Model model) {
        Payment payment = paymentService.getPaymentById(id);
        model.addAttribute("payment", payment);

        List<House> houses = houseService.getAllHouses();
        model.addAttribute("houses", houses);

        List<Tenant> tenants = tenantService.getAllTenants();
        model.addAttribute("tenants", tenants);

        return "update_payment";
    }

    @GetMapping("/deletePayment/{id}")
    public String deletePayment(@PathVariable(value = "id") long id) {
        paymentService.deletePaymentById(id);
        return "redirect:/payment";
    }

    @GetMapping("/pagePayments/{pageNo}")
    public String findPaginatedPayments(@PathVariable(value = "pageNo") int pageNo,
                                        @RequestParam("sortField") String sortField,
                                        @RequestParam("sortDir") String sortDir,
                                        Model model) {
        int pageSize = 5;
        Page<Payment> page = paymentService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Payment> listPayments = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listPayments", listPayments);

        return "payment";
    }
}
