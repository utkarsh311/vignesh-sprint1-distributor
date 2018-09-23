package com.stackroute.distributorservice.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Distributor {
	
	@Id
	private String distributorId;
	private String distributorName;
	private String distributorLocation;
	private List<Movies> movies;

	public Distributor(String distributorId, String distributorName, String distributorLocation, List<Movies> movies) {
		super();
		this.distributorId = distributorId;
		this.distributorName = distributorName;
		this.distributorLocation = distributorLocation;
		this.movies = movies;
	}

	
	public Distributor() {
		super();
		// TODO Auto-generated constructor stub
	}


	public String getDistributorId() {
		return distributorId;
	}

	public void setDistributorId(String distributorId) {
		this.distributorId = distributorId;
	}

	public String getDistributorName() {
		return distributorName;
	}

	public void setDistributorName(String distributorName) {
		this.distributorName = distributorName;
	}

	public String getDistributorLocation() {
		return distributorLocation;
	}

	public void setDistributorLocation(String distributorLocation) {
		this.distributorLocation = distributorLocation;
	}

	public List<Movies> getMovies() {
		return movies;
	}

	public void setMovies(List<Movies> movies) {
		this.movies = movies;
	}

	@Override
	public String toString() {
		return "Distributor [distributorId=" + distributorId + ", distributorName=" + distributorName
				+ ", distributorLocation=" + distributorLocation + ", movies=" + movies + "]";
	}


}