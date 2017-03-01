
public class OrkWizard extends Wizard{
    static int warriorNumber = 1;

    public OrkWizard(){
        this.setRace("Orks");
        this.setName("-Shaman[" + warriorNumber++ + "]");
    }
}
