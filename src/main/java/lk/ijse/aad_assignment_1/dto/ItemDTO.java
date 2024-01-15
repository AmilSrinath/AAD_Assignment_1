package lk.ijse.aad_assignment_1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author Amil Srinath
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class ItemDTO {
    private String item_id;
    private String item_name;
    private int quantity;
    private double price;
    private String description;
}
