package com.example.accidents.service;

import com.example.accidents.model.Accident;
import com.example.accidents.model.AccidentType;
import com.example.accidents.repository.AccidentMem;
import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@ThreadSafe
@AllArgsConstructor
public class AccidentService {
    private final AccidentMem accidentMem;
    private final AccidentTypeService accidentTypeService;

    public List<Accident> getAll() {
        return accidentMem.getAll();
    }

    public void create(Accident accident) {
        AccidentType accidentType = getType(accident);
        if (accidentType != null) {
            accident.setType(accidentType);
        }
        accidentMem.save(accident);
    }

    public void update(Accident accident) {
        AccidentType accidentType = getType(accident);
        if (accidentType != null) {
            accident.setType(accidentType);
        }
        accidentMem.update(accident);
    }

    public Optional<Accident> findById(int id) {
        return accidentMem.findById(id);
    }

    private AccidentType getType(Accident accident) {
        return accidentTypeService.findById(accident.getType().getId());
    }

}
