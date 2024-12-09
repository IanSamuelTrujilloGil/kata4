package software.ulpgc.kata4.apps.windows;

import software.ulpgc.kata4.architecture.control.ToggleGraphCommand;
import software.ulpgc.kata4.architecture.io.ToggleBarchartLoader;
import software.ulpgc.kata4.architecture.model.Barchart;
import software.ulpgc.kata4.architecture.model.FilmIndustryPerson;
import software.ulpgc.kata4.architecture.model.MapToBarchartBuilder;

import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) {


        List<FilmIndustryPerson> people;


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
