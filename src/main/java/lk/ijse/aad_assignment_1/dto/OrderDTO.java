package lk.ijse.aad_assignment_1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author Amil Srinath
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class OrderDTO {
    private String date;
    private String order_id;
    private String cusName;
    private double netTotal;
}
