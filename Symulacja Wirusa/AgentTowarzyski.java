import java.util.ArrayList;
import java.util.Random;

public class AgentTowarzyski extends Agent {
    public ArrayList<ZnajomiZnajomych> znajomiZnajomych = new ArrayList<>();

    public AgentTowarzyski(int id) {
        super(id);
        nazwa = "towarzyski";
    }

    @Override
    public ArrayList<Spotkanie> umowSpotkanie(int dzien, int liczbaDni, double prawdSpotkania, Random generator) {
        if (!stan.equals(StanNaKoniecDnia.MARTWY)) {
            if (stan.equals(StanNaKoniecDnia.CHORY)) {
                return super.umowSpotkanie(dzien, liczbaDni, prawdSpotkania, generator);
            } else {
                int liczbaZnajomych = znajomi.size();
                int liczbaZnajomychZnajomych = 0;
                for (ZnajomiZnajomych znajomi : znajomiZnajomych) {
                    liczbaZnajomychZnajomych += znajomi.liczbaZnajomych;
                }
                ArrayList<Spotkanie> spotkania = new ArrayList<>();
                while (generator.nextDouble() <= prawdSpotkania) {
                    int ktoryZnajomy = generator.nextInt(liczbaZnajomych + liczbaZnajomychZnajomych);
                    Agent drugi;
                    if (ktoryZnajomy >= znajomi.size()) {
                        ktoryZnajomy -= znajomi.size();
                        int i = 0;
                        while (ktoryZnajomy >= znajomiZnajomych.get(i).liczbaZnajomych) {
                            ktoryZnajomy -= znajomiZnajomych.get(i).liczbaZnajomych;
                            i++;
                        }
                        drugi = znajomiZnajomych.get(i).znajomi.get(ktoryZnajomy);
                    } else drugi = znajomi.get(ktoryZnajomy);
                    spotkania.add(new Spotkanie
                            (generator.nextInt(liczbaDni - dzien) + dzien + 1, this, drugi));
                }
                return spotkania;
            }
        }
        return new ArrayList<>();
    }

    @Override
    public String toString() {
        return super.toString() + " " + nazwa;
    }
}
