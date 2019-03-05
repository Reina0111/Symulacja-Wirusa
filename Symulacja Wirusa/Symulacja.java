import dane.Dane;
import dane.Raport;
import dane.Wczytywanie;

import java.util.ArrayList;
import java.util.Random;

import static java.lang.System.exit;

public class Symulacja {
    private static ArrayList<Agent> stworzPopulacje(Dane dane, Random generator) {
        ArrayList<Agent> populacja = new ArrayList<>();
        int liczbaAgentow = dane.liczbaAgentow;
        for (int i = 0; i < liczbaAgentow; i++) {
            if (generator.nextDouble() <= dane.prawdTowarzyski) {
                populacja.add(new AgentTowarzyski(i + 1));
            } else {
                populacja.add(new AgentZwykły(i + 1));
            }
        }
        int liczbaZnajomosci = (dane.liczbaAgentow) * dane.srZnajomych;
        liczbaZnajomosci /= 2;

        for (int i = 0; i < liczbaZnajomosci; i++) {
            Agent pierwszy = populacja.get(generator.nextInt(liczbaAgentow));
            Agent drugi = populacja.get(generator.nextInt(liczbaAgentow));
            if (pierwszy != drugi && !pierwszy.znajomi.contains(drugi)) {
                pierwszy.znajomi.add(drugi);
                drugi.znajomi.add(pierwszy);
            } else i--;
        }
        for (int k = 0; k < liczbaAgentow; k++) {
            if (populacja.get(k).nazwa.compareTo("towarzyski") == 0) {
                AgentTowarzyski obecny = (AgentTowarzyski) populacja.get(k);
                int liczbaZnajomych = obecny.getZnajomi().size();
                for (int i = 0; i < liczbaZnajomych; i++) {
                    obecny.znajomiZnajomych.add(new ZnajomiZnajomych(obecny.getZnajomi().get(i), obecny));
                }
            }
        }
        populacja.get(generator.nextInt(liczbaAgentow)).stan = StanNaKoniecDnia.CHORY;
        return populacja;
    }

    private static Raport symuluj(Dane dane, ArrayList<Agent> populacja, Random generator) {
        int liczbaAgentow = populacja.size();
        Raport raport = new Raport();
        ArrayList<Stan> stanNaDzien = new ArrayList<>();
        stanNaDzien.add(new Stan(liczbaAgentow - 1, 1, 0));
        raport.wynik.add(dane.toString());
        raport.wynik.add("# agenci jako: id typ lub id* typ dla chorego");
        for (Agent Populacja : populacja) {
            raport.wynik.add(Populacja.toString());
        }
        raport.wynik.add("\n# graf");
        for (Agent Populacja : populacja) {
            raport.wynik.add(Populacja.opisGrafu());
        }
        raport.wynik.add("\n# liczność w kolejnych dniach");
        ArrayList<Spotkanie> spotkania = new ArrayList<>();
        int zdrowi = liczbaAgentow - 1;
        int chorzy = 1;
        int odporni = 0;
        for (int i = 1; i <= dane.liczbaDni; i++) {
            if (chorzy > 0) {
                for (int j = 0; j < liczbaAgentow; j++) {
                    Agent obecny = populacja.get(j);
                    if (obecny.stan.equals(StanNaKoniecDnia.CHORY)) {
                        if (generator.nextDouble() <= dane.smiertelnosc) {
                            obecny.stan = StanNaKoniecDnia.ZDROWY;
                            chorzy--;
                            for (Agent agent : populacja) {
                                if (agent.getClass().equals(AgentTowarzyski.class)) {
                                    AgentTowarzyski znajomy = (AgentTowarzyski) agent;
                                    for (int k = 0; k < znajomy.znajomiZnajomych.size(); k++) {
                                        if (znajomy.znajomiZnajomych.get(k).agent.equals(obecny)) {
                                            znajomy.znajomiZnajomych.remove(k);
                                            k--;
                                        }
                                    }
                                }
                            }
                        } else {
                            if (generator.nextDouble() <= dane.prawdWyzdrowienia) {
                                obecny.stan = StanNaKoniecDnia.UODPORNIONY;
                                chorzy--;
                                odporni++;
                            }
                        }
                    }

                    if (i < dane.liczbaDni && obecny.znajomi.size() > 0)
                        spotkania.addAll(obecny.umowSpotkanie(i, dane.liczbaDni, dane.prawdSpotkania, generator));
                }
                spotkania.sort(new ComparatorSpotkanPoDniach());

                int spotkaniaDanegoDnia = 0;
                while (spotkaniaDanegoDnia < spotkania.size() && spotkania.get(spotkaniaDanegoDnia).getDzien() == i) {
                    spotkaniaDanegoDnia++;
                }
                for (int j = 0; j < spotkaniaDanegoDnia; j++) {
                    int zmianaStanu = spotkania.get(0).przeprowadz(dane.prawdZarazenia, generator);
                    chorzy += zmianaStanu;
                    zdrowi -= zmianaStanu;
                    spotkania.remove(0);
                }
            }
            stanNaDzien.add(new Stan(zdrowi, chorzy, odporni));
        }
        for (Stan stan : stanNaDzien) {
            raport.wynik.add(stan.toString());
        }
        return raport;
    }


    public static void main(String[] args) {
        Wczytywanie w = new Wczytywanie();
        Dane dane = w.wczytaj();
        try {
            Random generator = new Random(dane.seed);

            ArrayList<Agent> populacja = stworzPopulacje(dane, generator);

            Raport raport = symuluj(dane, populacja, generator);
            if (raport.zapisz(dane.plikZRaportem)) {
                exit(0);
            } else {
                exit(1);
            }
        } catch (NullPointerException e) {
            exit(1);
        }
    }

}
