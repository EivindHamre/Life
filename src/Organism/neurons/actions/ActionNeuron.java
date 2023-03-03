package Organism.neurons.actions;

import Organism.Organism;
import Organism.neurons.SynapseCollection;
import Organism.neurons.sensors.ValueSender;

public class ActionNeuron {

  private final SynapseCollection synapses;
  private final float threshold;

  public ActionNeuron(float threshold) {
    this.synapses = new SynapseCollection();
    this.threshold = threshold;
  }

  public void doAction(Organism organism) {}

  public void setNewSynapse(ValueSender input, float weight) {
    this.synapses.newSynapse(input, weight);
  }

  public boolean weightedSynapseSumGreaterThanThreshold (){
    return synapses.getValue() > this.threshold;
  }
}
