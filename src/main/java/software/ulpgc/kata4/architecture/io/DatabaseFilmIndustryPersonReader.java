package software.ulpgc.kata4.architecture.io;

import software.ulpgc.kata4.architecture.model.FilmIndustryPerson;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseFilmIndustryPersonReader implements FilmIndustryPersonReader{

    private final Connection connection;
    private final ResultSet resultSet;
    private static  final String ReadFilmIndustryPeopleTableStatement= """
            SELECT *
            FROM FilmIndustryPeople""";

    public DatabaseFilmIndustryPersonReader(String connection) throws SQLException {
        this(DriverManager.getConnection(connection));
    }


    public static DatabaseFilmIndustryPersonReader open(File file) throws SQLException {
        return new DatabaseFilmIndustryPersonReader("jdbc:sqlite:" + file.getAbsolutePath());
    }

    public DatabaseFilmIndustryPersonReader(Connection connection) throws SQLException {
        this.connection = connection;
        this.resultSet = getResultSet();
    }

    private ResultSet getResultSet() throws SQLException {
        return this.connection.createStatement().executeQuery(ReadFilmIndustryPeopleTableStatement);
    }

    @Override
    public FilmIndustryPerson read() throws IOException {
        try {
            if(resultSet!= null && resultSet.next()){
                return DatabaseToFilmIndustryPersonAdapter.adapt(resultSet);
            }else  return  null;
        } catch (SQLException e) {
            throw new IOException(e);
        }
    }

    public List<FilmIndustryPerson> readAll() throws IOException {
        List<FilmIndustryPerson> people = new ArrayList<>();
        while(true){
            FilmIndustryPerson person = read();
            if(person==null) break;
            people.add(person);
        }
        return people;
    }

    @Override
    public void close() throws Exception {
        this.connection.close();
    }
}
