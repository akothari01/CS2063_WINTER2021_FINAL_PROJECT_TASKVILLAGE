package ca.unb.mobiledev.task_village_take2;

public class building {

    private int image;
    private String inhabitant;
    randomName rand = new randomName();

    public building(int image){

        this.image = image;
        this.inhabitant = rand.randomName();

    }

    public int getImage(){
        return image;
    }

    public String getInhabitant(){
        return inhabitant;
    }


}
