package com.devsuperiorfaw.dspesquisa.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperiorfaw.dspesquisa.dto.GameDTO;
import com.devsuperiorfaw.dspesquisa.entities.Game;
import com.devsuperiorfaw.dspesquisa.repositories.GameRepository;

@Service
public class GameService {
	
	@Autowired
	private GameRepository repositoty;
	
	@Transactional(readOnly = true)
	public List<GameDTO> findAll(){
		List<Game> list	 = repositoty.findAll();
		return list.stream().map(x -> new GameDTO(x)).collect(Collectors.toList());
	}
	

}
