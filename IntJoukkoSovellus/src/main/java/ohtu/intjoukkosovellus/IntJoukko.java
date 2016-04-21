package ohtu.intjoukkosovellus;

import java.util.Arrays;

public class IntJoukko {

    public final static int OLETUSKAPASITEETTI = 5,    // aloitustalukon koko
                            OLETUSKASVATUS = 5;  // luotava uusi taulukko on 
                                                 // näin paljon isompi kuin vanha
    private int kasvatuskoko;    // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] lukujono;      // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenLkm;    // Tyhjässä joukossa alkioiden_määrä on nolla. 

    public IntJoukko() {
        this(OLETUSKAPASITEETTI, OLETUSKASVATUS);
    }

    public IntJoukko(int kapasiteetti) {
        this(kapasiteetti, OLETUSKASVATUS);
    }

    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0 || kasvatuskoko < 0) {
            throw new IllegalArgumentException("argumentin on oltava positiivinen");
        }
        lukujono = new int[kapasiteetti];
        alkioidenLkm = 0;
        this.kasvatuskoko = kasvatuskoko;

    }

    public boolean lisaa(int luku) {
        if (!kuuluu(luku)) {
            lukujono[alkioidenLkm] = luku;
            alkioidenLkm++;
            if (lukujono.length == alkioidenLkm) {
                nostaKokoa();
            }
        }
        return false;

    }

    private void nostaKokoa() {
        int[] uusiTaulukko = new int[alkioidenLkm + kasvatuskoko];
        kopioiTaulukko(lukujono, uusiTaulukko);
        lukujono = uusiTaulukko;
    }

    public boolean kuuluu(int luku) {
        for (int i = 0; i < lukujono.length; i++) {
            if (lukujono[i] == luku) {
                return true;
            }
        }
        return false;
    }

    public boolean poista(int luku) {
        boolean loytyiko = false;
        for (int i = 0; i < alkioidenLkm; i++) {
            if (lukujono[i] == luku) {
                loytyiko = true;
                alkioidenLkm--;
            }
            if (loytyiko) lukujono[i] = lukujono[i + 1];
        }
        return loytyiko;
    }

    private void kopioiTaulukko(int[] vanha, int[] uusi) {
        for (int i = 0; i < vanha.length; i++) {
            uusi[i] = vanha[i];
        }
    }

    private static IntJoukko kopioIntJoukko(IntJoukko vanha) {
        IntJoukko uusi = new IntJoukko();
        for (int i = 0; i < vanha.mahtavuus(); i++) {
            uusi.lisaa(vanha.toIntArray()[i]);
        }
        return uusi;
    }

    public int mahtavuus() {
        return alkioidenLkm;
    }

    @Override
    public String toString() {
        String palautus = "{";
        for (int i = 0; i < alkioidenLkm; i++) {
            palautus += lukujono[i];
            if (i < alkioidenLkm - 1) {
                palautus += ", ";
            }
        }
        return palautus + "}";
    }

    public int[] toIntArray() {
        return Arrays.copyOf(lukujono, alkioidenLkm);
    }

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko yhdiste = kopioIntJoukko(a);

        for (int i = 0; i < b.mahtavuus(); i++) {
            yhdiste.lisaa(b.toIntArray()[i]);
        }

        return yhdiste;

    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko leikkaus = new IntJoukko();

        for (int i = 0; i < b.mahtavuus(); i++) {
            if (a.kuuluu(b.toIntArray()[i])) {
                leikkaus.lisaa(b.toIntArray()[i]);
            }
        }

        return leikkaus;

    }

    public static IntJoukko erotus(IntJoukko a, IntJoukko b) {
        IntJoukko erotus = kopioIntJoukko(a);

        for (int i = 0; i < b.mahtavuus(); i++) {
            erotus.poista(b.toIntArray()[i]);
        }

        return erotus;

    }

}
