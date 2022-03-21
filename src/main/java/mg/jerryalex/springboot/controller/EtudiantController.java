package mg.jerryalex.springboot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mg.jerryalex.springboot.exception.ResourceNotFoundException;
import mg.jerryalex.springboot.model.Etudiants;
import mg.jerryalex.springboot.repository.EtudiantRepository;

@RestController
@RequestMapping("/api/")
public class EtudiantController {

	@Autowired
	private EtudiantRepository etudiantRepository;
	
	//get students
	@GetMapping("etudiant")
	public List<Etudiants> getAllStudent(){
		return this.etudiantRepository.findAll();
	}
	
	//get student by id
	@GetMapping("etudiant/{id}")
	public ResponseEntity<Etudiants> getStudentById(@PathVariable(value = "id") Long etudiantID) throws ResourceNotFoundException{
		Etudiants etudiants = etudiantRepository.findById(etudiantID)
					.orElseThrow(() -> new ResourceNotFoundException("student not found  for id : " + etudiantID));
		return ResponseEntity.ok().body(etudiants);
	} 
	
	//save student
	@	PostMapping("etudiant")
	public Etudiants createStudent(@RequestBody Etudiants etudiants) {
		return this.etudiantRepository.save(etudiants);
	}
	
	//update student
	@PutMapping("etudiant/{id}")
	public ResponseEntity<Etudiants> updateStudent(@PathVariable(value = "id") Long etudiantID, @ Validated @RequestBody Etudiants newStudent)
			throws ResourceNotFoundException{
		Etudiants etudiants = etudiantRepository.findById(etudiantID)
				.orElseThrow(() -> new ResourceNotFoundException("student not found  for id : " + etudiantID));
		etudiants.setFirstName(newStudent.getFirstName());
		etudiants.setLastName(newStudent.getLastName());
		etudiants.setNumber(newStudent.getNumber());
		
		return ResponseEntity.ok(this.etudiantRepository.save(etudiants));
	}
	
	//delete student
	@DeleteMapping("etudiant/{id}")
	public Map<String , Boolean> deleteStudent(@PathVariable(value = "id") long etudiantID) throws ResourceNotFoundException{
		Etudiants etudiants = etudiantRepository.findById(etudiantID)
				.orElseThrow(() -> new ResourceNotFoundException("student not found  for id : " + etudiantID));
		this.etudiantRepository.delete(etudiants);
		
		Map<String, Boolean> response = new HashMap<>();
		response.put(" Deleted", Boolean.TRUE);
		return response;
	}
}
