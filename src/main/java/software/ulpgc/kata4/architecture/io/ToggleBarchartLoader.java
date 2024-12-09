package software.ulpgc.kata4.architecture.io;


import software.ulpgc.kata4.architecture.model.Barchart;

public class ToggleBarchartLoader implements BarchartLoader{
    private final Barchart mainBarchart;
    private final Barchart secondBarchart;

    public ToggleBarchartLoader(Barchart mainBarchart, Barchart secondBarchart) {
        this.mainBarchart = mainBarchart;
        this.secondBarchart = secondBarchart;
    }

    @Override
    public Barchart load(int id) {
        return switch (id){
            case 0 -> mainBarchart;
            case 1 -> secondBarchart;
            default -> null;
        };
    }
}


