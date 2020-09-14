//camada de acesso a dados

package com.devsuperiorfaw.dspesquisa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperiorfaw.dspesquisa.entities.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long>{

}
