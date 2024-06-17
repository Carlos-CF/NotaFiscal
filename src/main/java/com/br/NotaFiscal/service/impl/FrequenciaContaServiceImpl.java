/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.br.NotaFiscal.service.impl;

import com.br.NotaFiscal.mapper.CustomObjectMapper;
import com.br.NotaFiscal.model.FrequenciaConta;
import com.br.NotaFiscal.model.dto.FrequenciaContaDTO;
import com.br.NotaFiscal.repository.FrequenciaContaRepository;
import com.br.NotaFiscal.service.FrequenciaContaService;
import com.br.NotaFiscal.service.util.ApiResponse;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 *
 * @author carlos.fernandes
 */
@Service
public class FrequenciaContaServiceImpl implements FrequenciaContaService {

    @Autowired
    private FrequenciaContaRepository frequenciaContaRepository;

    @Autowired
    private CustomObjectMapper<FrequenciaConta, FrequenciaContaDTO> frequenciaContaMapper;

    @Override
    public ResponseEntity<Object> mudarStatus(Long idObjeto) throws Exception {
        FrequenciaConta objeto = frequenciaContaRepository.findById(idObjeto)
                .orElseThrow(() -> new NoSuchElementException("A FrequenciaConta com ID " + idObjeto + " não foi encontrada"));

        objeto.setStatus(!objeto.isStatus());
        objeto.setUltimaAtualizacao(LocalDateTime.now());
        FrequenciaConta objetoAtualizado = frequenciaContaRepository.saveAndFlush(objeto);

        FrequenciaContaDTO objetoDTO = frequenciaContaMapper.converterParaDto(objetoAtualizado);

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(objetoDTO));

    }

    @Override
    public ResponseEntity<Object> cadastrar(FrequenciaContaDTO objeto) throws Exception {
        if (frequenciaContaRepository.existsByNome(objeto.getNome())) {
            return ResponseEntity.badRequest().body(new ApiResponse<>("Não é possivel cadastrar a FrequenciaConta, Já existe outra com o mesmo nome."));
        }

        FrequenciaConta frequenciaConta = new FrequenciaConta();
        frequenciaConta.setNome(objeto.getNome());
        frequenciaConta.setStatus(objeto.isStatus());

        FrequenciaConta objetoCriado = frequenciaContaRepository.saveAndFlush(frequenciaConta);
        return ResponseEntity.created(
                ServletUriComponentsBuilder
                        .fromCurrentContextPath()
                        .path("/{id}")
                        .buildAndExpand(objetoCriado.getId())
                        .toUri())
                .build();
    }

    @Override
    public ResponseEntity<Object> listar() throws Exception {

        List<FrequenciaContaDTO> frequenciaContaDTOs = frequenciaContaMapper.converterParaListaDeDtos(frequenciaContaRepository.findAll());
        if (frequenciaContaDTOs.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>("Não existe FrequenciaConta cadastradas no Sistemas"));
        }

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(frequenciaContaDTOs));
    }

    @Override
    public ResponseEntity<Object> editar(Long idObjeto, FrequenciaContaDTO objeto) throws Exception {

        FrequenciaConta dadosDto = frequenciaContaMapper.converterParaEntidade(objeto);
        FrequenciaConta paraEditar = frequenciaContaRepository.findById(idObjeto)
                .orElseThrow(() -> new NoSuchElementException("A FrequenciaConta com ID " + idObjeto + " não foi encontrada"));

        if (frequenciaContaRepository.existsByNome(objeto.getNome())) {
            return ResponseEntity.badRequest().body(new ApiResponse<>("Não é possivel cadastrar a FrequenciaConta, Já existe outra com o mesmo nome."));
        }

        dadosDto.setUltimaAtualizacao(LocalDateTime.now());
        dadosDto.setId(idObjeto);
        BeanUtils.copyProperties(dadosDto, paraEditar, "id");
        FrequenciaConta objetoAtualizado = frequenciaContaRepository.saveAndFlush(dadosDto);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(frequenciaContaMapper.converterParaDto(objetoAtualizado)));
    }

    @Override
    public ResponseEntity<Object> listarPorId(Long idObjeto) throws Exception {

        FrequenciaConta frequenciaConta = frequenciaContaRepository.findById(idObjeto)
                .orElseThrow(() -> new NoSuchElementException("A FrequenciaConta com ID " + idObjeto + " não foi encontrado"));

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(frequenciaContaMapper.converterParaDto(frequenciaConta)));
    }

    @Override
    public ResponseEntity<Object> excluir(Long idObjeto) throws Exception {

        frequenciaContaRepository.findById(idObjeto)
                .orElseThrow(() -> new NoSuchElementException("A FrequenciaConta com ID " + idObjeto + " não foi encontrado"));
        
        frequenciaContaRepository.deleteById(idObjeto);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>("A FrequenciaConta foi excluída com sucesso."));
    }

}
