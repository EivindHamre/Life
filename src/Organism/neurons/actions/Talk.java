package Organism.neurons.actions;

import Organism.Organism;
import Organism.neurons.sensors.ValueSender;

public class Talk implements ActionNeuron {

  final float threshold;
  final String text;

  public void setNewSynapse(ValueSender input, float weight) {
    
  }

  public Talk(float threshold, String text) {
    this.threshold = threshold;
    this.text = text;
  }

  @Override
  public void doAction(Organism organism) {
    if (this.synapses.getValue() < this.threshold) return;
    System.out.print(organism);
    System.out.println(" says \"" + this.text + "\"");
  }
}
