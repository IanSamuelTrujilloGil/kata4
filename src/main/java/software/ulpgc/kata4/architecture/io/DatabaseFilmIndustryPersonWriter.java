package software.ulpgc.kata4.architecture.io;

import software.ulpgc.kata4.architecture.model.FilmIndustryPerson;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseFilmIndustryPersonWriter implements FilmIndustryPersonWriter{

    private final Connection connection;
    private final PreparedStatement insertFilmIndustryPersonPreparedStatement;
    private static final String InsertFilmIndustryPersonStatement= """
            INSERT INTO FilmIndustryPeople(id,primaryName,birthYear,deathYear)
            VALUES(?,?,?,?)""";
    private static final String CreateFilmIndustryPeopleTableStatement= """
            CREATE TABLE IF NOT EXISTS FilmIndustryPeople(
            id TEXT PRIMARY KEY,
            primaryName TEXT NOT NULL,
            birthYear INTEGER,
            deathYear INTEGER)""";

    public DatabaseFilmIndustryPersonWriter(String connection) throws SQLException {
        this(DriverManager.getConnection(connection));
    }


    public static DatabaseFilmIndustryPersonWriter open(File file) throws SQLException {
        return new DatabaseFilmIndustryPersonWriter("jdbc:sqlite:" + file.getAbsolutePath());
    }

    public DatabaseFilmIndustryPersonWriter(Connection connection) throws SQLException {
        this.connection = connection;
        stopAutoCommit();
        createTable();
        this.insertFilmIndustryPersonPreparedStatement = getInsertFilmIndustryPersonPreparedStatement();
    }

    private PreparedStatement getInsertFilmIndustryPersonPreparedStatement() throws SQLException {
        return this.connection.prepareStatement(InsertFilmIndustryPersonStatement);
    }

    private void createTable() throws SQLException {
        this.connection.createStatement().execute(CreateFilmIndustryPeopleTableStatement);
    }

    private void stopAutoCommit() throws SQLException {
        this.connection.setAutoCommit(false);
    }

    @Override
    public void write(FilmIndustryPerson person) throws IOException {
        try {
            insertFilmIndustryPersonPreparedStatementFor(person).execute();
        } catch (SQLException e) {
            throw new IOException(e);
        }
    }

    private PreparedStatement insertFilmIndustryPersonPreparedStatementFor(FilmIndustryPerson person) throws SQLException {
        insertFilmIndustryPersonPreparedStatement.clearParameters();
        insertFilmIndustryPersonPreparedStatement.setString(1, person.id());
        insertFilmIndustryPersonPreparedStatement.setString(2, person.primaryName());
        insertFilmIndustryPersonPreparedStatement.setInt(3, person.birthYear());
        insertFilmIndustryPersonPreparedStatement.setInt(4, person.deathYear());
        return insertFilmIndustryPersonPreparedStatement;
    }

    @Override
    public void close() throws Exception {
        this.connection.commit();
        this.connection.close();
    }
}
