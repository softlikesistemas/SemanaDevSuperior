package com.devsuperiorfaw.dspesquisa.resources;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperiorfaw.dspesquisa.dto.RecordDTO;
import com.devsuperiorfaw.dspesquisa.dto.RecordInsertDTO;
import com.devsuperiorfaw.dspesquisa.services.RecordService;

@RestController
@RequestMapping(value = "/records")
public class RecordResource {
	
	@Autowired
	private RecordService service;
	
	@PostMapping
	public ResponseEntity<RecordDTO> insert(@RequestBody RecordInsertDTO dto){
		RecordDTO newDTO = service.insert(dto);
		return ResponseEntity.ok().body(newDTO);
	}
	
	@GetMapping
	public ResponseEntity<Page<RecordDTO>> findAll(	
			@RequestParam(value = "min", defaultValue = "") String min,
			@RequestParam(value = "max", defaultValue = "") String max,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "0") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "moment") String orderBy,
			@RequestParam(value = "direction", defaultValue = "DESC") String direction){
		
		//trata lógica de paginação
		Instant minDate = ("".equals(min)) ? null : Instant.parse(min);
		Instant maxDate = ("".equals(max)) ? null : Instant.parse(max);
		
		if(linesPerPage == 0 ) {
			linesPerPage = Integer.MAX_VALUE;
		}
		
		PageRequest pageResquest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy); 
		
		
		Page<RecordDTO> list = service.findByMoments(minDate, maxDate, pageResquest);
		return ResponseEntity.ok().body(list);
	}
}
