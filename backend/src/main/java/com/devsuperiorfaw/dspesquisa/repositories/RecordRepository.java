//camada de acesso a dados

package com.devsuperiorfaw.dspesquisa.repositories;

import java.time.Instant;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.devsuperiorfaw.dspesquisa.entities.Record;

@Repository
public interface RecordRepository extends JpaRepository<Record, Long>{
	
	//PARA USAR NO POSTGRES TEM QUE COLCOAR O COALESCE.

	@Query("SELECT obj FROM Record obj WHERE "
			+ "(coalesce(:min, null) IS NULL OR obj.moment >= :min) AND "
			+ "(coalesce(:max, null) IS NULL OR obj.moment <= :max)")
	Page<Record> findByMoments(Instant min, Instant max, Pageable pageable);
	

}
