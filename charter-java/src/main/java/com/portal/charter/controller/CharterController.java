package com.portal.charter.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.portal.charter.dao.CharterDao;

@RestController
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
public class CharterController {

	@Autowired
	private CharterDao charterDao;

	@GetMapping(value = "/restaurants", produces = "application/json")
	public ResponseEntity<List<Map<String, Object>>> getRestaurants(
			@RequestParam(name = "name", required = false) String name,
			@RequestParam(name = "state", required = false) String state,
			@RequestParam(name = "genre", required = false) String genre) {
		List<Map<String, Object>> result = new ArrayList<>();
		try {
			result = charterDao.getRestaurants(name, state, genre);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (result != null && !result.isEmpty()) {
			return new ResponseEntity<List<Map<String, Object>>>(result, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<Map<String, Object>>>(result, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(value = "/count", produces = "application/json")
	public ResponseEntity<Integer> getCount(@RequestParam(name = "name", required = false) String name,
			@RequestParam(name = "state", required = false) String state,
			@RequestParam(name = "genre", required = false) String genre) {
		Integer result = 0;
		try {
			result = charterDao.getCount();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}

}
