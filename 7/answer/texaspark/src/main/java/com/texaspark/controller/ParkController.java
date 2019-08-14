package com.texaspark.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.texaspark.ParkForm;
import com.texaspark.service.ParkService;

@RestController
public class ParkController {

	@Autowired
	private ParkService parkService;

	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value = "/createPark", method = RequestMethod.POST)
	public ResponseEntity<ParkForm> create(@RequestBody ParkForm parkForm) {
		System.out.println(parkForm.getId());
		if (parkForm.getId() != null) {
			parkService.update(parkForm);
			return ResponseEntity.status(HttpStatus.OK).body(parkForm);
		} else {
			ParkForm savedParkForm = parkService.create(parkForm);
			if (savedParkForm.getErrorMessage() != null) {
				return ResponseEntity.status(HttpStatus.CONFLICT).body(savedParkForm);
			} else {
				return ResponseEntity.status(HttpStatus.OK).body(savedParkForm);
			}

		}

	}

	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value = "/getAllParks", method = RequestMethod.GET)
	public List<ParkForm> getAllParks() {

		List<ParkForm> parkFormList = parkService.getAll();
		return parkFormList;
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value = "/deletePark", method = RequestMethod.POST)
	public boolean delete(@RequestBody ParkForm parkForm) {
		boolean isDeleted = parkService.delete(parkForm.getId());
		return isDeleted;
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value = "/updatePark", method = RequestMethod.POST)
	public void update(@RequestBody ParkForm parkForm) {
		parkService.update(parkForm);
	}

}
