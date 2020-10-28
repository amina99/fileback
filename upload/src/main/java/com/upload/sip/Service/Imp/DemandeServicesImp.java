package com.upload.sip.Service.Imp;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upload.sip.Repository.DemandeRepository;
import com.upload.sip.entities.Demande;
import com.upload.sip.serivce.DemandeServices;


@Service
public class DemandeServicesImp implements DemandeServices {

	@Autowired
	DemandeRepository demandeRepository;
	
	
	@Override
	public Demande saveDemande(Demande demande) {
		demande.setDate(new Date());
		return demandeRepository.save(demande);
	}


	@Override
	public List<Demande> getDemandes() {
		return demandeRepository.findAll();
	}



	
	
}
