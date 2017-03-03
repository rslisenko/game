import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

import static java.lang.System.exit;
/*
*                           КРАТКОЕ ОПИСАНИЕ ИГРЫ
*   Игра пошаговая. Есть 4 расы - Ельфы, Люди, Орки, Нежить
*   Эльфы и Люди сражаются против Орков и Нежити. Расы выбираются случайным образом.
*   В каждом отряде 4 бойца, 3 стрелка и 1 маг.
*   У каждого есть свой набор скиллов(ближняя атака, дальняя атака, баф, дебаф, диспел)
*
*                       ОПИСАНИЕ НАЗНАЧЕНИЯ ИГРОВЫХ КЛАССОВ
*   Game - основной  класс игры, где создаются отряды и реализован игровой цикл.
*   Warrior, Hunter, Wizard - абстрактные класы ,  скоторых создаются персонажи всех рас
*   Buf, Debuf, MeleeAtacker, RangeAttacker - интерфейсы, для реализации умений персонажей
*
* */
public class Game {
    public static void main(String[] args) {
//        Scanner scan = new Scanner(System.in);
        String race1; // race 1 name
        String race2; //
        int race1Turn = 1; // turns count
        int race2Turn = 1;
        ArrayList<Character> army1 = new ArrayList(); //
        ArrayList<Character> army2 = new ArrayList();
        FileOutputStream fos;
        PrintStream printStream; //

        try {
            fos = new FileOutputStream("log.txt"); // to record game turn
            printStream = new PrintStream(fos);
//            System.setOut(printStream);
        }catch (IOException e){
            System.out.println("Cannot create file.");
        }

/* Создание отрядов */
        int chooseFirstRace = (int)(Math.random()*2); // выбор одной из рас
        if (chooseFirstRace == 0){
            race1 = "Humans";
            army1.add(new HumanWizard());
            army1.add(new HumanHunter());
            army1.add(new HumanHunter());
            army1.add(new HumanHunter());
            army1.add(new HumanWarrior());
            army1.add(new HumanWarrior());
            army1.add(new HumanWarrior());
            army1.add(new HumanWarrior());
        } else {
            race1 = "Elfs";
            army1.add(new ElfWizard());
            army1.add(new ElfHunter());
            army1.add(new ElfHunter());
            army1.add(new ElfHunter());
            army1.add(new ElfWarrior());
            army1.add(new ElfWarrior());
            army1.add(new ElfWarrior());
            army1.add(new ElfWarrior());
            army1.add(new ElfWarrior());
        }

        int chooseSecondRace = (int)(Math.random()*2);

        if (chooseSecondRace == 0) {
            race2 = "Orks";
            army2.add(new OrkWizard());
            army2.add(new OrkHunter());
            army2.add(new OrkHunter());
            army2.add(new OrkHunter());
            army2.add(new OrkWarrior());
            army2.add(new OrkWarrior());
            army2.add(new OrkWarrior());
            army2.add(new OrkWarrior());
        } else {
            race2 = "Undead";
            army2.add(new UndeadWizard());
            army2.add(new UndeadHunter());
            army2.add(new UndeadHunter());
            army2.add(new UndeadHunter());
            army2.add(new UndeadWarrior());
            army2.add(new UndeadWarrior());
            army2.add(new UndeadWarrior());
            army2.add(new UndeadWarrior());
        }

        System.out.println("----- The battle between " + race1 + " and " + race2 + " has began -----");

        /////////////////////////////////////////////////////////////////////   ГЛАВНЫЙ ЦИКЛ ИГРЫ

        while (army1.size() > 0 || army2.size() > 0) { // Ходим, пока не умрут все бойцы одной из рас

            // RACE 1 TURN

            if (army1.size() == 0) {
                System.out.println("The Battle is over. " + race2 + " win !!!");
            } else {
                System.out.println("----- " + race1 + " TURN-----");
                System.out.println("Turn#:" + race1Turn++ );
            }

            Iterator<Character> race1Iter = army1.iterator();
            while (race1Iter.hasNext()) { // ходим всеми членами отряда
                Character currentWarrior = race1Iter.next();

                if (currentWarrior instanceof HumanWarrior) { // Выбран человек-боец
                    HumanWarrior hw = (HumanWarrior) currentWarrior;
                    hw.makeTurn(army2);
                }
                if (currentWarrior instanceof HumanHunter) { // Выбран человек-лучник
                    HumanHunter hh = (HumanHunter) currentWarrior;
                    hh.makeTurn(army2);
                }
                if (currentWarrior instanceof HumanWizard) { // Выбран человек-калдун
                    HumanWizard hwiz = (HumanWizard) currentWarrior;
                    hwiz.makeTurn(hwiz, army1, army2);
                }
                if (currentWarrior instanceof ElfWarrior) { // Выбран эльф-боец
                    ElfWarrior ew = (ElfWarrior) currentWarrior;
                    ew.makeTurn(army2);
                }
                if (currentWarrior instanceof ElfHunter) { // Выбран эльф-лучник
                    ElfHunter eh = (ElfHunter) currentWarrior;
                    eh.makeTurn(army2);
                }
                if (currentWarrior instanceof ElfWizard) { // Выбран эльф-калдун
                    ElfWizard ewiz = (ElfWizard) currentWarrior;
                    ewiz.makeTurn(ewiz, army1, army2);
                }
                if (currentWarrior.getHp() < 0) {
                    System.out.println(currentWarrior + " died.");
                    race1Iter.remove();
                }
            }
            /* убираем трупы с отряда */
            Iterator<Character> iter2 = army2.iterator();
            while (iter2.hasNext()) {
                Character c = iter2.next();
                if (c.getHp() <= 0) {
                    c.setHp(0);
                    System.out.println(c + " died. ");
                    iter2.remove(); // если здоровье меньше 0 , то удаляем бойца с отряда
                }
            }
            /* выводим состояние вражеского отряда */
            System.out.println("--> " + race2 + " health status <--");
            if (army2.size() > 0) {
                for (Character i: army2){
                    System.out.println(i);
                }
                System.out.println("----- " + race1 + " END turn-----");
            }
            else{
                System.out.println("----- no " + race2 + " left -----");
                System.out.println("----- The game is over. " + race1 + " WIN -----");
                exit(0);
            }

            ////////////////////////////////////////////////////////////////

            // RACE 2 TURN

            if (army2.size() == 0) {
                System.out.println("The game is over. " + race1 + " WIN !!!");
            } else {
                System.out.println("----- " + race2 + " TURN -----");
                System.out.println("turn#:" + race2Turn++);
            }

            Iterator<Character> race2Iter = army2.iterator();
            while (race2Iter.hasNext()) { // ходим всеми членами отряда
                Character currentWarrior = race2Iter.next();

                if (currentWarrior instanceof OrkWarrior) {
                    OrkWarrior ow = (OrkWarrior) currentWarrior;
                    ow.makeTurn(army1);
                }
                if (currentWarrior instanceof OrkHunter) { // Выбран Лучник
                    OrkHunter oh = (OrkHunter) currentWarrior;
                    oh.makeTurn(army1);
                }
                if (currentWarrior instanceof OrkWizard){
                    OrkWizard owiz = (OrkWizard) currentWarrior;
                    owiz.makeTurn(owiz, army2, army1);
                }
                if (currentWarrior instanceof UndeadWarrior) {
                    UndeadWarrior uw = (UndeadWarrior) currentWarrior;
                    uw.makeTurn(army1);
                }
                if (currentWarrior instanceof UndeadHunter) { // Выбран Лучник
                    UndeadHunter uh = (UndeadHunter) currentWarrior;
                    uh.makeTurn(army1);
                }
                if (currentWarrior instanceof UndeadWizard){
                    UndeadWizard uwiz = (UndeadWizard) currentWarrior;
                    uwiz.makeTurn(uwiz, army2, army1);
                }
                if (currentWarrior.getHp() < 0) {
                    System.out.println(currentWarrior + " died. ");
                    race2Iter.remove();
                }
            }

            /* убираем трупы с отряда */
            Iterator<Character> iter1 = army1.iterator();
            while (iter1.hasNext()) {
                Character c = iter1.next();
                if (c.getHp() <= 0) {
                    c.setHp(0);
                    System.out.println(c + " died.");
                    iter1.remove();
                }
            }

            /* выводим состояние вражеского отряда */
            System.out.println("--> " + race1 + " health status : <--");
            if (army1.size() > 0) {
                for (Character i: army1){
                    System.out.println(i);
                }
                System.out.println("----- " + race2 + " END turn -----");
            }
            else{
                System.out.println("-----  no " + race1 + " left -----");
                System.out.println("----- The game is over. " + race2 + " WIN -----");
                exit(0);
            }
//            System.out.print("Нажмите Enter , чтобы сделать следующий ход: \n");
//            scan.nextLine();
        }
    }
}
