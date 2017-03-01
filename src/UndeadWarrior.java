
public class UndeadWarrior extends Warrior{
    static int warriorNumber = 1;

    public UndeadWarrior(){
        this.setRace("Undead");
        this.setName("-Zombi[" + warriorNumber++ + "]");
        this.setMeleeAtkPower(18);
    }
}
