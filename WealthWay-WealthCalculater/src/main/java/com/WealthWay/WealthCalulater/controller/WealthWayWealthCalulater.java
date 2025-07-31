package com.WealthWay.WealthCalulater.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.WealthWay.WealthCalulater.model.LumsumDto;
import com.WealthWay.WealthCalulater.model.SipDto;
import com.WealthWay.WealthCalulater.model.WealthProjectedDto;
import com.WealthWay.WealthCalulater.services.WealthWayCalulater;
@RestController()
@RequestMapping("app/wealthcalulater")
public class WealthWayWealthCalulater {
	
	@Autowired
	WealthWayCalulater wealthWaycaluclater;
	
	
	@PostMapping("/lumpsum")
	public ResponseEntity<WealthProjectedDto> lumpsumWeatlh(@RequestBody LumsumDto dto) {
		
		
		return new ResponseEntity<>(wealthWaycaluclater.lumpsumWeatlh(dto),HttpStatus.OK);
		
	}
	
	@PostMapping(value ="/sip")
   public ResponseEntity<WealthProjectedDto> sipWealth(@RequestBody SipDto dto) {
	
	return new ResponseEntity<>(wealthWaycaluclater.SipWealth(dto),HttpStatus.OK);
	
		
	}

}
