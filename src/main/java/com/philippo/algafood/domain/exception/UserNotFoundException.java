package com.philippo.algafood.domain.exception;

public class UserNotFoundException extends EntityNotFoundException{

    private static final long serialVersionUID = 1L;

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(Long userId){
        this(String.format("the user register with the code %d could not be found", userId));
    }
}
