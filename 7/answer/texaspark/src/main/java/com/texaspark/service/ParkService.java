package com.texaspark.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.texaspark.Park;
import com.texaspark.ParkForm;
import com.texaspark.repository.ParkRepository;

@Service
public class ParkService {

	@Autowired
	public ParkRepository parkRepository;


	public ParkForm create(ParkForm parkForm) {
		/// if username already exists in databse if yes show errror
		List<Park> parks = parkRepository.getParkByName(parkForm.getName());
		if (parks.size() > 0) {
			parkForm.setErrorMessage("park with same name already exits");
			parkForm.setSuccessMessage(null);
			return parkForm;
		} else {
			// else save to database
			Park park = new Park();
			BeanUtils.copyProperties(parkForm, park);
			Park savedPark = parkRepository.save(park);
			parkForm.setId(savedPark.getId());
			parkForm.setSuccessMessage("Park created");
			parkForm.setErrorMessage(null);
			return parkForm;
		}
	}

	public boolean delete(long parkid) {
		try {
			parkRepository.delete(parkid);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public void update(ParkForm parkForm) {
		Park park = new Park();
		BeanUtils.copyProperties(parkForm, park);
		parkRepository.save(park);
	}

	public List<ParkForm> getAll() {
		List<ParkForm> parkFormList = new ArrayList<ParkForm>();
		Iterable<Park> parks = parkRepository.findAll();
		for (Park park : parks) {
			ParkForm parkForm = new ParkForm();
			BeanUtils.copyProperties(park, parkForm);
			parkFormList.add(parkForm);
		}
		return parkFormList;
	}
}
