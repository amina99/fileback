package com.upload.sip.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.upload.sip.entities.Demande;


@Repository
public interface DemandeRepository extends JpaRepository<Demande, Long>{

}
