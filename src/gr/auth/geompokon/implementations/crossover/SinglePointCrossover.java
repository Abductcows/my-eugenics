package gr.auth.geompokon.implementations.crossover;

import gr.auth.geompokon.Individual;
import gr.auth.geompokon.interfaces.CrossoverMechanism;

public class SinglePointCrossover implements CrossoverMechanism {

    protected final int pointIndex;

    public SinglePointCrossover(int geneLength, double relativePoint) {
        this(geneLength, (int) Math.round(Math.floor( geneLength * relativePoint)));
    }

    /**
     * Absolute index of the gene array
     */
    public SinglePointCrossover(int geneLength, int absolutePoint) {
        if (absolutePoint < 0 || absolutePoint >= geneLength) {
            throw new IndexOutOfBoundsException("gene index");
        }
        pointIndex = absolutePoint;
    }

    @Override
    public Individual[] crossOver(Individual o1, Individual o2) {
        byte[] gene1 = o1.getGenes(), gene2 = o2.getGenes();

        byte[] result1 = new byte[gene1.length];
        byte[] result2 = new byte[gene2.length];

        int i=0;
        while (i<pointIndex) {
            result1[i] = gene1[i];
            result2[i] = gene2[i];
            i++;
        }
        while (i<result1.length) {
            result1[i] = gene2[i];
            result2[i] = gene1[i];
            i++;
        }

        return new Individual[] { new Individual(result1), new Individual(result2) };
    }
}
