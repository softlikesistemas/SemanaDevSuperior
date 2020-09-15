package com.devsuperiorfaw.dspesquisa.services;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperiorfaw.dspesquisa.dto.RecordDTO;
import com.devsuperiorfaw.dspesquisa.dto.RecordInsertDTO;
import com.devsuperiorfaw.dspesquisa.entities.Game;
import com.devsuperiorfaw.dspesquisa.entities.Record;
import com.devsuperiorfaw.dspesquisa.repositories.GameRepository;
import com.devsuperiorfaw.dspesquisa.repositories.RecordRepository;

@Service
public class RecordService {
	
	@Autowired
	private RecordRepository repositoty;
	
	@Autowired
	private GameRepository gameRepositoty;
	
	@Transactional
	public RecordDTO insert(RecordInsertDTO dto) {
		Record entity = new Record();
		entity.setName(dto.getName());
		entity.setAge(dto.getAge());
		entity.setMoment(Instant.now());
		
		Game game = gameRepositoty.getOne(dto.getGameId());
		entity.setGame(game);
		
		entity = repositoty.save(entity);
		return new RecordDTO(entity);
	}
	
	

}
