package ch.bbw.lho;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Locomotive> trains = Dataset.generateData();

        // Comparator abgeleitete Klasse und Anonyme Klasse
        trains.sort(new Comparator<Locomotive>() {
            @Override
            public int compare(Locomotive o1, Locomotive o2) {
                return Integer.compare(o1.getwagons().size(), o2.getwagons().size());
            }
        });

        //Lambda
        trains.sort((t1, t2) -> Long.compare(t1.getHp(), t2.getHp()));

        //Comparator Chain
        trains.sort(Comparator.comparing((Locomotive loco) -> loco.getwagons().getFirst().getLength())
                .thenComparing((Locomotive loco) -> loco.getwagons().getFirst().getCargo()));

        //Natural Order
        Collections.sort(trains);

        //Reverse Order
        trains.sort(Comparator.comparing(Locomotive::getColor).reversed());
    }
}

class Locomotive implements Comparable<Locomotive> {

    public Locomotive(String m_name, Long m_number, Long m_hp, String m_color, List<Wagon> m_wagons) {
        this.m_name = m_name;
        this.m_number = m_number;
        this.m_hp = m_hp;
        this.m_color = m_color;
        this.m_wagons = m_wagons;
    }

    // 5 Attributen, 4 versch. Datentypen, 2 kein primitives/Wrapperclass
    private String m_name;
    private Long m_number;
    private Long m_hp;
    private String m_color;
    private List<Wagon> m_wagons;

    public String getName() {
        return m_name;
    }

    public Long getNumber() {
        return m_number;
    }

    public Long getHp() {
        return m_hp;
    }

    public String getColor() {
        return m_color;
    }

    public List<Wagon> getwagons() {
        return m_wagons;
    }

    @Override
    public int compareTo(Locomotive o) {
        return this.m_number.compareTo(o.m_number);
    }
}

class Wagon {

    public Wagon(String m_cargo, Long m_length) {
        this.m_cargo = m_cargo;
        this.m_length = m_length;
    }

    public String getCargo() {
        return m_cargo;
    }

    public Long getLength() {
        return m_length;
    }

    private String m_cargo;
    private Long m_length;
}

