package lk.ijse.aad_assignment_1.API;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.aad_assignment_1.db.CustomerDBProcess;
import lk.ijse.aad_assignment_1.db.ItemDBProcess;
import lk.ijse.aad_assignment_1.dto.CustomerDTO;
import lk.ijse.aad_assignment_1.dto.ItemDTO;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Amil Srinath
 */
@WebServlet(name = "item", urlPatterns = "/item")
public class Item extends HttpServlet {
    Connection connection;
    @Override
    public void init() throws ServletException {
        try {
            InitialContext ctx = new InitialContext();
            DataSource pool = (DataSource) ctx.lookup("java:comp/env/jdbc/pos");
            this.connection = pool.getConnection();
        } catch (SQLException | NamingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ItemDTO> allItem = new ItemDBProcess().getAllItem(connection);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonResult = objectMapper.writeValueAsString(allItem);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(jsonResult);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Jsonb jsonb = JsonbBuilder.create();
        ItemDTO itemDTO = jsonb.fromJson(req.getReader(), ItemDTO.class);

        ItemDBProcess itemDBProcess = new ItemDBProcess();

        if(itemDBProcess.saveItem(itemDTO, connection)){
            PrintWriter writer = resp.getWriter();
            writer.write("Item Saved !");
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Jsonb jsonb = JsonbBuilder.create();
        ItemDTO itemDTO = jsonb.fromJson(req.getReader(), ItemDTO.class);

        ItemDBProcess itemDBProcess = new ItemDBProcess();

        if(itemDBProcess.updateItem(itemDTO, connection)){
            PrintWriter writer = resp.getWriter();
            writer.write("Customer Updated !");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(new ItemDBProcess().deleteItem(req.getParameter("item_id"),connection)){
            resp.getWriter().write("Item Deleted!");
        }
    }
}
