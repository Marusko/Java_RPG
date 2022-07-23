package MS.unstableDungeon.hraHrac;

import javax.swing.JOptionPane;
/**
 * Trieda na spustenie hry.
 * 
 * @author Matúš Suský 
 * @version r2022ver04.2
 */
public class Hra {
    /**
     * Spúšťacia metóda hry, predtým než sa MS.unstableDungeon spustí, zobrazia sa dva okná, jedno na uvedenie do príbehu a či je hráč pripravený hrať, ak nie MS.unstableDungeon sa vypne.
     * V druhom okne sa nachádzajú informácie o tom, ako sa MS.unstableDungeon ovláda.
     */
    public static void main(String[] args) {
        Object[] options = {"Poďme na to", "Nie, chcem ísť preč"};
        int n = JOptionPane.showOptionDialog(null, "Vitaj na panstve Blackwood\nPred nejakým časom sa tu odohrala krvavá bitka medzi démonmi a vojakmi z kasární Backwood,\nktoré sú teraz opustené. Zobudil si sa v posteli vo vile bývalých pánov. Pozrel si sa z okna a tam\nkde sa kedysi deti hrávali, teraz je všetko opustené. Bohužiaľ pre teba, na nič si nespomenieš,\nlen jednu vec máš stále v hlave.\n'Musím sa odtiaľto dostať'\nAle ako? Zrazu v diaľke uvidíš horiaci plameň a okolo neho ľudí. Chceš sa ísť pozrieť bližšie,\nno zistíš že nie sú moc priateľský a bude treba použiť hrubú silu. No oni majú meče a ty len peste.\nV tom si spomenieš, že máš meč. Len ho treba nájsť.\nA potom ti MOŽNO pomôžu.\nPripravený na krvavé dobrodružstvo za slobodou?", "Unstable dungeon", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0 ]);
        if (n == 0) {
            Object[] options2 = {"Jasné"};
            JOptionPane.showOptionDialog(null, "Pohybuješ sa šípkami\nInterakcia s NPC je automaticky\nVeci používaš medzerníkom / enterom\nZoznam úloh Q -> nezabudni kontrolovať\nHru vypneš ESC\nNezabudni na meč\nHLAVNE NÁJDI ÚLOHY\nBude ich mať asi nejaký podivín v lese", "Ešte zopár rád", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options2, options2[0]);
            new HraciaPlocha();
        } else {
            System.exit(0);
        }
    }
}
