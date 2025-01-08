package com.travel.dao;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travel.entity.TourPackage;

public interface TourPackageRepository extends JpaRepository<TourPackage, Integer> {

	TourPackage findPackageByName(String name);
	
	boolean existsByName(String name);
}
