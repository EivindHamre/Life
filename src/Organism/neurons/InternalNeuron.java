package Organism.neurons;

import Organism.neurons.sensors.ValueSender;

public class InternalNeuron extends SynapseCollection implements ValueSender {

  private float value = 0;

  @Override
  public void UpdateValue() {
    this.value = super.getValue();
  }

  @Override
  public float getValue() {
    return this.value;
  }
}
