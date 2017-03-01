
public class HumanWarrior extends Warrior{
    static int warriorNumber = 1;

    public HumanWarrior(){
        this.setRace("Humans");
        this.setName("-HumanWarrior[" + warriorNumber++ + "]");
        this.setMeleeAtkPower(20);
    }
}
