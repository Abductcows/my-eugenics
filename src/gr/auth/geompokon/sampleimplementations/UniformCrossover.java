package gr.auth.geompokon.sampleimplementations;

import gr.auth.geompokon.Individual;
import gr.auth.geompokon.interfaces.CrossoverMechanism;

import java.util.Random;

public class UniformCrossover implements CrossoverMechanism {

    protected Random random = new Random();

    @Override
    public Individual[] crossOver(Individual o1, Individual o2) {
        byte[] gene1 = o1.getGenes(), gene2 = o2.getGenes();

        if (gene1.length != gene2.length) {
            throw new RuntimeException("Mismatching gene lengths: " + gene1.length + " - " + gene2.length);
        }

        byte[] result1 = new byte[gene1.length];
        byte[] result2 = new byte[gene2.length];

        for (int i=0; i<result1.length; i++) {
            if (random.nextDouble() < 0.5) {
                // 1 gets from 1 and 2 gets from 2
                if (gene1[i] == 1) {
                    result1[i] = 1;
                }
                if (gene2[i] == 1) {
                    result2[i] = 1;
                }
            } else {
                // 1 gets from 2 and 2 gets from 1
                if (gene1[i] == 1) {
                    result2[i] = 1;
                }
                if (gene2[i] == 1) {
                    result1[i] = 1;
                }
            }
        }

        return new Individual[]{ new Individual(result1), new Individual(result2) };
    }


}
