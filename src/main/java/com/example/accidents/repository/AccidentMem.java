package com.example.accidents.repository;

import com.example.accidents.model.Accident;
import com.example.accidents.model.AccidentType;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {
    private Map<Integer, Accident> accidents = new ConcurrentHashMap<>();

    private final AtomicInteger numberAccident = new AtomicInteger();

    public AccidentMem() {
        accidents.put(numberAccident.incrementAndGet(), new Accident(numberAccident.get(),
                "name1", "text1", "address1", new AccidentType(1, "Две машины")));
        accidents.put(numberAccident.incrementAndGet(), new Accident(numberAccident.get(),
                "name2", "text2", "address2", new AccidentType(1, "Две машины")));
        accidents.put(numberAccident.incrementAndGet(), new Accident(numberAccident.get(),
                "name3", "text3", "address3", new AccidentType(2, "Машина и человек")));
        accidents.put(numberAccident.incrementAndGet(), new Accident(numberAccident.get(),
                "name4", "text4", "address4", new AccidentType(3, "Машина и велосипед")));
    }

    public List<Accident> getAll() {
        return accidents.values().stream().toList();
    }

    public void save(Accident accident) {
        accident.setId(numberAccident.incrementAndGet());
        accidents.put(accident.getId(), accident);
    }

    public void update(Accident accident) {
        int id = accident.getId();
        accidents.replace(id, accidents.get(id), accident);
    }

    public Optional<Accident> findById(int id) {
        return Optional.of(accidents.get(id));
    }
}
