package ru.irlix_moviesearch.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.irlix_moviesearch.dto.CriticDTO;
import ru.irlix_moviesearch.model.Critic;
import ru.irlix_moviesearch.repository.CriticRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CriticService {

    private final CriticRepository criticRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    public List<CriticDTO> getAll() {
        List<CriticDTO> criticDTOs = new ArrayList<>();
                criticRepository
                .findAll()
                .forEach(critic -> criticDTOs.add(modelMapper.map(critic, CriticDTO.class)));
        return criticDTOs;
    }

    public CriticDTO getById(Long id) {
        Critic critic = criticRepository.findById(id).orElse(null);
        return convertToDTO(critic);
    }

    public CriticDTO create (Critic critic) {
        Critic critic1 = criticRepository.save(critic);
       return convertToDTO(critic1);
    }

    public Critic update(Long id, Critic critic) {
        Critic updateCritic = criticRepository.findById(id).orElse(null);
        updateCritic.setFirst_name(critic.getFirst_name());
        updateCritic.setLast_name(critic.getLast_name());
        updateCritic.setInfo(critic.getInfo());
        return criticRepository.save(updateCritic);
    }

    public void delete(Long id) {
        criticRepository.deleteById(id);
    }

    public CriticDTO convertToDTO(Critic critic) {
        return modelMapper.map(critic, CriticDTO.class);
    }

}
