package com.example.Complexo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Complexo.service.*;
import com.example.Complexo.model.*;

@RestController
@RequestMapping("/trabalhos")
public class WorkController {

    @Autowired
    private WorkService workService;

    @PostMapping
    public ResponseEntity<Work> createWork(@RequestBody Work work) {
        Work newWork = workService.createWork(work);
        return ResponseEntity.status(201).body(newWork);
    }

    @GetMapping("/{workId}")
    public ResponseEntity<Work> getWorkById(@PathVariable Long workId) {
        Optional<Work> work = workService.getWorkById(workId);
        return work.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Work> getAllWorks() {
        return workService.getAllWorks();
    }

    @DeleteMapping("/{workId}")
    public ResponseEntity<Void> deleteWorkById(@PathVariable Long workId) {
        workService.deleteWorkById(workId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{workId}")
    public ResponseEntity<Work> updateWorkById(@PathVariable Long workId, @RequestBody Work workDetails) {
        Work updatedWork = workService.updateWorkById(workId, workDetails);
        return ResponseEntity.ok(updatedWork);
    }
}
