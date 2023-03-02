package Organism.neurons.actions;

import Organism.neurons.sensors.ValueSender;
import java.util.HashMap;

public class SynapseCollection {

  private HashMap<ValueSender, Float> synapses;

  public void newSynapse(ValueSender input, float weight) {
    this.synapses.put(input, weight);
  }

  public double getValue() {
    return Math.tanh(
      this.synapses.entrySet()
        .stream()
        .reduce(
          0f,
          (sum, synapse) ->
            sum + synapse.getKey().getValue() * synapse.getValue(),
          (a, b) -> a + b
        ) /
      this.synapses.size()
    );
  }
}
