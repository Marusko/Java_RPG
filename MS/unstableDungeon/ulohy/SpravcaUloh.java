package MS.unstableDungeon.ulohy;

import javax.swing.JOptionPane;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * Trieda na načítanie a spravovanie úloh
 *
 * @author Matúš Suský
 * @version r2022ver04.2
 */

public class SpravcaUloh {
    private final TreeMap<Integer, IUlohy> ulohy;
    private final int pocetUloh;
    private int pocetHotovychUloh;
    private int aktualizovanyPocetUloh;
    private int aUlohy;

    public SpravcaUloh() {
        this.ulohy = new TreeMap<>();
        this.aUlohy = 0;
        try {
            this.nacitajUlohy();
        } catch (IOException e) {
            System.out.println("Chyba pri načítavaní úloh");
        }
        this.pocetUloh = this.ulohy.keySet().size();
        this.pocetHotovychUloh = 0;
        this.aktualizovanyPocetUloh = 0;
    }

    public TreeMap<Integer, IUlohy> getUlohy() {
        return this.ulohy;
    }

    public int getPocetUloh() {
        return this.pocetUloh;
    }

    public int getPocetHotovychUloh() {
        return this.pocetHotovychUloh;
    }

    /**
     * Metóda zistí či hráč zobral všetky úlohy
     * @return true alebo false
     */
    public boolean vsetkyZobrane() {
        this.aktualizovanyPocetUloh = this.ulohy.keySet().size();
        int minus = 0;
        for (int i = 0; i < this.aktualizovanyPocetUloh; i++) {
            if (this.ulohy.get(i).getSkryty()) {
                minus++;
            }
        }
        this.aktualizovanyPocetUloh -= minus;
        return this.aUlohy - this.aktualizovanyPocetUloh != 0;
    }

    /**
     * Metóda načíta všetky úlohy zo súboru
     */
    private void nacitajUlohy() throws IOException {
        File subor = new File("Data/Ulohy/ulohy_U_D.txt");
        Scanner sc = new Scanner(subor);
        int pocitadlo = 0;
        while (sc.hasNextLine()) {
            String uloha = sc.nextLine();
            String[] split = uloha.split(";");
            if (split.length == 3) {
                this.ulohy.put(pocitadlo, new ZbieranieUloha("[Z]" + split[0], split[1], split[2]));
            } else if (split.length == 4) {
                this.ulohy.put(pocitadlo, new ZabijanieUloha("[K]" + split[0], split[1], split[2], split[3]));
            }
            pocitadlo++;
        }
    }

    /**
     * Metóda vypíše všetky úlohy ktoré nie sú aktívne a ak hráč úlohu prijme tak ju označí ako aktívnu
     */
    public void vypisUlohy() {
        if (this.vsetkyZobrane()) {
            Object[] options = {"Áno prijímam", "Nie, poďme ďalej"};
            for (int i = 0; i < this.ulohy.size(); i++) {
                if (!this.ulohy.get(i).getAktivny()) {
                    int n = JOptionPane.showOptionDialog(null, this.ulohy.get(i).getPopis() + "\nPrijímaš túto výzvu?", this.ulohy.get(i).getNazov(), JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
                    if (n == 0) {
                        this.ulohy.get(i).setAktivny(true);
                        this.aUlohy++;
                    }
                }
            }
        } else {
            Object[] options2 = {"No čo už"};
            JOptionPane.showOptionDialog(null, "Aktuálne nemáš na výber\nžiadne ďalšie úlohy", "Úlohy", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, options2, options2[0]);
        }
    }

    /**
     * Metóda vypíše všetky úlohy ktoré sú aktívne, ak hráč splní úlohu označí úlohu ako skrytú a nebude ju viac zobrazovať
     * Tiež sa môže hráč rozhodnúť že sa vzdá úlohy, potom ju označí ako nie aktívnu
     */
    public void vypisAUlohy() {
        if (this.aUlohy > 0) {
            Object[] options = {"Ďalej", "Vzdať sa úlohy"};
            for (int i = 0; i < this.ulohy.size(); i++) {
                if (this.ulohy.get(i).getAktivny() && this.ulohy.get(i).getHotovy() && !this.ulohy.get(i).getSkryty()) {
                    Object[] options3 = {"Vymazať"};
                    JOptionPane.showOptionDialog(null, this.ulohy.get(i).getPopis() + "\nUž je hotová a nemusíš ju znova robiť!", "[Hotová]" + this.ulohy.get(i).getNazov(), JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options3, options3[0]);
                    this.ulohy.get(i).setSkryty(true);
                    this.pocetHotovychUloh++;
                    this.aUlohy--;
                } else if (this.ulohy.get(i).getAktivny() && !this.ulohy.get(i).getSkryty()) {
                    int n = JOptionPane.showOptionDialog(null, this.ulohy.get(i).getPopis() + "\nČo chceš robiť?", "[A]" + this.ulohy.get(i).getNazov(), JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
                    if (n == 1) {
                        this.ulohy.get(i).setAktivny(false);
                        this.aUlohy--;
                    }
                }
            }
        } else {
            Object[] options2 = {"No čo už"};
            JOptionPane.showOptionDialog(null, "Aktuálne nemáš žiadne aktívne úlohy", "Aktívne úlohy", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, options2, options2[0]);
        }
    }
}