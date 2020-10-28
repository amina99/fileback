package com.upload.sip.Controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.upload.sip.Repository.DemandeRepository;
import com.upload.sip.entities.Demande;
import com.upload.sip.serivce.DemandeServices;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
//@RequestMapping("/files")

public class DemandeController {
	
	@Autowired
	DemandeServices demandeServices;
	@Autowired
	DemandeRepository demanderepository;
	
	@PostMapping(value="demandes")
	public Demande saveDemande(@RequestBody Demande demande) {
		return demandeServices.saveDemande(demande);
	}


	@GetMapping(value="/demandes1")
	public List<Demande> getDemandes() {
		return demandeServices.getDemandes();
	}
	
	
	
    @PostMapping(value="files")
    public ResponseEntity<?> uploadFile(
      @RequestParam("fid") String fid, // file id
      @RequestParam("file") MultipartFile file
    ) {
      if (file.isEmpty()) {
        throw new RuntimeException("File given is not valid!");
      }
    
      String folder =System.getProperty("user.home") + "/piecejointe/uploads/";

      try {
        Path pathFolder = Paths.get(folder);
        Files.createDirectories(pathFolder);

        Path pathFile = Paths.get(folder + fid);
        Files.write(pathFile, file.getBytes());

      } catch (IOException e) {
        throw new RuntimeException(e);
      }

      return new ResponseEntity(HttpStatus.OK);
    }

    
    @GetMapping(path="photoDemande/{id}", produces= MediaType.IMAGE_JPEG_VALUE)
     public byte[] getPhoto(@PathVariable("id") Long id) throws IOException {
      	Demande p=demanderepository.findById(id).get();
          return Files.readAllBytes(Paths.get(System.getProperty("user.home")+"/piecejointe/uploads/"+p.getPieceJointeDemande()));	
     }  
    
    @DeleteMapping("{fid}")
    public void deleteFile(@PathVariable("fid") String fid)
    {
      try {
        Path fileToDelete = Paths.get((System.getProperty("user.home")+"/piecejointe/uploads/" + fid)); 

        if (Files.exists(fileToDelete)) {
          Files.delete(fileToDelete);
        }
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    }

    
    
}
