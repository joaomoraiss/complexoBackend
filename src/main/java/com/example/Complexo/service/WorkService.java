package com.example.Complexo.service;
import com.example.Complexo.repository.*;
import com.example.Complexo.model.*;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class WorkService {

    @Autowired
    private WorkRepository workRepository;

    //criar trabalhos
    @Transactional
    public Work createWork(Work work) {
        return workRepository.save(work);
    }

    //buscar trabalhos pelo id
    @Transactional
    public Optional<Work> getWorkById(Long workId) {
        return workRepository.findById(workId);
    }

    //buscar todos os trabalhos
    @Transactional
    public List<Work> getAllWorks() {
        return workRepository.findAll();
    }

    //deletar o trabalho pelo id
    @Transactional
    public void deleteWorkById(Long workId) {
        if (!workRepository.existsById(workId)) {
            throw new RuntimeException("Deleção mal sucedida! Esse trabalho não foi encontrado ou inexiste");
        }
        workRepository.deleteById(workId);
    }

    //atualizar um trabalho
    @Transactional
    public Work updateWorkById(Long workId, Work workDetails) {
        return workRepository.findById(workId).map(work -> {
            workDetails.setWorkId(workId);
            return workRepository.save(workDetails);
        }).orElseThrow(() -> new RuntimeException("Atualização mal sucedida! Esse trabalho não foi encontrado ou não existe."));
    }
}
