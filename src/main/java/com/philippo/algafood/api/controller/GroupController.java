package com.philippo.algafood.api.controller;

import com.philippo.algafood.api.assembler.GroupInputDisassembler;
import com.philippo.algafood.api.assembler.GroupModelAssembler;
import com.philippo.algafood.api.openapi.controller.GroupControllerOpenApi;
import com.philippo.algafood.api.model.GroupModel;
import com.philippo.algafood.api.model.input.GroupInput;
import com.philippo.algafood.domain.exception.BusinessException;
import com.philippo.algafood.domain.exception.GroupNotFoundException;
import com.philippo.algafood.domain.model.Group;
import com.philippo.algafood.domain.repository.GroupRepository;
import com.philippo.algafood.domain.service.RegisterGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/groups", produces = MediaType.APPLICATION_JSON_VALUE)
public class GroupController implements GroupControllerOpenApi {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private RegisterGroupService registerGroup;

    @Autowired
    private GroupModelAssembler groupModelAssembler;

    @Autowired
    private GroupInputDisassembler groupInputDisassembler;

    @GetMapping
    public List<GroupModel> list() {
        List<Group> groups = groupRepository.findAll();
        return groupModelAssembler.toCollectionModel(groups);
    }

    @GetMapping("/{groupId}")
    public GroupModel find(@PathVariable Long groupId){
        Group group = registerGroup.findOrFail(groupId);
        return groupModelAssembler.toModel(group);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GroupModel add(@RequestBody @Valid GroupInput groupInput){
        try {
            Group group = groupInputDisassembler.toDomainObject(groupInput);
            return groupModelAssembler.toModel(registerGroup.save(group));
        }catch (GroupNotFoundException e){
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @PutMapping("/{groupId}")
    public GroupModel update(@PathVariable Long groupId, @RequestBody @Valid GroupInput groupInput){
        try {
            Group currentGroup = registerGroup.findOrFail(groupId);
            groupInputDisassembler.copyToDomainObject(groupInput, currentGroup);
            return groupModelAssembler.toModel(registerGroup.save(currentGroup));
        }catch (GroupNotFoundException e){
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @DeleteMapping("/{groupId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long groupId){
        registerGroup.delete(groupId);

    }
}
