package com.example.randomgenerationservicesequences.models;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class SequenceNumber {

    private final RandomSimpleNumber randomSimpleNumber = new RandomSimpleNumber(20000);
    private final List<List<Integer>> generatedSequenceList;

    public SequenceNumber(int size) {
        this.generatedSequenceList = generatedSequence(size);
    }

    private List<List<Integer>> generatedSequence(int size) {
        List<List<Integer>> newList = new LinkedList<>();
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            List<Integer> list = new LinkedList<>();
            for (int j = 0; j < size; j++) {
                int x = random.nextInt(200);
                list.add(randomSimpleNumber.getArrays().get(x));
                randomSimpleNumber.getArrays().remove(x);
            }
            newList.add(list);
        }
        return newList;
    }

    public List<List<Integer>> getGeneratedSequenceList() {
        return generatedSequenceList;
    }

    public List<List<Integer>> getGeneratedSequenceListOf(int size) {
        return generatedSequence(size);
    }
}
