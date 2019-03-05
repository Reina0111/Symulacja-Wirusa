import java.util.ArrayList;
import java.util.Random;

public class AgentZwykły extends Agent {

    public AgentZwykły(int id){
        super(id);
        nazwa = "zwykły";
    }

    @Override
    public ArrayList<Spotkanie> umowSpotkanie(int dzien, int liczbaDni, double prawdSpotkania, Random generator) {
        if(!stan.equals(StanNaKoniecDnia.MARTWY)) {
            if(stan.equals(StanNaKoniecDnia.CHORY)) prawdSpotkania/=2;
            return super.umowSpotkanie(dzien, liczbaDni, prawdSpotkania, generator);
        }
        return new ArrayList<>();
    }

    @Override
    public String toString() {
        return super.toString() + " " + nazwa;
    }
}
