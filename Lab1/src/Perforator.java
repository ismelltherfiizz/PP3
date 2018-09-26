public class Perforator {


    private int rotationsPerMinute;
    private int beatsPerMinute;
    private String Color;

    public Perforator() {}


    public Perforator(int rotationsPerMinute, int beatsPerMinute, String Color){
        setRotationsPerMinute(rotationsPerMinute);
        setBeatsPerMinute(beatsPerMinute);
        setColor(Color);

    }
    public void setRotationsPerMinute(int rotationsPerMinute) {this.rotationsPerMinute = rotationsPerMinute; }

    public void setBeatsPerMinute(int beatsPerMinute) {
        this.beatsPerMinute = beatsPerMinute;
    }

    public void setColor(String color) {
        this.Color = color;
    }

    public int getRotationsPerMinute() {
        return rotationsPerMinute;
    }

    public int getBeatsPerMinute() {
        return beatsPerMinute;
    }

    public String getColor() {
        return Color;
    }
}
