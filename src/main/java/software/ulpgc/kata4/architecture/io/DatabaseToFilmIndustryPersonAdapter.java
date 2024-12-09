package software.ulpgc.kata4.architecture.io;

import software.ulpgc.kata4.architecture.model.FilmIndustryPerson;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseToFilmIndustryPersonAdapter {
    public static FilmIndustryPerson adapt(ResultSet resultSet) throws SQLException {
        return new FilmIndustryPerson(resultSet.getString("id"),
                resultSet.getString("primaryName"),
                resultSet.getInt("birthYear"),
                resultSet.getInt("deathYear"));
    }
}
