package com.example.accidents.repository;

import com.example.accidents.model.Rule;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class RuleMem {
    private Map<Integer, Rule> rules = new ConcurrentHashMap<>();

    private final AtomicInteger numberRule = new AtomicInteger();

    public RuleMem() {
        rules.put(numberRule.incrementAndGet(), new Rule(numberRule.get(), "Статья_1"));
        rules.put(numberRule.incrementAndGet(), new Rule(numberRule.get(), "Статья_2"));
        rules.put(numberRule.incrementAndGet(), new Rule(numberRule.get(), "Статья_3"));
    }

    public List<Rule> getAll() {
        return rules.values().stream().toList();
    }

    public Set<Rule> getByIds(String[] ids) {
        List<Integer> idsRule = Arrays.stream(ids).map(Integer::parseInt).toList();
        return rules.entrySet().stream().filter(rule ->
                idsRule.contains(rule.getKey())
        ).map(Map.Entry::getValue).collect(Collectors.toSet());
    }
    public Optional<Rule> findById(int id) {
        return Optional.ofNullable(rules.get(id));
    }
}
