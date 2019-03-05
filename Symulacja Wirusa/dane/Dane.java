package dane;

public class Dane {
    public final int seed;
    public final int liczbaAgentow;
    public final double prawdTowarzyski;
    public final double prawdSpotkania;
    public final double prawdZarazenia;
    public final double prawdWyzdrowienia;
    public final double smiertelnosc;
    public final int liczbaDni;
    public final int srZnajomych;
    public final String plikZRaportem;

    public Dane(int seed, int liczbaAgentow, double prawdTowarzyski, double prawdSpotkania,
                double prawdZarazenia, double prawdWyzdrowienia, double smiertelnosc,
                int liczbaDni, int srZnajomych, String plikZRaportem) {
        this.seed = seed;
        this.liczbaAgentow = liczbaAgentow;
        this.prawdTowarzyski = prawdTowarzyski;
        this.prawdSpotkania = prawdSpotkania;
        this.prawdZarazenia = prawdZarazenia;
        this.prawdWyzdrowienia = prawdWyzdrowienia;
        this.smiertelnosc = smiertelnosc;
        this.liczbaDni = liczbaDni;
        this.srZnajomych = srZnajomych;
        this.plikZRaportem = plikZRaportem;
    }

    @Override
    public String toString() {
        String wynik = "";
        wynik += "seed=" + seed + "\n";
        wynik += "liczbaAgentów=" + liczbaAgentow + "\n";
        wynik += "pradwSpotkania=" + prawdSpotkania + "\n";
        wynik += "prawdZarażenia=" + prawdZarazenia + "\n";
        wynik += "prawdWyzdrowienia=" + prawdWyzdrowienia + "\n";
        wynik += "śmiertelność=" + smiertelnosc + "\n";
        wynik += "liczbaDni=" + liczbaDni + "\n";
        wynik += "śrZnajomych=" + srZnajomych + "\n";
        return wynik;
    }

}
