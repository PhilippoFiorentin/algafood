package com.philippo.algafood.api.V1.assembler;

import com.philippo.algafood.api.V1.AlgaLinks;
import com.philippo.algafood.api.V1.controller.StateController;
import com.philippo.algafood.api.V1.model.StateModel;
import com.philippo.algafood.core.security.AlgaSecurity;
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

    @Autowired
    private AlgaSecurity algaSecurity;

    public StateModelAssembler() {
        super(StateController.class, StateModel.class);
    }

    public StateModel toModel(State state){
        StateModel stateModel = createModelWithId(state.getId(), state);

        modelMapper.map(state, stateModel);

        if (algaSecurity.canConsultStates()) {
            stateModel.add(algaLinks.linkToStates("states"));
        }

        return stateModel;
    }

    @Override
    public CollectionModel<StateModel> toCollectionModel(Iterable<? extends State> entities) {
        CollectionModel<StateModel> collectionModel = super.toCollectionModel(entities);

        if (algaSecurity.canConsultStates()) {
            collectionModel.add(algaLinks.linkToStates());
        }

        return collectionModel;
    }
}
