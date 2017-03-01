
public class UndeadHunter extends Hunter{
    private static int warriorNumber = 0;

    public UndeadHunter(){
        this.setRace("Undead");
        this.setMeleeAtkPower(4);
        this.setRangeAtkPower(2);
        this.setName("-UndeadHunter[" + warriorNumber++ + "]");
    }
}
