
public class HumanWizard extends Wizard {
    static int warriorNumber = 1;

    public HumanWizard(){
        this.setRace("Humans");
        this.setName("-Gendalf[" + warriorNumber++ +"]");
        this.setRangeAtkPower(4);
    }
}
