
public class ElfWarrior extends Warrior{
    static int warriorNumber = 1;

    public ElfWarrior() {
        this.setRace("Elfs");
        this.setName("-ElfWarrior[" + warriorNumber++ + "]");
        this.setMeleeAtkPower(15);
    }
}
