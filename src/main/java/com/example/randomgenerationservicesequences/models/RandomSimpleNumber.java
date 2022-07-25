package com.example.randomgenerationservicesequences.models;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomSimpleNumber {
    private final List<Integer> arrays;

    public RandomSimpleNumber(int size) {
        this.arrays = createSimpleArrayOfNumber(size);
    }

    public List<Integer> getArrays() {
        return arrays;
    }

    private List<Integer> createSimpleArrayOfNumber(int size) {
        return IntStream.range(2, size)
                .filter(this::isPrime).boxed()
                .collect(Collectors.toList());
    }

    private boolean isPrime(int number) {
        return IntStream.iterate(2, i -> i * i <= number, i -> i + 1).noneMatch(i -> number % i == 0);
    }
}
