package Organism.neurons;

import Organism.neurons.sensors.ValueSender;
import java.util.HashMap;
import java.util.Map;

public class SynapseCollection {

  private final Map<ValueSender, Float> synapses;

  public SynapseCollection(){
    this.synapses = new HashMap<>();
  }

  public void newSynapse(ValueSender input, float weight) {
    this.synapses.put(input, weight);
  }

  public float getValue() {
    if(synapses.size() == 0) return 0;
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
