package app.runfinder.web.restControllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import app.runfinder.domain.dto.RunGroupGetDTO;
import app.runfinder.domain.dto.RunGroupPostPutDTO;
import app.runfinder.domain.entities.RunGroup;
import app.runfinder.domain.entities.Zipcode;
import app.runfinder.domain.repositories.RunGroupRepository;
import app.runfinder.domain.repositories.ZipcodeRepository;
import app.runfinder.domain.repositories.AppUserRepository;

@RestController // @RestController annotates this class as a rest controller class
@RequestMapping("/api") // @RequestMapping sets a default part of the request path
public class RunGroupRestController {

    // Injects repositories to the controller
    private final RunGroupRepository runGroupRepository;
    private final ZipcodeRepository zipcodeRepository;
    private final AppUserRepository appUserRepository;

    public RunGroupRestController(RunGroupRepository runGroupRepository, ZipcodeRepository zipcodeRepository,
            AppUserRepository appUserRepository) {
        this.runGroupRepository = runGroupRepository;
        this.zipcodeRepository = zipcodeRepository;
        this.appUserRepository = appUserRepository;
    }

    @GetMapping("/rungroups")
    public ResponseEntity<List<RunGroupGetDTO>> getAllRunGroups() {
        List<RunGroup> allRunGroups = new ArrayList<RunGroup>();
        runGroupRepository.findAll().forEach(allRunGroups::add);

        List<RunGroupGetDTO> runGroupDTOs = allRunGroups.stream()
                .filter(runGroup -> runGroup.getDeletedAt() == null)
                .map(runGroup -> runGroup.toGetDTO())
                .collect(Collectors.toList());

        return ResponseEntity.ok(runGroupDTOs);
    }

    @GetMapping("/rungroups/{runGroupId}")
    public ResponseEntity<RunGroupGetDTO> getRunGroupById(@PathVariable("runGroupId") Long runGroupId) {
        Optional<RunGroup> runGroup = runGroupRepository.findById(runGroupId);
        if (!runGroup.isPresent() || runGroup.get().getDeletedAt() != null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Run group not found");
        }

        RunGroupGetDTO runGroupDTO = runGroup.get().toGetDTO();
        return ResponseEntity.ok(runGroupDTO);
    }

    @PostMapping("/rungroups")
    public ResponseEntity<RunGroupGetDTO> addNewRunGroup(@Valid @RequestBody RunGroupPostPutDTO runGroupDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Optional<Zipcode> runGroupZipcode = zipcodeRepository.findById(runGroupDTO.zipcode());
        if (!runGroupZipcode.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Zipcode is not valid");
        }

        RunGroup newRunGroup = new RunGroup(
                runGroupDTO.runGroupName(),
                runGroupDTO.runStartDate(),
                runGroupDTO.runStartTime(),
                runGroupDTO.startAddress(),
                zipcodeRepository.findById(runGroupDTO.zipcode()).get(),
                null,
                appUserRepository.findByUsername(username));
        runGroupRepository.save(newRunGroup);

        RunGroupGetDTO responseDTO = newRunGroup.toGetDTO();
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @PutMapping("/rungroups/{runGroupId}")
    public ResponseEntity<RunGroupGetDTO> editRunGroup(@Valid @RequestBody RunGroupPostPutDTO runGroupDTO,
            @PathVariable("runGroupId") Long runGroupId) {
        Optional<RunGroup> runGroup = runGroupRepository.findById(runGroupId);
        if (!runGroup.isPresent() || runGroup.get().getDeletedAt() != null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Run group not found");
        }

        Optional<Zipcode> runGroupZipcode = zipcodeRepository.findById(runGroupDTO.zipcode());
        if (!runGroupZipcode.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Zipcode is not valid");
        }

        RunGroup editedRunGroup = runGroup.get();
        editedRunGroup.setRunGroupName(runGroupDTO.runGroupName());
        editedRunGroup.setRunStartDate(runGroupDTO.runStartDate());
        editedRunGroup.setRunStartTime(runGroupDTO.runStartTime());
        editedRunGroup.setStartAddress(runGroupDTO.startAddress());
        editedRunGroup.setZipcode(zipcodeRepository.findByZipcode(runGroupDTO.zipcode()));
        runGroupRepository.save(editedRunGroup);

        RunGroupGetDTO responseDTO = editedRunGroup.toGetDTO();
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @DeleteMapping("/rungroups/{runGroupId}")
    public ResponseEntity<String> deleteRunGroup(@PathVariable("runGroupId") Long runGroupId) {
        Optional<RunGroup> runGroup = runGroupRepository.findById(runGroupId);
        if (!runGroup.isPresent() || runGroup.get().getDeletedAt() != null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Run group not found");
        }

        runGroup.get().delete();
        runGroupRepository.save(runGroup.get());

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
