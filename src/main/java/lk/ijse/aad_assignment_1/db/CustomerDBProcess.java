package lk.ijse.aad_assignment_1.db;

import lk.ijse.aad_assignment_1.dto.CustomerDTO;
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
public class CustomerDBProcess {
    final static Logger logger = LoggerFactory.getLogger(CustomerDBProcess.class);
    public boolean saveCustomer(CustomerDTO customerDTO, Connection connection){
        try {
            var ps = connection.prepareStatement("INSERT INTO customer(cus_ID,name,nic,address) VALUES (?,?,?,?)");
            ps.setString(1, customerDTO.getCus_id());
            ps.setString(2, customerDTO.getName());
            ps.setString(3, customerDTO.getNic());
            ps.setString(4, customerDTO.getAddress());

            if (ps.executeUpdate() != 0) {
                logger.info("Data saved");
                System.out.println("Data saved");
                return true;
            } else {
                logger.error("Failed to save");
                System.out.println("Failed to save");
                return false;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<CustomerDTO> getAllCustomer(Connection connection) {
        List<CustomerDTO> customerDTOS = new ArrayList<>();
        try {
            ResultSet resultSet = connection.prepareStatement("select * from customer").executeQuery();
            while(resultSet.next()) {
                CustomerDTO customerDTO = new CustomerDTO(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4)
                );
                customerDTOS.add(customerDTO);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return customerDTOS;
    }

    public boolean deleteCustomer(String cusId, Connection connection) {
        try {
            var ps = connection.prepareStatement("DELETE from customer where cus_id=?");
            ps.setString(1, cusId);

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
