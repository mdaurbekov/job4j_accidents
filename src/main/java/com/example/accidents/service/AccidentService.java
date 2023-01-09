package com.example.accidents.service;

import com.example.accidents.model.Accident;
import com.example.accidents.repository.AccidentMem;
import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@ThreadSafe
@AllArgsConstructor
public class AccidentService {
    private final AccidentMem accidentMem;

    public List<Accident> getAll() {
        return accidentMem.getAll();
    }


}