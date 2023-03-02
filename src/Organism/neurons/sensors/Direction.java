package Organism.neurons.sensors;

import Organism.Organism;

public class Direction implements ValueSender{

  float value;
  Organism parent;

  public Direction(Organism parent){
    this.parent = parent;
  }

  @Override
  public float getValue() {
    return this.value;
  }

  @Override
  public void UpdateValue() {
    this.value = parent.direction / 4f;
  }
}
