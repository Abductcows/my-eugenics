package gr.auth.geompokon.sampleimplementations;

import gr.auth.geompokon.Individual;
import gr.auth.geompokon.interfaces.FitnessFunction;

import java.util.Arrays;

public class DivisibilityFitness implements FitnessFunction {

    private final int[] divisors;

    public DivisibilityFitness(int[] divisors) {
        this.divisors = divisors;
    }

    @Override
    public double getScore(Individual individual) {

        int intValue = Integer.parseInt(individual.getBinaryGenesString(), 2);

        return Arrays.stream(divisors)
                .filter(divisor -> intValue % divisor == 0) // perfect divisors
                .count();
    }
}
