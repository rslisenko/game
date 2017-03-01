
abstract class Character {
    private double hp = 100;
    private String race;
    private String name;

    public void setHp(double hp){this.hp = hp;};
    public double getHp(){return this.hp;};

    public void setRace(String race){
        this.race = race;
    }
    public String getRace(){
        return this.race;
    }

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }

    @Override
    public String toString() {
        return this.getName() + "[hp:" + this.getHp() + "]";
    }

}
