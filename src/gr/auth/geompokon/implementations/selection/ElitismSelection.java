package gr.auth.geompokon.implementations.selection;

import gr.auth.geompokon.Individual;
import gr.auth.geompokon.Population;
import gr.auth.geompokon.interfaces.FitnessFunction;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ElitismSelection extends RouletteSelection {

    protected double normalizedPercentage;

    public ElitismSelection(double normalizedPercentage) {
        this.normalizedPercentage = normalizedPercentage;
    }

    @Override
    public Population selectForCrossover(Population population, FitnessFunction fitnessFunction) {

        // get individuals list from population and sort by score descending
        Individual[] individuals = population.getPopulation().toArray(new Individual[0]);
        Arrays.sort(individuals, Comparator.comparingDouble(fitnessFunction::getScore).reversed());

        // calculate index of last person to be included in the elites
        int lastIndex = (int) Math.round(Math.floor(normalizedPercentage * individuals.length));

        // add elites into a population
        List<Individual> toPass = Arrays.asList(Arrays.copyOfRange(individuals, 0, lastIndex + 1));
        Population elites = new Population(toPass);

        // remove the elites from the original population
        population.difference(elites);

        // run roulette selection on the rest
        Population rest = super.selectForCrossover(population, fitnessFunction);

        @SuppressWarnings("unused")
        // return their union
        Population result = elites;
        result.union(rest);

        return result;
    }
}
