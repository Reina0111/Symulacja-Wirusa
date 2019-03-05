import java.util.ArrayList;
import java.util.Random;

public abstract class Agent {
    protected final int id;
    protected final ArrayList<Agent> znajomi = new ArrayList<>();
    protected StanNaKoniecDnia stan;
    public String nazwa = "Agent";

    public Agent(int id) {
        this.id = id;
        stan = StanNaKoniecDnia.ZDROWY;
    }

    public ArrayList<Spotkanie> umowSpotkanie(int dzien, int liczbaDni, double prawdSpotkania, Random generator) {
        int liczbaZnajomych = znajomi.size();
        ArrayList<Spotkanie> spotkania = new ArrayList<>();
        while (generator.nextDouble() <= prawdSpotkania) {
            spotkania.add(new Spotkanie(generator.nextInt(liczbaDni - dzien) + dzien + 1, this,
                    znajomi.get(generator.nextInt(liczbaZnajomych))));
        }
        return spotkania;
    }

    @Override
    public String toString() {
        String wynik = "" + id;
        if (stan.equals(StanNaKoniecDnia.CHORY))
            wynik += "*";
        return wynik;
    }

    public String opisGrafu() {
        String wynik = "" + id;
        for (Agent znajomy : znajomi) {
            wynik += " " + znajomy.id;
        }
        return wynik;
    }

    public ArrayList<Agent> getZnajomi() {
        return znajomi;
    }
}
