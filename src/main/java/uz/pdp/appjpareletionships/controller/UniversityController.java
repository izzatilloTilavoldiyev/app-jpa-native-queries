package uz.pdp.appjpareletionships.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appjpareletionships.entity.Address;
import uz.pdp.appjpareletionships.entity.University;
import uz.pdp.appjpareletionships.payLoad.UniversityDTO;
import uz.pdp.appjpareletionships.repository.AddressRepository;
import uz.pdp.appjpareletionships.repository.UniversityRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class UniversityController {

    @Autowired
    UniversityRepository universityRepository;

    @Autowired
    AddressRepository addressRepository;

    //READ
    @RequestMapping(value = "/university", method = RequestMethod.GET)
    public List<University> getUniversities() {
        return universityRepository.findAll();
    }

    //CREATE
    @RequestMapping(value = "/university", method = RequestMethod.POST)
    public String addUniversity(@RequestBody UniversityDTO universityDTO) {
        Address address = new Address();
        address.setCity(universityDTO.getCity());
        address.setDistrict(universityDTO.getDistrict());
        address.setStreet(universityDTO.getStreet());
        Address savedAddress = addressRepository.save(address);

        University university = new University();
        university.setName(universityDTO.getName());
        university.setAddress(savedAddress);

        universityRepository.save(university);

        return "University added";
    }

    @RequestMapping(value = "/university/{id}", method = RequestMethod.DELETE)
    public String deleteUniversity(@PathVariable Integer id) {
        List<University> universities = universityRepository.findAll();
        for (University university : universities) {
            if (university.getId() == id) {
                universityRepository.deleteById(id);
                return "University deleted";
            }
        }
        return "University not found";
    }

    @RequestMapping(value = "/university/{id}", method = RequestMethod.PUT)
    public String updateUniversity(@PathVariable Integer id, @RequestBody UniversityDTO universityDTO) {
        Optional<University> optionalUniversity = universityRepository.findById(id);
        if (optionalUniversity.isPresent()) {
            University university = optionalUniversity.get();

            Address address = university.getAddress();
            address.setCity(universityDTO.getCity());
            address.setDistrict(universityDTO.getDistrict());
            address.setStreet(universityDTO.getStreet());
            addressRepository.save(address);
            university.setName(universityDTO.getName());
            universityRepository.save(university);
            return "University edited";
        }else {
            return "University not found";
        }
    }
}
