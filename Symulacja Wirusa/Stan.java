public class Stan {
    private int liczbaZdrowych;
    private int liczbaChorych;
    private int liczbaUodpornionych;

    public Stan(int zdrowi, int chorzy, int odporni) {
        liczbaZdrowych = zdrowi;
        liczbaChorych = chorzy;
        liczbaUodpornionych = odporni;
    }

    @Override
    public String toString() {
        return "" + liczbaZdrowych + " " + liczbaChorych + " " + liczbaUodpornionych;
    }
}
