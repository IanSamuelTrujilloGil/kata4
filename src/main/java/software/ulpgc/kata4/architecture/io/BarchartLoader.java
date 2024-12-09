package software.ulpgc.kata4.architecture.io;


import software.ulpgc.kata4.architecture.model.Barchart;

public interface BarchartLoader {
    Barchart load(int id);
}
