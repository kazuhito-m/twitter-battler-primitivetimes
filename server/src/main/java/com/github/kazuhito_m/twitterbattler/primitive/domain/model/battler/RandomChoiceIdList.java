package com.github.kazuhito_m.twitterbattler.primitive.domain.model.battler;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;

public class RandomChoiceIdList {
    private final List<Long> values;

    public RandomChoiceIdList(List<Long> values) {
        this.values = values;
    }

    public List<Long> choice(int size) {
        Set<Integer> indexes = new HashSet<>();
        while (indexes.size() < size) {
            indexes.add((int) Math.floor(Math.random() * (double) values.size()));
        }
        return indexes.stream()
                .map(values::get)
                .collect(toList());
    }
}
