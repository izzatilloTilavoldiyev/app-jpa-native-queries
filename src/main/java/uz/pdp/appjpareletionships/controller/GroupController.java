package uz.pdp.appjpareletionships.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appjpareletionships.entity.Faculty;
import uz.pdp.appjpareletionships.entity.Group;
import uz.pdp.appjpareletionships.payLoad.GroupDTO;
import uz.pdp.appjpareletionships.repository.FacultyRepository;
import uz.pdp.appjpareletionships.repository.GroupRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/group")
public class GroupController {

    @Autowired
    GroupRepository groupRepository;
    @Autowired
    FacultyRepository facultyRepository;

    @GetMapping
    public List<Group> getGroups() {
        return groupRepository.findAll();
    }

    @GetMapping("/byUniversityId/{universityId}")
    public List<Group> getGroupsByUniversityId(@PathVariable Integer universityId) {
        return groupRepository.findAllByFaculty_UniversityId(universityId);
    }

    @GetMapping(value = "/byFacultyId/{facultyId}")
    public List<Group> getGroupByFacultyId(@PathVariable Integer facultyId) {
        return groupRepository.findAllByFacultyId(facultyId);
    }

    @PostMapping
    public String addGroup(@RequestBody GroupDTO groupDTO) {
        Group group = new Group();
        group.setName(groupDTO.getName());

        Optional<Faculty> optionalFaculty = facultyRepository.findById(groupDTO.getFaculty_id());
        if (optionalFaculty.isEmpty())
            return "Such faculty not found";
        group.setFaculty(optionalFaculty.get());
        groupRepository.save(group);
        return "Group added";
    }

    @DeleteMapping("/{id}")
    public String deleteGroup(@PathVariable Integer id) {
        Optional<Group> optionalGroup = groupRepository.findById(id);
        if (optionalGroup.isEmpty())
            return "Group not found";
        groupRepository.deleteById(id);
        return "Group deleted";
    }

    @PutMapping("/{id}")
    public String editeGroup(@PathVariable Integer id, @RequestBody GroupDTO groupDTO) {
        Optional<Group> optionalGroup = groupRepository.findById(id);
        if (optionalGroup.isEmpty())
            return "Group not found";
        Group group = optionalGroup.get();
        group.setName(groupDTO.getName());
        Optional<Faculty> optionalFaculty = facultyRepository.findById(groupDTO.getFaculty_id());
        if (optionalFaculty.isEmpty())
            return "Faculty not found";
        group.setFaculty(optionalFaculty.get());
        groupRepository.save(group);
        return "Group edited";
    }
}

