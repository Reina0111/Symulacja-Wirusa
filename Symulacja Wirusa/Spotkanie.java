import java.util.Random;

public class Spotkanie {
    private final int dzien;
    private final Agent pierwszy;
    private final Agent drugi;

    public Spotkanie(int dzien, Agent pierwszy, Agent drugi) {
        this.dzien = dzien;
        if (pierwszy.id < drugi.id) {
            this.pierwszy = pierwszy;
            this.drugi = drugi;
        } else {
            this.pierwszy = drugi;
            this.drugi = pierwszy;
        }
    }

    public int przeprowadz(double prawdZarazenia, Random generator) {
        if (!pierwszy.stan.equals(StanNaKoniecDnia.MARTWY) && !drugi.stan.equals(StanNaKoniecDnia.MARTWY)) {
            if (pierwszy.stan.equals(StanNaKoniecDnia.MARTWY) && drugi.stan.equals(StanNaKoniecDnia.ZDROWY)) {
                if (generator.nextDouble() <= prawdZarazenia) {
                    drugi.stan = StanNaKoniecDnia.CHORY;
                    return 1;
                }
            } else {
                if (pierwszy.stan.equals(StanNaKoniecDnia.ZDROWY) && drugi.stan.equals(StanNaKoniecDnia.CHORY)) {
                    if (generator.nextDouble() <= prawdZarazenia) {
                        pierwszy.stan = StanNaKoniecDnia.CHORY;
                        return 1;
                    }
                }
            }
        }
        return 0;
    }

    public int getDzien() {
        return dzien;
    }

}
