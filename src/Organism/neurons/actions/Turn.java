package Organism.neurons.actions;

import Organism.Organism;
import Organism.neurons.sensors.ValueSender;
import java.util.HashMap;

public class Turn implements ActionNeuron {

  final HashMap<ValueSender, Float> synapseWeights = new HashMap<>();
  private double threshold;

  public Turn(double threshold) {
    this.threshold = threshold;
  }

  @Override
  public void doAction(Organism organism) {
    Float sum = 0f;
    for (ValueSender s : this.synapseWeights.keySet()) {
      sum += s.getValue() * this.synapseWeights.get(s);
    }
    double value = Math.atan(sum / this.synapseWeights.size());
    if (Math.abs(value) < this.threshold) return;
    organism.direction += value > 0 ? 1 : 3;
    organism.direction %= 4;
  }

  @Override
  public void setNewSynapse(ValueSender input, float weight) {
    synapseWeights.put(input, weight);
  }
}
