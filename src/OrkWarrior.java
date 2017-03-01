
public class OrkWarrior extends Warrior {
    static int warriorNumber = 1;

    public OrkWarrior(){
        this.setRace("Orks");
        this.setName("-OrkWarrior[" + warriorNumber++ +"]");
        this.setMeleeAtkPower(20);
    }
}
