package Organism.neurons.sensors;

public class Constant implements ValueSender{

  private float value = 1f;

  @Override
  public float getValue() {
    return this.value;
  }

  @Override
  public void UpdateValue() {}

  public void setNewValue(float value){
    if(Math.abs(value) > 1) return;
    this.value = value;
  }
  
}
