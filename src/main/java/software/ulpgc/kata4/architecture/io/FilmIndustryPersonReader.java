package software.ulpgc.kata4.architecture.io;


import software.ulpgc.kata4.architecture.model.FilmIndustryPerson;

import java.io.IOException;

public interface FilmIndustryPersonReader extends AutoCloseable {
    public FilmIndustryPerson read() throws IOException;
}
