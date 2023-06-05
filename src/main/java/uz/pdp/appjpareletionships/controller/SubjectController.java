package uz.pdp.appjpareletionships.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appjpareletionships.entity.Subject;
import uz.pdp.appjpareletionships.repository.SubjectRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class SubjectController {

    @Autowired
    SubjectRepository subjectRepository;

    @RequestMapping(value = "/subject", method = RequestMethod.GET)
    public List<Subject> getSubjects() {
        return subjectRepository.findAll();
    }

    @RequestMapping(value = "/subject", method = RequestMethod.POST)
    public String addSubject(@RequestBody Subject subject) {
        subjectRepository.save(subject);
        return "Subject added";
    }

    @RequestMapping(value = "/subject/{id}", method = RequestMethod.DELETE)
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

    @RequestMapping(value = "/subject/{id}", method = RequestMethod.PUT)
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
