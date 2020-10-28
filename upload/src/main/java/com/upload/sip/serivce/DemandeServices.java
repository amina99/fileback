package com.upload.sip.serivce;

import java.util.List;

import com.upload.sip.entities.Demande;

public interface DemandeServices {
	
	public Demande saveDemande(Demande demande);
	public List<Demande>getDemandes();


}
