package com.philippo.algafood.api.v1.assembler;

import com.philippo.algafood.api.v1.model.input.UserInput;
import com.philippo.algafood.domain.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public User toDomainObject(UserInput userInput){
        return modelMapper.map(userInput, User.class);
    }

    public void copyToDomainObject(UserInput userInput, User user){
        modelMapper.map(userInput, user);
    }
}
