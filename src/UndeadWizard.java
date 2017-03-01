
public class UndeadWizard extends Wizard{
    static int warriorNumber = 1;

    public UndeadWizard(){
        this.setRace("Undead");
        this.setName("-Necromant[" + warriorNumber++ +"]");
        this.setRangeAtkPower(5);
    }
}
