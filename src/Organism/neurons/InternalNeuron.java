package Organism.neurons;

import java.util.HashMap;
import java.util.Map;

import Organism.neurons.sensors.ValueSender;

public class InternalNeuron implements ValueSender {

  public float value = 0;
  final Map<ValueSender, Float> synapseWeights = new HashMap<>();

  public void setNewSynapse(ValueSender input, float weight){
    synapseWeights.put(input, weight);
  }

  @Override
  public float getValue() {
    return this.value;
  }

  @Override
  public void UpdateValue() {
    Float sum = 0f;
    for (ValueSender s : this.synapseWeights.keySet()) {
      sum += s.getValue() * this.synapseWeights.get(s);
    }
    this.value = (float) Math.tanh(sum);
  }
}
