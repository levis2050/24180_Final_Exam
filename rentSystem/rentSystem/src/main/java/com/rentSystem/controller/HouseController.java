package com.rentSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rentSystem.model.House;
import com.rentSystem.service.HouseService;



@Controller
public class HouseController {

	@Autowired
	private HouseService houseService;
	
	// display list of Houses
	@GetMapping("/house")
	public String viewHomePage(Model model) {
		return findPaginated(1, "amount", "asc", model);		
	}
	
	@GetMapping("/showNewHouseForm")
	public String showNewHouseForm(Model model) {
		// create model attribute to bind form data
		House house = new House();
		model.addAttribute("house", house);
		return "new_house";
	}
	
	@PostMapping("/saveHouse")
	public String saveHouse(@ModelAttribute("house") House house) {
		// save House to database
		houseService.saveHouse(house);
		return "redirect:/";
	}
	
	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable ( value = "id") long id, Model model) {
		
		// get House from the service
		House house = houseService.getHouseById(id);
		
		// set House as a model attribute to pre-populate the form
		model.addAttribute("house", house);
		return "update_house";
	}
	
	@GetMapping("/deleteHouse/{id}")
	public String deleteHouse(@PathVariable (value = "id") long id) {
		
		// call delete House method 
		this.houseService.deleteHouseById(id);
		return "redirect:/";
	}
	
	
	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable (value = "pageNo") int pageNo, 
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
		int pageSize = 5;
		
		Page<House> page = houseService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<House> listHouses = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("listHouses", listHouses);
		return "house";
	}
}