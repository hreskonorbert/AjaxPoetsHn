package com.codecool.web.servlet;

import com.codecool.web.dao.UserDao;
import com.codecool.web.dao.WorkDao;
import com.codecool.web.dao.database.DatabaseUserDao;
import com.codecool.web.dao.database.DatabaseWorkDao;
import com.codecool.web.dto.UserDto;
import com.codecool.web.model.User;
import com.codecool.web.model.Work;
import com.codecool.web.service.LoginService;
import com.codecool.web.service.exception.ServiceException;
import com.codecool.web.service.simple.SimpleLoginService;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/login")
public final class LoginServlet extends AbstractServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try (Connection connection = getConnection(req.getServletContext())) {
            ObjectMapper objectMapper = new ObjectMapper();
            UserDao userDao = new DatabaseUserDao(connection);
            WorkDao workDao = new DatabaseWorkDao(connection);
            LoginService loginService = new SimpleLoginService(userDao);

            List<Work> works = workDao.getWorksByPoet(req.getParameter("email"));
            String email = req.getParameter("email");
            String password = req.getParameter("password");




            User user = loginService.loginUser(email, password);
            req.getSession().setAttribute("name", email);
            req.getSession().setAttribute("user", user);
            req.getSession().setAttribute("works", works);
            resp.setContentType("application/json");
            sendMessage(resp, HttpServletResponse.SC_OK, new UserDto(user,works));
        } catch (ServiceException ex) {
            sendMessage(resp, HttpServletResponse.SC_UNAUTHORIZED, ex.getMessage());
        } catch (SQLException ex) {
            handleSqlError(resp, ex);
        }
    }
}
