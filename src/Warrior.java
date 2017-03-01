import java.util.ArrayList;

abstract class Warrior extends Character implements MeleeAtacker {
    private double meleeAtkPower = 0;
    private double bufMeleeAtkPower = 0;
    private double debufMeleeAtkPower = 0;
    private double totalMeleeAtkPower = 0;

    /* установка значения атаки/бафа/дебафа для ближней атаки */
    @Override
    public void setMeleeAtkPower( double atk) { this.meleeAtkPower = atk; }
    public void setBufMeleeAtkPower(double atkPower) {
        this.bufMeleeAtkPower = atkPower;
    }
    public void setDebufMeleeAtkPower(double atkPower) {
        this.debufMeleeAtkPower = atkPower;
    }
    public void setTotalMeleeAtkPower(double atkPower) {
        this.totalMeleeAtkPower = atkPower;
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
    public double getTotalMeleeAtk(){ return this.totalMeleeAtkPower; };

    @Override
    public void meleeAtk(Character character) {
        totalMeleeAtkPower = this.meleeAtkPower + this.bufMeleeAtkPower - this.debufMeleeAtkPower;
        character.setHp(character.getHp() - totalMeleeAtkPower); // отнимаем количество здоровья цели равное силе атаки атакующего
        System.out.println(this.getName() + " made " + totalMeleeAtkPower + " melee damage to " + character + ". ");
    }

    public void makeTurn(ArrayList<Character> enemyArmy){
        int randomEnemyTarget = (int) (Math.random() * enemyArmy.size());
        this.meleeAtk(enemyArmy.get(randomEnemyTarget));
        this.setBufMeleeAtkPower(0);
        this.setDebufMeleeAtkPower(0);
    }
}
