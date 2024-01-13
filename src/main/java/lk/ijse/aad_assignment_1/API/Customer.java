package lk.ijse.aad_assignment_1.API;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.aad_assignment_1.db.CustomerDBProcess;
import lk.ijse.aad_assignment_1.dto.CustomerDTO;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Amil Srinath
 */
@WebServlet( name = "customer", urlPatterns = "/customer")
public class Customer extends HttpServlet {
    Connection connection;
    @Override
    public void init() throws ServletException {
        try {
            InitialContext ctx = new InitialContext();
            DataSource pool = (DataSource) ctx.lookup("java:comp/env/jdbc/pos");
            System.out.println(pool);
            this.connection = pool.getConnection();
        } catch (SQLException | NamingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Jsonb jsonb = JsonbBuilder.create();
        CustomerDTO customerDTO = jsonb.fromJson(req.getReader(), CustomerDTO.class);

        System.out.println(customerDTO.getCus_id());
        System.out.println(customerDTO.getName());
        System.out.println(customerDTO.getNic());
        System.out.println(customerDTO.getAddress());

        CustomerDBProcess customerDBProcess = new CustomerDBProcess();
        customerDBProcess.saveCustomer(customerDTO,connection);

    }
}