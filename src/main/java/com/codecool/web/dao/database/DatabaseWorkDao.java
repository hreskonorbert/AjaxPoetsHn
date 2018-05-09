package com.codecool.web.dao.database;

import com.codecool.web.dao.WorkDao;
import com.codecool.web.model.Work;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseWorkDao extends AbstractDao implements WorkDao {

    public DatabaseWorkDao(Connection connection) {
        super(connection);
    }

    @Override
    public List<Work> getWorksByPoet(String name) throws SQLException {
        List<Work> resultList = new ArrayList<>();
        String sql = "SELECT id, poet_id, title, content, written FROM works WHERE poet_id = (SELECT id FROM poets WHERE name = ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    resultList.add(getWork(resultSet));


                }
            }
            return resultList;
        }
    }



    private Work getWork(ResultSet resultSet) throws SQLException{
        int id = resultSet.getInt("id");
        int poetId = resultSet.getInt("poet_id");
        String title = resultSet.getString("title");
        String content = resultSet.getString("content");
        String written = resultSet.getString("written");
        return new Work(id,poetId,title,content,written);
    }
}
