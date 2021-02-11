package gr.auth.geompokon.implementations.crossover;

import gr.auth.geompokon.Individual;
import gr.auth.geompokon.interfaces.CrossoverMechanism;

public class SinglePointCrossover implements CrossoverMechanism {

    private enum PointMode {
        RELATIVE, ABSOLUTE
    }

    private final PointMode mode;
    double pointValue;
    int point;

    /**
     * Should be a number between 0 and 1 with 0 being the first gene
     * and 1 the last
     */
    public SinglePointCrossover(double relativePoint) {
        mode = PointMode.RELATIVE;
        pointValue = relativePoint;
    }

    /**
     * Absolute index of the gene array
     */
    public SinglePointCrossover(int absolutePoint) {
        mode = PointMode.ABSOLUTE;
        point = absolutePoint;
    }

    public SinglePointCrossover() {
        this(0.5);
    }

    @Override
    public Individual[] crossOver(Individual o1, Individual o2) {
        byte[] gene1 = o1.getGenes(), gene2 = o2.getGenes();

        byte[] result1 = new byte[gene1.length];
        byte[] result2 = new byte[gene2.length];

        long crossoverPoint;
        if (mode == PointMode.RELATIVE) {
            crossoverPoint = Math.round(pointValue * gene1.length);
        } else {
            crossoverPoint = point;
        }

        int i=0;
        while (i<crossoverPoint) {
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
