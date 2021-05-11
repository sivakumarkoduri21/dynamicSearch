package com.ashokit.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ashokit.entity.InsurancePlans;
import com.ashokit.entity.SearchCriteria;
import com.ashokit.pdf.PDFGenerator;
import com.ashokit.service.InsuranceService;

@RestController
@RequestMapping("/api/insurance")
public class InsuranceplansController {
	@Autowired
	private InsuranceService insuranceService;

	
	

	/*
	 * @GetMapping("/{status}&{name}") public List<InsurancePlans>
	 * getPlansByNameAndStatus(@RequestParam(required = false) String name,
	 * 
	 * @RequestParam(required = false) String status) {
	 * 
	 * if (name != null && status != null) { return
	 * insuranceService.getPlansByNameAndStatus(name, status); } else if (name !=
	 * null) { return insuranceService.getPlansByName(name); } else if (status !=
	 * null) { return insuranceService.getPlansByStatus(status); } else
	 * 
	 * return insuranceService.getAllPlans(); }
	 */

	@PostMapping("/search")
	public ResponseEntity<List<InsurancePlans>> dynamicSearch(@RequestBody SearchCriteria plans){
		List<InsurancePlans> incPlans=insuranceService.getAllPlans(plans);
		return new  ResponseEntity<>(incPlans,HttpStatus.CREATED);
	}

	@GetMapping(value = "/pdGenrator/{Incplans}", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<InputStreamResource> employeeReport(SearchCriteria Incplans) throws IOException {
		List<InsurancePlans> plans = (List<InsurancePlans>) insuranceService.getAllPlans(Incplans);
		ByteArrayInputStream bis = PDFGenerator.insurancePDFReport(plans);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=employees.pdf");

		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(bis));
	}
}
