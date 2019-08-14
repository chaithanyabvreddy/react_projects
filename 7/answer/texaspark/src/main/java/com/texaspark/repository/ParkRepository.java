package com.texaspark.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.texaspark.Park;

public interface ParkRepository extends CrudRepository<Park, Long> {

	public List<Park> getParkByName(String username);
	
}
