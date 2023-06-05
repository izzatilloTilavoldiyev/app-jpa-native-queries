package uz.pdp.appjpareletionships.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appjpareletionships.entity.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {
}
