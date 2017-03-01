import java.util.ArrayList;

public class Wizard extends Character implements RangeAtacker, Buf, Debuf, Dispel {

    private double rangeAtkPower = 0;
    private double bufRangeAtkPower = 0;
    private double debufRangeAtkPower = 0;

    /* установка значения атаки/бафа/дебафа для дальней атаки */
    @Override
    public void setRangeAtkPower( double atk) { this.rangeAtkPower = atk; }
    public void setBufRangeAtkPower(double atkPower) {
        this.bufRangeAtkPower = atkPower;
    }
    public void setDebufRangeAtkPower(double atkPower) {
        this.debufRangeAtkPower = atkPower;
    }

    @Override
    public double getRangeAtkPower() {
        return this.rangeAtkPower;
    }
    public double getBufRangeAtkPower() {
        return this.bufRangeAtkPower;
    }
    public double getDebufRangeAtkPower() {
        return this.debufRangeAtkPower;
    }

    /* расчет урона дальней атакой */
    @Override
    public void rangeAtk(Character character) {
            double atk = this.getRangeAtkPower();
            character.setHp(character.getHp() - atk); // отнимаем количество здоровья цели равное силе атаки атакующего
            System.out.println(this.getName() + " made " + atk + " range damage to " + character + ". ");
    }

    /* наложение бафа на союзника */
    @Override
    public void buf(Character character) {
        if (character instanceof Warrior) { /* если вибран Воин*/
            Warrior war = (Warrior)character;
            war.setBufMeleeAtkPower(war.getMeleeAtkPower()/2);// добавляем бонус к его ближней атаке
            System.out.println(this.getName() + " made buf to " + character + ". attack damage increase by 50%.");
        }
        if (character instanceof Hunter) {/* если вибран Стрелок*/
            Hunter hunt = (Hunter)character;
            hunt.setBufMeleeAtkPower(hunt.getMeleeAtkPower()/2);
            hunt.setBufRangeAtkPower(hunt.getRangeAtkPower()/2);
            System.out.println(this.getName() + " made buf to " + character + ". attack damage increase by 50%.");
        }
        if (character instanceof Wizard) {/* если вибран Маг*/
            Wizard wiz = (Wizard)character;
            wiz.setBufRangeAtkPower(wiz.getRangeAtkPower()/2);
            System.out.println(this.getName() + " made buf to " + character + ". attack damage increase by 50%.");
        }
    }

    /* наложение дебафа на врага */
    @Override
    public void debuf(Character character) {
        if (character instanceof Warrior) {
            Warrior war = (Warrior)character;
            war.setDebufMeleeAtkPower(war.getMeleeAtkPower()/2);
            System.out.println(this.getName() + " made debuf to " + character + ". attack damage increase by 50%.");
        }
        if (character instanceof Hunter) {
            Hunter hunt = (Hunter)character;
            hunt.setDebufRangeAtkPower(hunt.getRangeAtkPower()/2);
            hunt.setDebufRangeAtkPower(hunt.getRangeAtkPower()/2);
            System.out.println(this.getName() + " made debuf to " + character + ". attack damage decreased by 50%.");
        }
        if (character instanceof Wizard) {
            Wizard wiz = (Wizard)character;
            wiz.setDebufRangeAtkPower(wiz.getRangeAtkPower()/2);
            System.out.println(this.getName() + "  made debuf to " + character + ". attack damage decreased by 50%.");
        }
    }

    @Override
    public void dispel(Character character) {
        if (character instanceof Warrior) {
            Warrior war = (Warrior)character;
            war.setBufMeleeAtkPower(0);
            System.out.println(this.getName() + " made dispel to " + character + ". ");
        }
        if (character instanceof Hunter) {
            Hunter hunt = (Hunter)character;
            hunt.setBufMeleeAtkPower(0); // сброс бонусов
            hunt.setBufRangeAtkPower(0);
            System.out.println(this.getName() + " made dispel to  " + character + ". ");
        }
        if (character instanceof Wizard) {
            Wizard wiz = (Wizard)character;
            wiz.setBufRangeAtkPower(0);
            System.out.println(this.getName() + " made dispel to  " + character + ". ");
        }
    }
    public void makeTurn(Character character, ArrayList<Character> allyArmy, ArrayList<Character> enemyArmy){
        int randomEnemyTarget = (int) (Math.random() * enemyArmy.size()); // выбираем случайную вражескую цель
        int randomAllyTarget = (int) (Math.random() * allyArmy.size()); // выбираем случайную союзную цель

        int randomSkill = (int) (Math.random() * 2); // Выбираем случайный скилл

        if (character instanceof ElfWizard) { // Выбран волшебник людей ?
            if (randomSkill == 1) {
                this.buf(allyArmy.get(randomAllyTarget));// Накладываем улучшение на союзника
            } else {
                this.rangeAtk(enemyArmy.get(randomEnemyTarget));// Атакуем врага дальней атакой
            }
        }
        if (character instanceof HumanWizard) { // Выбран волшебник людей ?
            if (randomSkill == 1) {
                this.buf(allyArmy.get(randomAllyTarget));// Накладываем улучшение на союзника
            } else {
                this.rangeAtk(enemyArmy.get(randomEnemyTarget));// Атакуем врага дальней атакой
            }
        }
        if (character instanceof OrkWizard) { // Выбран волшебник орков ?
            if (randomSkill == 1) {
                this.buf(allyArmy.get(randomAllyTarget));// Накладываем улучшение на союзника
            } else {
                this.dispel(enemyArmy.get(randomEnemyTarget));// Накладываем дебаф на врага
            }
        }
        if (character instanceof UndeadWizard) { // Выбран волшебник людей ?
            if (randomSkill == 1) {
                this.debuf(enemyArmy.get(randomAllyTarget));// Накладываем улучшение на союзника
            } else {
                this.rangeAtk(enemyArmy.get(randomEnemyTarget));// Атакуем врага дальней атакой
            }
        }
        this.setBufRangeAtkPower(0); // сбрасываем значение бафа после хода
        this.setDebufRangeAtkPower(0); // сбрасываем значение дебафа после хода
    }
}
