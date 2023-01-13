package com.example.accidents.service;

import com.example.accidents.model.AccidentType;
import com.example.accidents.model.Rule;
import com.example.accidents.repository.RuleMem;
import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@ThreadSafe
@AllArgsConstructor
public class RuleService {
    private final RuleMem ruleMem;
    public List<Rule> getAll() {
        return ruleMem.getAll();
    }

    public Set<Rule> getByIds(String[] ids) {
        return ruleMem.getByIds(ids);
    }

    public Optional<Rule> findById(int id) {
        return ruleMem.findById(id);
    }

}
