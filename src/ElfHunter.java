
public class ElfHunter extends Hunter{
    private static int warriorNumber = 0;

    public ElfHunter(){
        this.setRace("Elfs");
        this.setMeleeAtkPower(3);
        this.setRangeAtkPower(7);
        this.setName("-Legolas[" + warriorNumber++ + "]");
    }
}
