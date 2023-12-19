package com.rentSystem.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.rentSystem.model.House;
import com.rentSystem.repository.HouseRepository;


@Service
public class HouseServiceImpl implements HouseService {

	@Autowired
	private HouseRepository houseRepository;

	@Override
	public List<House> getAllHouses() {
		return houseRepository.findAll();
	}

	@Override
	public void saveHouse(House house) {
		this.houseRepository.save(house);
	}

	@Override
	public House getHouseById(long id) {
		Optional<House> optional = houseRepository.findById(id);
		House house = null;
		if (optional.isPresent()) {
			house = optional.get();
		} else {
			throw new RuntimeException(" House not found for id :: " + id);
		}
		return house;
	}

	@Override
	public void deleteHouseById(long id) {
		this.houseRepository.deleteById(id);
	}

	@Override
	public Page<House> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.houseRepository.findAll(pageable);
	}


}