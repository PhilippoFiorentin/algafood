package com.philippo.algafood.domain.exception;


public class GroupNotFoundException extends EntityNotFoundException {

    private static final long serialVersionUID = 1L;

    public GroupNotFoundException(String message){
        super(message);
    }

    public GroupNotFoundException(Long groupId){
        this(String.format("The group register with the code %d could not be found", groupId));
    }
}
