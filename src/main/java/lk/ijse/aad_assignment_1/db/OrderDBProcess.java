package lk.ijse.aad_assignment_1.db;

import lk.ijse.aad_assignment_1.dto.ItemDTO;
import lk.ijse.aad_assignment_1.dto.OrderDTO;
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
public class OrderDBProcess {
    final static Logger logger = LoggerFactory.getLogger(OrderDBProcess.class);
    public List<OrderDTO> getAllItem(Connection connection) {
        List<OrderDTO> orderDTOS = new ArrayList<>();
        try {
            ResultSet resultSet = connection.prepareStatement("select * from orders").executeQuery();
            while(resultSet.next()) {
                OrderDTO orderDTO = new OrderDTO(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getDouble(4)
                );
                orderDTOS.add(orderDTO);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return orderDTOS;
    }

    public boolean saveOrder(OrderDTO orderDTO, Connection connection) {
        try {
            var ps = connection.prepareStatement("INSERT INTO orders(date,order_id,customer_id,net_total) VALUES (?,?,?,?)");

            ps.setString(1, orderDTO.getDate());
            ps.setString(2, orderDTO.getOrder_id());
            ps.setString(3, orderDTO.getCusName());
            ps.setDouble(4, orderDTO.getNetTotal());

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
}
