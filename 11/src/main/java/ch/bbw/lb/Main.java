package ch.bbw.lb;

import ch.bbw.lb.Aufgabe.ComparatorAufgabe;
import ch.bbw.lb.DataGeneration.DataDump;
import ch.bbw.lb.DataGeneration.DataGenerator;
import ch.bbw.lb.DataGeneration.adapter.GsonAdapter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {

    public static void main(String[] args) throws IOException {
        var file = new File(DataGenerator.FINAL_FILE);
        if (!file.exists()) {
            System.out.println("🔄️ The File " + DataGenerator.FINAL_FILE + " does not exist. Generate data...");
            try {
                DataGenerator.main(args);
            } catch (Exception e) {
                System.err.println("❌ Failed to generate data: " + e.getMessage());
                return;
            }
        }

        System.out.println("📖 Reading data from " + DataGenerator.FINAL_FILE);
        var json = Files.readString(Path.of(DataGenerator.FINAL_FILE));
        var dump = GsonAdapter.createGson().fromJson(json, DataDump.class);

        if (dump == null) {
            System.err.println("❌ Failed to read data from " + DataGenerator.FINAL_FILE);
            return;
        }

        System.out.println("✔️ Data read successfully. Starting application...");

        System.out.println("--- Starting ComparatorAufgabe ---");
        ComparatorAufgabe.run(dump);
    }
}