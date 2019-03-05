package dane;

import java.io.*;
import java.nio.channels.Channels;
import java.nio.charset.MalformedInputException;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class Wczytywanie {
    public Wczytywanie() {
    }

    public Dane wczytaj() {
        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String defpath = rootPath + "default.properties";
        String simpath = "simulation-conf.xml";
        System.setProperty("file.encoding", "UTF-8");

        Properties def = new Properties();
        try (FileInputStream stream = new FileInputStream(defpath);
             Reader reader = Channels.newReader(stream.getChannel(), StandardCharsets.UTF_8.name())) {
            def.load(reader);
        } catch (MalformedInputException e) {
            System.out.println("default.properties nie jest tekstowy\n");
            System.exit(1);
        } catch (IOException e) {
            System.out.println("Brak pliku default.properties\n");
            System.exit(1);
        }

        Properties sim = new Properties();
        try {
            sim.loadFromXML(new FileInputStream(simpath));
        } catch (IOException e) {
            simpath = rootPath + "simulation-conf.xml";
            try {
                sim.loadFromXML(new FileInputStream(simpath));
            } catch (IOException e1) {
                System.out.println("Brak pliku simulation-conf.xml\n");
                System.exit(1);
            }
        }
        String Sseed = def.getProperty("seed");
        String SliczbaAgentow = def.getProperty("liczbaAgentów");
        String SprawdTowarzyski = def.getProperty("prawdTowarzyski");
        String SprawdSpotkania = def.getProperty("prawdSpotkania");
        String SprawdZarazenia = def.getProperty("prawdZarażenia");
        String SprawdWyzdrowienia = def.getProperty("prawdWyzdrowienia");
        String Ssmiertelnosc = def.getProperty("śmiertelność");
        String SliczbaDni = def.getProperty("liczbaDni");
        String SsrZnajomych = def.getProperty("śrZnajomych");
        String SplikZRaportem = def.getProperty("plikZRaportem");

        if (SliczbaAgentow != null) poprawnoscDanych("liczbaAgentów", Integer.parseInt(SliczbaAgentow), 1, 1000000);
        if (SprawdTowarzyski != null) poprawnoscDanych("prawdTowarzyski", Double.parseDouble(SprawdTowarzyski));
        if (SprawdSpotkania != null) poprawnoscDanych("prawdSpotkania", Double.parseDouble(SprawdSpotkania));
        if (SprawdZarazenia != null) poprawnoscDanych("prawdZarażenia", Double.parseDouble(SprawdZarazenia));
        if (SprawdWyzdrowienia != null) poprawnoscDanych("prawdWyzdrowienia", Double.parseDouble(SprawdWyzdrowienia));
        if (Ssmiertelnosc != null) poprawnoscDanych("śmiertelność", Double.parseDouble(Ssmiertelnosc));
        if (SliczbaDni != null) poprawnoscDanych("liczbaDni", Integer.parseInt(SliczbaDni), 1, 1000);
        if (SsrZnajomych != null) poprawnoscDanych("śrZnajomych", Integer.parseInt(SsrZnajomych),
                0, Integer.parseInt(SliczbaAgentow) - 1);

        if (sim.containsKey("seed")) Sseed = sim.getProperty("seed");
        if (sim.containsKey("liczbaAgentów")) SliczbaAgentow = sim.getProperty("liczbaAgentów");
        if (sim.containsKey("prawdTowarzyski")) SprawdTowarzyski = sim.getProperty("prawdTowarzyski");
        if (sim.containsKey("prawdSpotkania")) SprawdSpotkania = sim.getProperty("prawdSpotkania");
        if (sim.containsKey("prawdZarażenia")) SprawdZarazenia = sim.getProperty("prawdZarażenia");
        if (sim.containsKey("prawdWyzdrowienia")) SprawdWyzdrowienia = sim.getProperty("prawdWyzdrowienia");
        if (sim.containsKey("śmiertelność")) Ssmiertelnosc = sim.getProperty("śmiertelność");
        if (sim.containsKey("liczbaDni")) SliczbaDni = sim.getProperty("liczbaDni");
        if (sim.containsKey("śrZnajomych")) SsrZnajomych = sim.getProperty("śrZnajomych");
        if (sim.containsKey("plikZRaportem")) SplikZRaportem = sim.getProperty("plikZRaportem");

        if (SliczbaAgentow != null) poprawnoscDanych("liczbaAgentów", Integer.parseInt(SliczbaAgentow), 1, 1000000);
        if (SprawdTowarzyski != null) poprawnoscDanych("prawdTowarzyski", Double.parseDouble(SprawdTowarzyski));
        if (SprawdSpotkania != null) poprawnoscDanych("prawdSpotkania", Double.parseDouble(SprawdSpotkania));
        if (SprawdZarazenia != null) poprawnoscDanych("prawdZarażenia", Double.parseDouble(SprawdZarazenia));
        if (SprawdWyzdrowienia != null) poprawnoscDanych("prawdZarażenia", Double.parseDouble(SprawdWyzdrowienia));
        if (Ssmiertelnosc != null) poprawnoscDanych("prawdZarażenia", Double.parseDouble(Ssmiertelnosc));
        if (SliczbaDni != null) poprawnoscDanych("prawdZarażenia", Integer.parseInt(SliczbaDni), 1, 1000);
        if (SsrZnajomych != null) poprawnoscDanych("prawdZarażenia", Integer.parseInt(SsrZnajomych),
                0, Integer.parseInt(SliczbaAgentow) - 1);

        int seed = Integer.parseInt(Sseed);
        int liczbaAgentow = Integer.parseInt(SliczbaAgentow);
        double prawdTowarzyski = Double.parseDouble(SprawdTowarzyski);
        double prawdSpotkania = Double.parseDouble(SprawdSpotkania);
        double prawdZarazenia = Double.parseDouble(SprawdZarazenia);
        double prawdWyzdrowienia = Double.parseDouble(SprawdWyzdrowienia);
        double smiertelnosc = Double.parseDouble(Ssmiertelnosc);
        int liczbaDni = Integer.parseInt(SliczbaDni);
        int srZnajomych = Integer.parseInt(SsrZnajomych);
        String plikZRaportem = SplikZRaportem;

        return new Dane(seed, liczbaAgentow, prawdTowarzyski, prawdSpotkania,
                prawdZarazenia, prawdWyzdrowienia, smiertelnosc,
                liczbaDni, srZnajomych, plikZRaportem);
    }

    private void poprawnoscDanych(String nazwaWartosci, int wartosc, int ogrDolne, int ogrGorne) {
        if (wartosc < ogrDolne || wartosc > ogrGorne) {
            System.out.println("Niedozwolona wartość " + wartosc + " dla klucza " + nazwaWartosci);
            System.exit(1);
        }
    }

    private void poprawnoscDanych(String nazwaWartosci, double wartosc) {
        if (wartosc <= (double) 0 || wartosc >= (double) 1) {
            System.out.println("Niedozwolona wartość " + wartosc + " dla klucza " + nazwaWartosci);
            System.exit(1);
        }
    }
}