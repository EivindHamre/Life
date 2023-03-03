package Organism;

import Organism.dna.DNA;
import Organism.dna.Gene;
import Organism.neurons.InternalNeuron;
import Organism.neurons.actions.ActionNeuron;
import Organism.neurons.actions.Move;
// import Organism.neurons.actions.Talk;
import Organism.neurons.actions.Turn;
import Organism.neurons.sensors.Constant;
import Organism.neurons.sensors.Direction;
import Organism.neurons.sensors.Oscilllator;
import Organism.neurons.sensors.ValueSender;
import environment.GridPosition;
import java.util.ArrayList;
import java.util.Random;

public class Organism {

  static Random r = new Random();
  public GridPosition pos;
  public int direction;

  final DNA dna;
  final InternalNeuron[] internalNeurons;
  final ValueSender[] sensors;
  final ActionNeuron[] actions;

  private void createSynapse(Gene g) {
    boolean inputIsIneternalNeuron = g.inputIsInternalNeuron();
    boolean outputIsInternalNeuron = g.outputIsInternalNeuron();
    int inputID = g.getInputNeuronID();
    int outputID = g.getOutputId();
    float weight = g.getWeight();
    ValueSender input = inputIsIneternalNeuron
      ? internalNeurons[inputID % internalNeurons.length]
      : sensors[inputID % sensors.length];
    if (outputIsInternalNeuron) {
      internalNeurons[outputID % internalNeurons.length].newSynapse(
          input,
          weight
        );
    } else {
      actions[outputID % actions.length].setNewSynapse(input, weight);
    }
  }

  public Organism(DNA dna) {
    this.dna = dna;
    this.internalNeurons = dna.makeInternalNeurons();

    this.direction = Organism.r.nextInt(4);
    this.pos =
      new GridPosition(Organism.r.nextInt(128), Organism.r.nextInt(128));

    this.actions =
      new ActionNeuron[] {
        // new Talk(0.5f, "'ello"),
        new Move(.5f),
        new Turn(.5f, 1),
        new Turn(.5f, 3),
      };

    this.sensors =
      new ValueSender[] {
        new Oscilllator(),
        new Constant(),
        new Direction(this),
      };

    for (Gene g : dna.synapseGenes()) {
      createSynapse(g);
    }
  }

  public static Organism randomOrganism(int genes, int internalNeurons) {
    return new Organism(
      new DNA(
        new ArrayList<>() {
          {
            for (int i = 0; i < genes; i++) {
              add(new Gene(r.nextInt()));
            }
          }
        },
        (short) internalNeurons
      )
    );
  }

  public void step() {
    for (ValueSender s : this.sensors) s.UpdateValue();
    for (InternalNeuron n : this.internalNeurons) n.UpdateValue();
    for (ActionNeuron a : this.actions) a.doAction(this);
  }

  public Organism replicate() {
    return new Organism(this.dna.replicate());
  }
}
