package mg.jerryalex.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mg.jerryalex.springboot.model.Etudiants;

@Repository
public interface EtudiantRepository extends JpaRepository<Etudiants, Long>{

}
