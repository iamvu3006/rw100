package com.vti.service.impl;

import com.vti.dto.DiningTableDTO;
import com.vti.entity.DiningTable;
import com.vti.enums.TableStatus;
import com.vti.exception.BusinessException;
import com.vti.form.DiningTableCreateForm;
import com.vti.form.DiningTableUpdateForm;
import com.vti.repository.IDiningTableRepository;
import com.vti.service.IDiningTableService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DiningTableServiceImpl implements IDiningTableService {

    @Autowired
    private IDiningTableRepository diningTableRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<DiningTableDTO> findAll() {
        List<DiningTable> tables = diningTableRepository.findAll();
        return tables.stream()
                .map(table -> modelMapper.map(table, DiningTableDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public DiningTableDTO findById(Integer id) {
        DiningTable table = diningTableRepository.findById(id)
                .orElseThrow(() -> BusinessException.builder().message("Bàn ăn không tồn tại with ID: " + id).build());
        return modelMapper.map(table, DiningTableDTO.class);
    }

    @Override
    @Transactional
    public void create(DiningTableCreateForm form) {
        if (diningTableRepository.existsByTableNumber(form.getTableNumber())) {
            throw BusinessException.builder().message("Mã/Số bàn đã tồn tại").build();
        }

        DiningTable table = DiningTable.builder()
                .tableNumber(form.getTableNumber())
                .capacity(form.getCapacity())
                .status(form.getStatus() != null ? form.getStatus() : TableStatus.EMPTY)
                .build();

        diningTableRepository.save(table);
    }

    @Override
    @Transactional
    public void update(DiningTableUpdateForm form, Integer id) {
        DiningTable table = diningTableRepository.findById(id)
                .orElseThrow(() -> BusinessException.builder().message("Bàn ăn không tồn tại with ID: " + id).build());

        if (!table.getTableNumber().equals(form.getTableNumber()) && diningTableRepository.existsByTableNumber(form.getTableNumber())) {
            throw BusinessException.builder().message("Mã/Số bàn đã tồn tại").build();
        }

        table.setTableNumber(form.getTableNumber());
        table.setCapacity(form.getCapacity());
        if (form.getStatus() != null) {
            table.setStatus(form.getStatus());
        }

        diningTableRepository.save(table);
    }

    @Override
    @Transactional
    public void updateStatus(Integer id, TableStatus status) {
        DiningTable table = diningTableRepository.findById(id)
                .orElseThrow(() -> BusinessException.builder().message("Bàn ăn không tồn tại with ID: " + id).build());
        table.setStatus(status);
        diningTableRepository.save(table);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        if (!diningTableRepository.existsById(id)) {
            throw BusinessException.builder().message("Bàn ăn không tồn tại with ID: " + id).build();
        }
        diningTableRepository.deleteById(id);
    }
}
