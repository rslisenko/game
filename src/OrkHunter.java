
public class OrkHunter extends Hunter{

    private static int warriorNumber = 0;

    public OrkHunter(){
        this.setRace("Orks");
        this.setMeleeAtkPower(5);
        this.setRangeAtkPower(8);
        this.setName("-OrkHunter[" + warriorNumber++ +"]");
    }
}

