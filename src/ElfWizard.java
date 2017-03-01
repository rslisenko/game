
public class ElfWizard extends Wizard{
    static int warriorNumber = 1;

    public ElfWizard(){
        this.setRace("Elfs");
        this.setName("-ElfWizard[" + warriorNumber++ +"]");
        this.setRangeAtkPower(10);
    }
}
