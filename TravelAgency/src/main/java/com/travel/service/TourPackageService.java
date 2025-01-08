package com.travel.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel.dao.TourPackageRepository;
import com.travel.entity.TourPackage;
import com.travel.exception.PackageNotFound;

@Service
public class TourPackageService {

	@Autowired
	private TourPackageRepository tourpackagerepo;
	
	public boolean existsByName(String name) {
	    return tourpackagerepo.existsByName(name);
	}
	
	public TourPackage addTourPackage(TourPackage tourPackage) {
		TourPackage tourSaved = tourpackagerepo.save(tourPackage);
		return tourSaved;
	}
	
	public Collection<TourPackage> getAllPackages(){
		List<TourPackage> getAllTour = tourpackagerepo.findAll();
		return getAllTour;
	}
	
	public TourPackage getTourPackageByName (String name) {
		TourPackage findPackage = tourpackagerepo.findPackageByName(name);
		return findPackage;
	}
	
	public TourPackage updatePackageById (Integer id, TourPackage updatePackage) {
		TourPackage foundPackage = tourpackagerepo.findById(id).orElseThrow(()-> new PackageNotFound("The said ID of the package is not found in the database!!!"));
		foundPackage.setName(updatePackage.getName());
		foundPackage.setDuration(updatePackage.getDuration());
		foundPackage.setPrice(updatePackage.getPrice());
		foundPackage.setTransportationType(updatePackage.getTransportationType());
		
		TourPackage updatedTourPackage = tourpackagerepo.save(foundPackage);
		return updatedTourPackage;
	}
	
	
	public String deleteTourPackage(Integer id) {
		TourPackage deletePackage = tourpackagerepo.findById(id).orElseThrow(()-> new PackageNotFound("The said ID of the package is not found in the database!!!"));
		tourpackagerepo.delete(deletePackage);
		
		return "The Package with id "+deletePackage+" is deleted!!!";
	}

	
	
}
