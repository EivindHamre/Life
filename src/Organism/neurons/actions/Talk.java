package Organism.neurons.actions;

import Organism.Organism;
import Organism.neurons.sensors.ValueSender;
import java.util.HashMap;

public class Talk implements ActionNeuron {

  final float threshold;
  final HashMap<ValueSender, Float> synapseWeights = new HashMap<>();
  final String text;

  public void setNewSynapse(ValueSender input, float weight) {
    synapseWeights.put(input, weight);
  }

  public Talk(float threshold, String text) {
    this.threshold = threshold;
    this.text = text;
  }

  @Override
  public void doAction(Organism organism) {
    Float sum = 0f;
    for (ValueSender s : this.synapseWeights.keySet()) {
      sum += s.getValue() * this.synapseWeights.get(s);
    }
    if (Math.tanh(sum) >= this.threshold) {
      System.out.print(organism);
      System.out.println(" says \"" + this.text + "\"");
    }
  }
}
