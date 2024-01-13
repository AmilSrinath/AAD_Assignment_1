package lk.ijse.aad_assignment_1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Amil Srinath
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CustomerDTO {
    private String Cus_ID;
    private String name;
    private String nic;
    private String address;
}
