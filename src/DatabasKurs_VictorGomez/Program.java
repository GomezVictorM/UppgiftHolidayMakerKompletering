package DatabasKurs_VictorGomez;

import org.w3c.dom.css.CSSStyleRule;

import javax.swing.plaf.synth.SynthSpinnerUI;
import java.util.Scanner;

public class Program {

    boolean run() {

        boolean on = true;
        while (on) {
            mainMenu();
            Scanner scanner = new Scanner(System.in);
            String option = scanner.nextLine();
            switch (option) {
                case "1":
                    System.out.println("");
                case "2":
                    System.out.println("");
                case "n-1":
                    System.out.println("");
                case "n":
                    System.out.println();
            }
        }
    }
}
