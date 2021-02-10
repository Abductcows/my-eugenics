package gr.auth.geompokon.run;

import gr.auth.geompokon.Individual;
import gr.auth.geompokon.Population;
import gr.auth.geompokon.generification.TypeToIndividualBijection;
import gr.auth.geompokon.interfaces.CrossoverMechanism;
import gr.auth.geompokon.interfaces.FitnessFunction;
import gr.auth.geompokon.interfaces.MutationMechanism;
import gr.auth.geompokon.interfaces.SelectionMechanism;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public abstract class GeneticAlgorithmProgram<T> {

    private int maxIter;
    protected TypeToIndividualBijection<T> bijection;
    protected FitnessFunction fitnessFunction;
    protected SelectionMechanism selectionMechanism;
    protected CrossoverMechanism crossoverMechanism;
    protected MutationMechanism mutationMechanism;

    public GeneticAlgorithmProgram(int maxIter) {
        this.maxIter = maxIter;
    }

    public GeneticAlgorithmProgram() {
        this(100);
    }

    public List<T> run(Population startingPopulation) {
        instantiateMechanisms();
        Population population = startingPopulation;

        for (int i = 0; i< maxIter; i++) {
            population = runIteration(population);
        }

        return population.getPopulation().stream().map(bijection::toType).collect(Collectors.toList());
    }

    public List<T> run() {
        Population startingPopulation = getStartingPopulation();
        return run(startingPopulation);
    }

    public List<T> run(Collection<T> startingPopulation) {
        instantiateMechanisms();
        List<Individual> individuals = startingPopulation.stream()
                .map(bijection::toIndividual)
                .collect(Collectors.toList());

        return run(new Population(individuals));
    }

    protected Population runIteration(Population population) {

        Population selectedPopulation = selectionMechanism.selectForCrossover(population, fitnessFunction);
        Population notSelected = new Population(population.getPopulation());
        notSelected.difference(selectedPopulation);

        Population resultPopulation = new Population();
        // add not selected individuals
        resultPopulation.union(notSelected);

        // preform crossover
        Individual[] individuals = selectedPopulation.getPopulation().toArray(new Individual[0]);
        for (int i=0; i<individuals.length/2; i++) {
            Individual i1 = individuals[2*i];
            Individual i2 = individuals[2*i + 1];

            // add children after mutation
            Individual[] children = crossoverMechanism.crossOver(i1, i2);
            for (Individual child : children) {
                mutationMechanism.mutate(child);
            }
            resultPopulation.addAll(children);
        }

        // add single individual if any
        if (individuals.length % 2 == 1) {
            resultPopulation.add(individuals[individuals.length-1]);
        }

        return resultPopulation;
    }

    protected Population getStartingPopulation() {
        return new Population();
    }

    protected abstract void instantiateMechanisms();

    public void setMaxIter(int maxIter) {
        this.maxIter = maxIter;
    }

    public int getMaxIter() {
        return maxIter;
    }
}
