package com.example.accidents.service;

import com.example.accidents.model.Accident;
import com.example.accidents.model.AccidentType;
import com.example.accidents.model.Rule;
import com.example.accidents.repository.AccidentMem;
import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@ThreadSafe
@AllArgsConstructor
public class AccidentService {
    private final AccidentMem accidentMem;
    private final AccidentTypeService accidentTypeService;
    private final RuleService ruleService;

    public List<Accident> getAll() {
        return accidentMem.getAll();
    }

    public boolean create(Accident accident) {
        Optional<AccidentType> accidentType = getType(accident);
        Set<Rule> rules = accident.getRules();
        if (accidentType.isEmpty() || rules.isEmpty()) {
            return false;
        }
        accident.setType(accidentType.get());
        accident.setRules(rules);
        accidentMem.save(accident);
        return true;
    }

    public boolean update(Accident accident) {
        Optional<AccidentType> accidentType = getType(accident);
        if (accidentType.isEmpty()) {
            return false;
        }
        accident.setType(accidentType.get());
        accidentMem.update(accident);
        return true;
    }

    public Optional<Accident> findById(int id) {
        return accidentMem.findById(id);
    }

    private Optional<AccidentType> getType(Accident accident) {
        return accidentTypeService.findById(accident.getType().getId());
    }

}
