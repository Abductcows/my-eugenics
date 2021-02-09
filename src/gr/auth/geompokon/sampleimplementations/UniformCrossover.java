package gr.auth.geompokon.sampleimplementations;

import gr.auth.geompokon.Individual;
import gr.auth.geompokon.interfaces.CrossoverMechanism;

import java.util.BitSet;
import java.util.Random;

public class UniformCrossover implements CrossoverMechanism {

    Random random = new Random();

    @Override
    public Individual[] crossOver(Individual o1, Individual o2) {
        BitSet gene1 = o1.getGenes(), gene2 = o2.getGenes();

        BitSet result1 = new BitSet(gene1.size());
        BitSet result2 = new BitSet(gene1.size());

        for (int i=0; i<result1.size(); i++) {
            if (random.nextDouble() < 0.5) {
                // 1 gets from 1 and 2 gets from 2
                if (gene1.get(i)) {
                    result1.set(i);
                }
                if (gene2.get(i)) {
                    result2.set(i);
                }
            } else {
                // 1 gets from 2 and 2 gets from 1
                if (gene1.get(i)) {
                    result2.set(i);
                }
                if (gene2.get(i)) {
                    result1.set(i);
                }
            }
        }

        return new Individual[]{ new Individual(result1), new Individual(result2) };
    }


}
