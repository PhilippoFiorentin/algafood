package com.philippo.algafood.api.assembler;

import com.philippo.algafood.api.model.input.StateInput;
import com.philippo.algafood.domain.model.State;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StateInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public State toDomainObject(StateInput stateInput) {
        return modelMapper.map(stateInput, State.class);
    }

    public void copyToDomainObject(StateInput stateInput, State state) {
        modelMapper.map(stateInput,state);
    }
}
