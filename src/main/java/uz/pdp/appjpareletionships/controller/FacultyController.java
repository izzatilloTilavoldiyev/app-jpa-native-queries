package uz.pdp.appjpareletionships.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appjpareletionships.entity.Faculty;
import uz.pdp.appjpareletionships.entity.University;
import uz.pdp.appjpareletionships.payLoad.FacultyDTO;
import uz.pdp.appjpareletionships.repository.FacultyRepository;
import uz.pdp.appjpareletionships.repository.UniversityRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/faculty")
public class FacultyController {

    @Autowired
    FacultyRepository facultyRepository;
    @Autowired
    UniversityRepository universityRepository;

    @PostMapping
    public String addFaculty(@RequestBody FacultyDTO facultyDTO) {
        boolean result = facultyRepository.existsByNameAndUniversityId(facultyDTO.getName(), facultyDTO.getUniversity_id());
        if (result)
            return "This university such faculty exist";
        Faculty faculty = new Faculty();
        faculty.setName(facultyDTO.getName());
        Optional<University> optionalUniversity = universityRepository.findById(facultyDTO.getUniversity_id());
        if (optionalUniversity.isEmpty())
            return "University not found";
        faculty.setUniversity(optionalUniversity.get());
        facultyRepository.save(faculty);
        return "Faculty saved";
    }

    @GetMapping
    public List<Faculty> getFaculties() {
        return facultyRepository.findAll();
    }

    @GetMapping("/byUniversityId/{universityId}")
    public List<Faculty> getFacultiesByUniversityId(@PathVariable Integer universityId) {
        return facultyRepository.findAllByUniversityId(universityId);
    }

    @DeleteMapping("/{facultyId}")
    public String deleteFaculty(@PathVariable Integer facultyId) {
        try {
            facultyRepository.deleteById(facultyId);
            return "Faculty deleted";
        } catch (Exception e) {
            return "Error in deleting";
        }
    }

    @PutMapping("/{id}")
    public String editeFaculty(@PathVariable Integer id, @RequestBody FacultyDTO facultyDTO) {
        Optional<Faculty> optionalFaculty = facultyRepository.findById(id);
        if (optionalFaculty.isPresent()) {
            Faculty faculty = optionalFaculty.get();
            faculty.setName(facultyDTO.getName());
            Optional<University> optionalUniversity = universityRepository.findById(facultyDTO.getUniversity_id());
            if (optionalUniversity.isEmpty())
                return "University not found";
            faculty.setUniversity(optionalUniversity.get());
            facultyRepository.save(faculty);
            return "Faculty edited";
        }
        return "Faculty not found";
    }
}
