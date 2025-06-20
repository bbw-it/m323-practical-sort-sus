package ch.bbw.lho;

import java.util.*;

public class Dataset {

    public static List<Locomotive> generateData() {
        // 100 Datensätze
        int count = 100;
        List<Locomotive> locomotives = new ArrayList<>();
        Random random = new Random();
        String[] colors = {"Red", "Blue", "Green", "Black", "Yellow"};
        String[] cargos = {"Coal", "Grain", "Oil", "Containers", "Lumber"};

        for (int i = 0; i < count; i++) {
            String name = "Locomotive-" + i;
            long number = i;
            long hp = 1000 + random.nextInt(3000);
            String color = colors[random.nextInt(colors.length)];

            List<Wagon> wagons = new ArrayList<>();
            int wagonCount = 1 + random.nextInt(10);
            for (int j = 0; j < wagonCount; j++) {
                String cargo = cargos[random.nextInt(cargos.length)];
                long length = 10 + random.nextInt(20);
                wagons.add(new Wagon(cargo, length));
            }

            locomotives.add(new Locomotive(name, number, hp, color, wagons));
        }

        return locomotives;
    }
}

