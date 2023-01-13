package com.example.accidents.repository;

import com.example.accidents.model.AccidentType;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentTypeMem {

    private Map<Integer, AccidentType> accidentsType = new ConcurrentHashMap<>();

    private final AtomicInteger numberAccidentType = new AtomicInteger();

    public AccidentTypeMem() {
        accidentsType.put(numberAccidentType.incrementAndGet(), new AccidentType(numberAccidentType.get(), "Две машины"));
        accidentsType.put(numberAccidentType.incrementAndGet(), new AccidentType(numberAccidentType.get(), "Машина и человек"));
        accidentsType.put(numberAccidentType.incrementAndGet(), new AccidentType(numberAccidentType.get(), "Машина и велосипед"));
    }

    public List<AccidentType> getAll() {
        return accidentsType.values().stream().toList();
    }

    public Optional<AccidentType> findById(int id) {
        return Optional.ofNullable(accidentsType.get(id));
    }
}
