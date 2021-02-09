package gr.auth.geompokon;

import java.util.BitSet;

public class Individual {

    protected BitSet genes;

    public Individual(BitSet genes) {
        this.genes = genes;
    }

    public Individual(String binaryGenes) {
        genes = new BitSet(binaryGenes.length());

        for (int i=0; i<binaryGenes.length(); i++) {
            if (binaryGenes.charAt(i) == '1') {
                genes.set(i);
            }
        }
    }

    public BitSet getGenes() {
        return genes;
    }

    public String getBinaryGenesString() {
        return calculateBinaryGenes();
    }

    protected String calculateBinaryGenes() {
        StringBuilder result = new StringBuilder(genes.size());

        for (int i=0; i<genes.size(); i++) {
            System.out.println(genes.size());
            result.append( genes.get(i) ? 1 : 0);
        }

        return result.toString();
    }
}
