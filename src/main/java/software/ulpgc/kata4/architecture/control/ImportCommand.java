package software.ulpgc.kata4.architecture.control;

import software.ulpgc.kata4.architecture.io.FilmIndustryPersonReader;
import software.ulpgc.kata4.architecture.io.FilmIndustryPersonWriter;
import software.ulpgc.kata4.architecture.model.FilmIndustryPerson;

import java.io.IOException;

public class ImportCommand implements Command{

    private final FilmIndustryPersonReader reader;
    private final FilmIndustryPersonWriter writer;

    public ImportCommand(FilmIndustryPersonReader reader, FilmIndustryPersonWriter writer) {
        this.reader = reader;
        this.writer = writer;
    }

    @Override
    public void execute() {
        while(true){
            try {
                FilmIndustryPerson person = reader.read();
                if(person==null) break;
                writer.write(person);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
