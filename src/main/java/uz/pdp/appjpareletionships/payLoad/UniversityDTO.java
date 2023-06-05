package uz.pdp.appjpareletionships.payLoad;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UniversityDTO {
    private String name;
    private String city;
    private String district;
    private String street;
}
