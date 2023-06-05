package uz.pdp.appjpareletionships.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appjpareletionships.entity.Subject;
import uz.pdp.appjpareletionships.repository.SubjectRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/subject")
public class SubjectController {

    @Autowired
    SubjectRepository subjectRepository;

    @GetMapping
    public List<Subject> getSubjects() {
        return subjectRepository.findAll();
    }

    @PostMapping
    public String addSubject(@RequestBody Subject subject) {
        if (subjectRepository.existsByName(subject.getName()))
            return "This subject already exists";
        subjectRepository.save(subject);
        return "Subject added";
    }

    @DeleteMapping("/{id}")
    public String deleteSubject(@PathVariable Integer id) {
        Optional<Subject> optionalSubject = subjectRepository.findById(id);
        if (optionalSubject.isPresent()) {
            Subject subject = optionalSubject.get();
            subjectRepository.delete(subject);
            return "Subject deleted";
        }else {
            return "Subject not found";
        }
    }

    @PutMapping("/{id}")
    public String editeSubject(@PathVariable Integer id, @RequestBody Subject subject) {
        Optional<Subject> optionalSubject = subjectRepository.findById(id);
        if (optionalSubject.isPresent()) {
            Subject editingSubject = optionalSubject.get();
            editingSubject.setName(subject.getName());
            subjectRepository.save(editingSubject);
            return "Subject updated";
        }else {
            return "Subject not found";
        }
    }
}
