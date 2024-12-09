package software.ulpgc.kata4.architecture.io;

import software.ulpgc.kata4.architecture.model.FilmIndustryPerson;

import java.io.IOException;

public interface FilmIndustryPersonWriter extends AutoCloseable{
    void write(FilmIndustryPerson person) throws IOException;
}
