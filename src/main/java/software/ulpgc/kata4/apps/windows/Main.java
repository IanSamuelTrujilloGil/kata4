package software.ulpgc.kata4.apps.windows;

import software.ulpgc.kata4.architecture.control.ImportCommand;
import software.ulpgc.kata4.architecture.control.ToggleGraphCommand;
import software.ulpgc.kata4.architecture.io.*;
import software.ulpgc.kata4.architecture.model.Barchart;
import software.ulpgc.kata4.architecture.model.FilmIndustryPerson;
import software.ulpgc.kata4.architecture.model.MapToBarchartBuilder;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        try (FileFilmIndustryPersonReader reader = new FileFilmIndustryPersonReader(new File("src/main/resources/name.basics.tsv"), new TsvFilmIndustryPersonDeserializer());
             DatabaseFilmIndustryPersonWriter writer = DatabaseFilmIndustryPersonWriter.open(databaseFile())
        ) {
            new ImportCommand(reader,writer).execute();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        List<FilmIndustryPerson> people;

        try (DatabaseFilmIndustryPersonReader reader = DatabaseFilmIndustryPersonReader.open(databaseFile())) {
            people = reader.readAll();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        HashMap<String, Integer> barchartLifeStatus = new HashMap<>();
        HashMap<String, Integer> barchartDeadYear = new HashMap<>();
        for (FilmIndustryPerson filmIndustryPerson : people) {

            barchartLifeStatus.put(lifeStatus(filmIndustryPerson), barchartLifeStatus.getOrDefault(lifeStatus(filmIndustryPerson),
                    0) + 1);

            if (filmIndustryPerson.deathYear() == -1 || filmIndustryPerson.birthYear() == -1 || isAgeOutOfRange(filmIndustryPerson))
                continue;

            barchartDeadYear.put(String.valueOf(calculateAge(filmIndustryPerson)), barchartDeadYear.getOrDefault(
                    String.valueOf(calculateAge(filmIndustryPerson)),0)+1);

        }

        Barchart mainBarchart = new MapToBarchartBuilder("Film Industry Life Status", "Life Status", "People Count", barchartLifeStatus).build();
        Barchart secondBarchart = new MapToBarchartBuilder("Film Industry death by age","Age at death", "People Count",
                barchartDeadYear).build();

        MainFrame mainFrame = new MainFrame();
        ToggleBarchartLoader loader = new ToggleBarchartLoader(mainBarchart, secondBarchart);
        mainFrame.put("toggle", new ToggleGraphCommand(mainFrame.getDisplay(), loader));
        mainFrame.getDisplay().show(loader.load(0));
        mainFrame.setVisible(true);

    }

    private static File databaseFile() {
        return new File("FilmIndustryPeople.db");
    }

    private static boolean isAgeOutOfRange(FilmIndustryPerson filmIndustryPerson) {
        return calculateAge(filmIndustryPerson) < 10 || calculateAge(filmIndustryPerson) > 90;
    }

    private static int calculateAge(FilmIndustryPerson filmIndustryPerson) {
        return filmIndustryPerson.deathYear() - filmIndustryPerson.birthYear();
    }

    private static String lifeStatus(FilmIndustryPerson filmIndustryPerson) {
        return filmIndustryPerson.deathYear() == -1? "Alive" : "Dead";
    }

}
