package com.philippo.algafood.api.v1.assembler;

import com.philippo.algafood.api.v1.AlgaLinks;
import com.philippo.algafood.api.v1.controller.StateController;
import com.philippo.algafood.api.v1.model.StateModel;
import com.philippo.algafood.domain.model.State;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class StateModelAssembler extends RepresentationModelAssemblerSupport<State, StateModel> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AlgaLinks algaLinks;

    public StateModelAssembler() {
        super(StateController.class, StateModel.class);
    }

    public StateModel toModel(State state){
        StateModel stateModel = createModelWithId(state.getId(), state);

        modelMapper.map(state, stateModel);

        stateModel.add(algaLinks.linkToStates("states"));

        return stateModel;
    }

    @Override
    public CollectionModel<StateModel> toCollectionModel(Iterable<? extends State> entities) {
        return super.toCollectionModel(entities)
                .add(algaLinks.linkToStates());
    }
}
