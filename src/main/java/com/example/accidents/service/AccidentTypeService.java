package com.example.accidents.service;

import com.example.accidents.model.AccidentType;
import com.example.accidents.repository.AccidentTypeMem;
import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@ThreadSafe
@AllArgsConstructor
public class AccidentTypeService {
    private final AccidentTypeMem accidentTypeMem;

    public List<AccidentType> getAll() {
        return accidentTypeMem.getAll();
    }

    public Optional<AccidentType> findById(int id) {
        return accidentTypeMem.findById(id);
    }

}
