package Organism.neurons;

import Organism.neurons.sensors.ValueSender;
import java.util.HashMap;

public class SynapseCollection {

  private HashMap<ValueSender, Float> synapses;

  public void newSynapse(ValueSender input, float weight) {
    this.synapses.put(input, weight);
  }

  public float getValue() {
    return (float) Math.tanh(
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
