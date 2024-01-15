package lk.ijse.aad_assignment_1.db;

import lk.ijse.aad_assignment_1.dto.CustomerDTO;
import lk.ijse.aad_assignment_1.dto.ItemDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Amil Srinath
 */
public class ItemDBProcess {
    final static Logger logger = LoggerFactory.getLogger(ItemDBProcess.class);
    public boolean saveItem(ItemDTO itemDTO, Connection connection){
        try {
            var ps = connection.prepareStatement("INSERT INTO item(item_id,item_name,quantity,price,description) VALUES (?,?,?,?,?)");
            ps.setString(1, itemDTO.getItem_id());
            ps.setString(2, itemDTO.getItem_name());
            ps.setInt(3, itemDTO.getQuantity());
            ps.setDouble(4, itemDTO.getPrice());
            ps.setString(5, itemDTO.getDescription());

            if (ps.executeUpdate() != 0) {
                logger.info("Data saved");
                return true;
            } else {
                logger.error("Failed to save");
                return false;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ItemDTO> getAllItem(Connection connection) {
        List<ItemDTO> itemDTOS = new ArrayList<>();
        try {
            ResultSet resultSet = connection.prepareStatement("select * from item").executeQuery();
            while(resultSet.next()) {
                ItemDTO itemDTO = new ItemDTO(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getInt(3),
                        resultSet.getDouble(4),
                        resultSet.getString(5)
                );
                itemDTOS.add(itemDTO);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return itemDTOS;
    }

    public boolean updateItem(ItemDTO itemDTO, Connection connection) {
        try {
            var ps = connection.prepareStatement("UPDATE item set item_name=?, quantity=?, price=?, description=? where item_id=?");
            ps.setString(1, itemDTO.getItem_name());
            ps.setInt(2, itemDTO.getQuantity());
            ps.setDouble(3, itemDTO.getPrice());
            ps.setString(4, itemDTO.getDescription());
            ps.setString(5, itemDTO.getItem_id());

            if (ps.executeUpdate() != 0) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deleteItem(String itemId, Connection connection) {
        try {
            var ps = connection.prepareStatement("DELETE from item where item_id=?");
            ps.setString(1, itemId);

            if (ps.executeUpdate() != 0) {
                return true;
            } else {
                return false;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
