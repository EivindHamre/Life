package Organism;

import Organism.dna.DNA;
import Organism.dna.Gene;
import Organism.neurons.InternalNeuron;
import Organism.neurons.actions.ActionNeuron;
import Organism.neurons.actions.Talk;
import Organism.neurons.sensors.Oscilllator;
import Organism.neurons.sensors.ValueSender;

public class Organism {

  final DNA dna;
  final InternalNeuron[] internalNeurons;
  final ValueSender[] sensors = { new Oscilllator() };
  final ActionNeuron[] actions = { new Talk(0.5f, "'ello") };

  private void createSynapse(
    boolean inputIsIneternalNeuron,
    boolean outputIsInternalNeuron,
    int inputID,
    int outputID,
    float weight
  ) {
    ValueSender input = inputIsIneternalNeuron
      ? internalNeurons[inputID]
      : sensors[inputID];
    if (outputIsInternalNeuron) {
      internalNeurons[outputID].setNewSynapse(input, weight);
    }
    else{
      actions[outputID].setNewSynapse(input, weight);
    }
  }

  public Organism(DNA dna) {
    this.dna = dna;
    this.internalNeurons = dna.makeInternalNeurons();
    System.out.println(dna.synapseGenes());
    for (Gene g : dna.synapseGenes()) {
      createSynapse(
        g.inputIsInternalNeuron(),
        g.outputIsInternalNeuron(),
        g.getInputNeuronID(),
        g.getOutputId(),
        g.getWeight()
      );
    }
  }

  public void step(){
    for(ValueSender s : this.sensors) s.UpdateValue();
    for(InternalNeuron n : this.internalNeurons) n.UpdateValue();
    for(ActionNeuron a : this.actions) a.doAction(this);
  }
}
