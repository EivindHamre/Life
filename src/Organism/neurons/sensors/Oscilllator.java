package Organism.neurons.sensors;

public class Oscilllator implements ValueSender {

    private short cycles = 0;
    private float value;
    private int period = 30;
    private float k = (float) Math.PI/this.period;

    @Override
    public float getValue() {
        return this.value;
    }

    @Override
    public void UpdateValue() {
        this.cycles = (short) (this.cycles + 1 % this.period);
        this.value = (float) (.5*Math.cos(this.k * this.cycles) + .5);
    }

    public void setNewPeriod(int newPeriod){
        this.period = newPeriod;
    }
}
