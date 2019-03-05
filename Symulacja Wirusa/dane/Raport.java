package dane;

import java.io.*;
import java.util.ArrayList;

public class Raport {
    public ArrayList<String> wynik = new ArrayList<>();

    public Raport() {
        wynik.add("# twoje wyniki powinny zawierać te komentarze");
    }

    @Override
    public String toString() {
        String wynik = "";
        for (String Wynik : this.wynik) {
            wynik += Wynik + "\n";
        }
        return wynik;
    }

    public boolean zapisz(String nazwaPliku) {

        try {
            Writer zapis = new PrintWriter(nazwaPliku);
            zapis.write(this.toString());
            zapis.close();
        } catch (IOException e) {
            System.out.println("Błąd zapisu raportu");
            return false;
        } catch (NullPointerException e) {
            System.out.println("Brak ścieżki do pliku do zapisu raportu");
            return false;
        }
        return true;
    }
}
