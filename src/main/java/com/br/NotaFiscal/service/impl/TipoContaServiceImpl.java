/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.br.NotaFiscal.service.impl;

import com.br.NotaFiscal.mapper.CustomObjectMapper;
import com.br.NotaFiscal.model.TipoConta;
import com.br.NotaFiscal.model.dto.TipoContaDTO;
import com.br.NotaFiscal.repository.TipoContaRepository;
import com.br.NotaFiscal.service.TipoContaService;
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
public class TipoContaServiceImpl implements TipoContaService {

    @Autowired
    private TipoContaRepository tipoContaRepository;

    @Autowired
    private CustomObjectMapper<TipoConta, TipoContaDTO> tipoContaMapper;

    @Override
    public ResponseEntity<Object> mudarStatus(Long idObjeto) throws Exception {

        TipoConta objeto = tipoContaRepository.findById(idObjeto)
                .orElseThrow(() -> new NoSuchElementException("O Tipo Conta com ID " + idObjeto + " não foi encontrado!"));

        objeto.setStatus(!objeto.isStatus());
        objeto.setUltimaAtualizacao(LocalDateTime.now());
        TipoConta objetoAtualizado = tipoContaRepository.saveAndFlush(objeto);

        TipoContaDTO objetoDTO = tipoContaMapper.converterParaDto(objetoAtualizado);

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(objetoDTO));
    }

    @Override
    public ResponseEntity<Object> cadastrar(TipoContaDTO objeto) throws Exception {

        if (tipoContaRepository.existsByNome(objeto.getNome())) {
            return ResponseEntity.badRequest().body(new ApiResponse<>("Não é possivel cadastrar o Tipo Conta, Já existe outra com o mesmo nome."));
        }

        TipoConta tipoConta = new TipoConta();
        tipoConta.setNome(objeto.getNome());
        tipoConta.setStatus(objeto.isStatus());

        TipoConta objetoCriado = tipoContaRepository.saveAndFlush(tipoConta);
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

        List<TipoContaDTO> tipoContaDTOs = tipoContaMapper.converterParaListaDeDtos(tipoContaRepository.findAll());
        if (tipoContaDTOs.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>("Não existe Tipo Conta cadastrado no Sistema"));
        }

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(tipoContaDTOs));
    }

    @Override
    public ResponseEntity<Object> editar(Long idObjeto, TipoContaDTO objeto) throws Exception {

        TipoConta dadosDto = tipoContaMapper.converterParaEntidade(objeto);
        TipoConta paraEditar = tipoContaRepository.findById(idObjeto)
                .orElseThrow(() -> new NoSuchElementException("A Tipo Conta com ID " + idObjeto + "não foi encontrada!"));
        
           if (tipoContaRepository.existsByNome(objeto.getNome())) {
            return ResponseEntity.badRequest().body(new ApiResponse<>("Não é possivel cadastrar o Tipo Conta, Já existe outra com o mesmo nome."));
        }

        dadosDto.setUltimaAtualizacao(LocalDateTime.now());
        dadosDto.setId(idObjeto);
        BeanUtils.copyProperties(dadosDto, paraEditar, "id");
        TipoConta objetoAtualizado = tipoContaRepository.saveAndFlush(dadosDto);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(tipoContaMapper.converterParaDto(objetoAtualizado)));
    }

    @Override
    public ResponseEntity<Object> listarPorId(Long idObjeto) throws Exception {

        TipoConta tipoConta = tipoContaRepository.findById(idObjeto)
                .orElseThrow(() -> new NoSuchElementException("O Tipo Conta com ID " + idObjeto + " não foi encontrada"));

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(tipoContaMapper.converterParaDto(tipoConta)));
    }

    @Override
    public ResponseEntity<Object> excluir(Long idObjeto) throws Exception {

        tipoContaRepository.findById(idObjeto)
                .orElseThrow(() -> new NoSuchElementException("O Tipo Conta com o ID " + idObjeto + " não foi encontrada!"));

        tipoContaRepository.deleteById(idObjeto);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>("O Tipo Conta foi excluída com sucesso."));
    }

}
