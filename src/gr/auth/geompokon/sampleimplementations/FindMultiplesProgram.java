package gr.auth.geompokon.sampleimplementations;

import gr.auth.geompokon.run.GeneticAlgorithmProgram;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class FindMultiplesProgram extends GeneticAlgorithmProgram<Integer> {

    private static final int[] staticFactors = { 4 };
    private final int[] factors;

    public static void main(String[] args) {
        List<Integer> startingPopulation = getMyIntPopulation();
        printResults(startingPopulation);

        GeneticAlgorithmProgram<Integer> program = new FindMultiplesProgram(staticFactors);
        program.setMaxIter(9999);
        List<Integer> result = program.run(startingPopulation);
        printResults(result);
    }

    public FindMultiplesProgram(int[] factors) {
        this.factors = staticFactors;
    }

    @Override
    protected void instantiateMechanisms() {
        this.bijection = new IntegerToBitsBijection();
        this.crossoverMechanism = new SinglePointCrossover(0.2);
        this.mutationMechanism = new BitStringMutation(0);
        this.selectionMechanism = new ElitismSelection(0.1);
        this.fitnessFunction = new DivisibilityFitness(factors);
    }

    private static List<Integer> getMyIntPopulation() {
        int populationSize = 40;
        List<Integer> result = new ArrayList<>(populationSize);
        int factorsProduct = Arrays.stream(staticFactors).reduce(1, (product, i)->product*i);
        Random random = new Random();

        // half are not multiples of the product of the factors
        for (int i=0; i<populationSize/2; i++) {
            int notMultiple = factorsProduct * random.nextInt( Integer.MAX_VALUE / factorsProduct - 1)
                    + random.nextInt(factorsProduct - 1) + 1;
            result.add(notMultiple);
        }

        // half are
        for (int i=0; i<populationSize/2; i++) {
            int multiple = factorsProduct * random.nextInt( Integer.MAX_VALUE / factorsProduct - 1);

            result.add(multiple);
        }

        return result;
    }

    private static void printResults(List<Integer> population) {
        int factorsProduct = Arrays.stream(staticFactors).reduce(1, (product, i)->product*i);
        long multiplesCount = population.stream().filter(i -> i % factorsProduct == 0).count();

        System.out.println("Total number: " + population.size());
        System.out.println("Number of multiples: " + multiplesCount);
    }
}
