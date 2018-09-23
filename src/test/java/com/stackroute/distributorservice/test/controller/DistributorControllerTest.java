package com.stackroute.distributorservice.test.controller;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.distributorservice.controller.DistributorController;
import com.stackroute.distributorservice.exceptions.DistributorAlreadyExistsException;
import com.stackroute.distributorservice.exceptions.DistributorNotFoundException;
import com.stackroute.distributorservice.model.Distributor;
import com.stackroute.distributorservice.service.DistributorService;

public class DistributorControllerTest {

	  @Autowired
	    private MockMvc mockMvc;
	    @MockBean
	    private Distributor distributor;
	    @MockBean
	    private DistributorService distributorService;
	    @InjectMocks
	    private DistributorController distributorController;

	    @Before
	    public void setUp() {
	        MockitoAnnotations.initMocks(this);
	        mockMvc = MockMvcBuilders.standaloneSetup(distributorController).build();
	        distributor = new Distributor();
	        distributor.setDistributorId("Vignesh008");
	        distributor.setDistributorName("Vignesh");
	        distributor.setDistributorLocation("Bangalore");
	    }

	    @Test
	    public void registerDistributorSuccess() throws Exception {

	        when(distributorService.createDistributor(distributor)).thenReturn(distributor);
	        assertNotNull("hi");
	        mockMvc.perform(post("/api/v1/distributor")
	                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(distributor)))
	                .andExpect(status().isCreated()).andDo(print());

	    }

	  @Test
	    public void registerDistributorFailure() throws Exception {

	        when(distributorService.createDistributor(any())).thenThrow(DistributorAlreadyExistsException.class);

	        mockMvc.perform(post("/api/v1/distributor")
	                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(distributor)))
	                .andExpect(status().isConflict()).andDo(print());
	        	assertNotNull("hi");
	    }


	    @Test
	    public void getLocationSuccess() throws Exception {
	    	distributor.setDistributorLocation("Chennai");
	    	when(distributorService.createDistributor(any())).thenThrow(DistributorAlreadyExistsException.class);
	        when(distributorService.distributorLocation(eq(distributor.getDistributorLocation()))).thenReturn(distributor);
	        mockMvc.perform(put("/api/v1/distributor/Vignesh008")
	                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(distributor)))
	                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
	        	assertNotNull("hello");
	    }

	    @Test
	    public void getLocationFailure() throws Exception {
	   
	    	distributor.setDistributorLocation("Chennai");
	            when(distributorService.distributorLocation(eq(distributor.getDistributorLocation()))).thenThrow(DistributorNotFoundException.class);
	            mockMvc.perform(put("/api/v1/distributor/Vignesh008")
	                    .contentType(MediaType.APPLICATION_JSON).content(asJsonString(distributor)))
	                    .andExpect(status().isNotFound()).andDo(MockMvcResultHandlers.print());
	            assertNotNull("hi");
	    }
/*
	    @Test
	    public void deleteUserSuccess() throws Exception {
	        when(distributorService.deleteDistributor("Vignesh008")).thenReturn(true);
	        mockMvc.perform(delete("/api/v1/distributor/Vignesh008")
	                .contentType(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk())
	                .andDo(MockMvcResultHandlers.print());

	    }


	    @Test
	    public void deleteUserFailure() throws Exception {
	        when(distributorService.deleteDistributor("Vignesh008")).thenThrow(DistributorNotFoundException.class);
	        mockMvc.perform(delete("/api/v1/distributor/Vignesh008")
	                .contentType(MediaType.APPLICATION_JSON))
	                .andExpect(status().isNotFound())
	                .andDo(MockMvcResultHandlers.print());

	    }


	    @Test
	    public void getByDistributorIdSuccess() throws Exception {

	        when(distributorService.getDistributorById("Vignesh008")).thenReturn(distributor);
	        mockMvc.perform(get("/api/v1/distributor/Vignesh008").contentType(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk())
	                .andDo(MockMvcResultHandlers.print());
	    }

	    @Test
	    public void getByDistributorIdFailure() throws Exception {

	        when(distributorService.getDistributorById("Vignesh008")).thenThrow(DistributorNotFoundException.class);
	        mockMvc.perform(get("/api/v1/distributor/Vignesh008").contentType(MediaType.APPLICATION_JSON))
	                .andExpect(status().isNotFound())
	                .andDo(MockMvcResultHandlers.print());
	    }*/

	    public static String asJsonString(final Object obj) {
	        try {
	            return new ObjectMapper().writeValueAsString(obj);
	        } catch (Exception e) {
	            return e.getMessage();
	        }
	    }
	}