package lk.ijse.aad_assignment_1.API;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.aad_assignment_1.dto.CustomerDTO;

import java.io.IOException;

/**
 * @author Amil Srinath
 */
@WebServlet( name = "customer", urlPatterns = "/customer")
public class Customer extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Jsonb jsonb = JsonbBuilder.create();
        CustomerDTO customerDTO = jsonb.fromJson(req.getReader(), CustomerDTO.class);

        System.out.println(customerDTO.getCus_ID());
        System.out.println(customerDTO.getName());
        System.out.println(customerDTO.getNic());
        System.out.println(customerDTO.getAddress());
    }
}