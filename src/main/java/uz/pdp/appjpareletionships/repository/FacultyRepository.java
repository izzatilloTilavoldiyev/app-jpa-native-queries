package uz.pdp.appjpareletionships.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appjpareletionships.entity.Faculty;

public interface FacultyRepository extends JpaRepository<Faculty, Integer> {
    boolean existsByNameAndUniversityId(String name, Integer university_id);
}
