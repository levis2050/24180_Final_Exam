package com.rentSystem.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.rentSystem.model.House;
public interface HouseService {
	List<House> getAllHouses();
	void saveHouse(House house);
	House getHouseById(long id);
	void deleteHouseById(long id);
	Page<House> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);

}
