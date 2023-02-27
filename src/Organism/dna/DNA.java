package Organism.dna;

import Organism.neurons.InternalNeuron;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public record DNA(List<Gene> genes, short numberOfInternalNeurons) {
  public DNA replicate() {
    Random r = new Random();
    List<Gene> newGenes = new ArrayList<>();
    for (Gene g : genes) {
      if (r.nextInt(1000) == 0) continue;
      if (r.nextInt(1000) == 0) newGenes.add(g.replicate());
      newGenes.add(g.replicate());
    }
    return new DNA(newGenes, numberOfInternalNeurons);
  }

  public List<Gene> synapseGenes() {
    return genes;
  }

  public InternalNeuron[] makeInternalNeurons() {
    return Stream
      .iterate(0, x -> x + 1)
      .limit(numberOfInternalNeurons)
      .map(x -> new InternalNeuron())
      .toArray(InternalNeuron[]::new);
  }
}
