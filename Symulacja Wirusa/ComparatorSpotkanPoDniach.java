import java.util.Comparator;

public class ComparatorSpotkanPoDniach implements Comparator<Spotkanie> {

    @Override
    public int compare(Spotkanie o1, Spotkanie o2) {
        return o1.getDzien()-o2.getDzien();
    }
}