import java.util.ArrayList;

abstract class Hunter extends Character implements MeleeAtacker, RangeAtacker {
    private double meleeAtkPower = 0;
    private double rangeAtkPower = 0;
    private double bufMeleeAtkPower = 0;
    private double debufMeleeAtkPower = 0;
    private double bufRangeAtkPower = 0;
    private double debufRangeAtkPower = 0;

    /* установка значения атаки/бафа/дебафа для ближней атаки */
    @Override
    public void setMeleeAtkPower( double atk) { this.meleeAtkPower = atk; }
    public void setBufMeleeAtkPower(double atkPower) {
        this.bufMeleeAtkPower = atkPower;
    }
    public void setDebufMeleeAtkPower(double atkPower) {
        this.debufMeleeAtkPower = atkPower;
    }

    @Override
    public double getMeleeAtkPower() {
        return this.meleeAtkPower;
    }
    public double getBufMeleeAtkPower() {
        return this.bufMeleeAtkPower;
    }
    public double getDebufMeleeAtkPower() {
        return this.debufMeleeAtkPower;
    }

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
        double atk = this.rangeAtkPower + this.bufRangeAtkPower - this.debufRangeAtkPower; // атака с бафом/дебафом
        character.setHp(character.getHp() - atk); // отнимаем количество здоровья цели равное силе атаки атакующего
        System.out.println(this.getName() + " made " + atk + " range damage to " + character + ". ");
    }
    /* расчет урона ближней атакой */
    @Override
    public void meleeAtk(Character character) {
        double atk = this.meleeAtkPower + this.bufMeleeAtkPower - this.debufMeleeAtkPower; // атака с бафом/дебафом
        character.setHp(character.getHp() - atk); // отнимаем количество здоровья цели равное силе атаки атакующего
        System.out.println(this.getName() + " made " + atk + " melee damage to " + character + ". ");
    }

    public void makeTurn(ArrayList<Character> enemyArmy){
        int randomEnemyTarget = (int) (Math.random() * enemyArmy.size());
        int randomSkill = (int) (Math.random() * 2); // Выбираем случайный скилл

        if (randomSkill == 1) {
            this.meleeAtk(enemyArmy.get(randomEnemyTarget));// Атакуем врага ближней атакой
        } else {
            this.rangeAtk(enemyArmy.get(randomEnemyTarget));// Атакуем врага дальней атакой
        }

        this.setBufMeleeAtkPower(0);
        this.setDebufMeleeAtkPower(0);
        this.setBufRangeAtkPower(0);
        this.setDebufRangeAtkPower(0);
    }

}
