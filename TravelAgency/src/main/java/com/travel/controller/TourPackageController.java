package com.travel.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.travel.entity.TourPackage;
import com.travel.service.TourPackageService;

@RestController
@RequestMapping("/tourPackage")
public class TourPackageController {

	@Autowired
	private TourPackageService tourPackageService;

	@PostMapping("/add")
	public ResponseEntity<?> addTourPackage(@RequestBody TourPackage tourPackage) {
		try {
			if (tourPackageService.existsByName(tourPackage.getName())) {
				return new ResponseEntity<>("A tour package with the same name already exists.",
						HttpStatus.BAD_REQUEST);
			}

			TourPackage packageAdded = tourPackageService.addTourPackage(tourPackage);
			return new ResponseEntity<>(packageAdded, HttpStatus.CREATED);

		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/all")
	public ResponseEntity<?> getAllTourPackages() {
		try {
			Collection<TourPackage> getPackages = tourPackageService.getAllPackages();
			return new ResponseEntity<>(getPackages, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/getOne/{name}")
	public ResponseEntity<?> getPackageByName(@PathVariable String name) {
		try {
			TourPackage findPackageByName = tourPackageService.getTourPackageByName(name);
			return new ResponseEntity<>(findPackageByName, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateTourPackageById(@PathVariable Integer id, @RequestBody TourPackage updatePackage) {
		try {
			TourPackage updatedTourPackage = tourPackageService.updatePackageById(id, updatePackage);
			return new ResponseEntity<>(updatedTourPackage, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deletePackageById(@PathVariable Integer id) {
		try {
			String deleteTourPackage = tourPackageService.deleteTourPackage(id);
			return new ResponseEntity<>(deleteTourPackage, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
