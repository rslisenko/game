
public class HumanHunter extends Hunter {
    private static int warriorNumber = 0;

    public HumanHunter(){
        this.setRace("Humans");
        this.setMeleeAtkPower(3);
        this.setRangeAtkPower(5);
        this.setName("-HumanHunter[" + warriorNumber++ + "]");
    }
}
