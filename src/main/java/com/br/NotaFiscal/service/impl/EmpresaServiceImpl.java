/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.br.NotaFiscal.service.impl;

import com.br.NotaFiscal.mapper.CustomObjectMapper;
import com.br.NotaFiscal.model.Empresa;
import com.br.NotaFiscal.model.dto.EmpresaDTO;
import com.br.NotaFiscal.repository.EmpresaRepository;
import com.br.NotaFiscal.service.EmpresaService;
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
public class EmpresaServiceImpl implements EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private CustomObjectMapper<Empresa, EmpresaDTO> empresaMapper;

    @Override
    public ResponseEntity<Object> mudarStatus(Long idObjeto) throws Exception {
        Empresa objeto = empresaRepository.findById(idObjeto)
                .orElseThrow(() -> new NoSuchElementException("A Empresa com ID " + idObjeto + " não foi encontrada!"));

        objeto.setStatus(!objeto.isStatus());
        objeto.setUltimaAtualizacao(LocalDateTime.now());
        Empresa objetoAtualizado = empresaRepository.saveAndFlush(objeto);

        //Converte o objeto atualizado para DTO
        EmpresaDTO objetoDTO = empresaMapper.converterParaDto(objetoAtualizado);

        //Retorna a resposta com o objeto atualizado
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(objetoDTO));
    }

    @Override
    public ResponseEntity<Object> cadastrar(EmpresaDTO objeto) throws Exception {

        if (empresaRepository.existsByNome(objeto.getNome())) {
            return ResponseEntity.badRequest().body(new ApiResponse<>("Não é possivel cadastrar a Empresa. Já existe outra Empresa com o mesmo nome."));
        }

        if (empresaRepository.existsByCodigo(objeto.getCodigo())) {
            return ResponseEntity.badRequest().body(new ApiResponse<>("Não é possivel cadastrar a Empresa. Já existe outro Codigo com o mesmo numero."));
        }

        Empresa empresa = new Empresa();
        empresa.setCodigo(objeto.getCodigo());
        empresa.setNome(objeto.getNome());
        empresa.setStatus(objeto.isStatus());

        Empresa objetoCriado = empresaRepository.saveAndFlush(empresa);
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
        
        List<EmpresaDTO> empresaDTOs = empresaMapper.converterParaListaDeDtos(empresaRepository.findAll());
        if(empresaDTOs.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>("Não existe Empresa cadastradas no Sistemas"));
        }
        
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(empresaDTOs));
    }

    @Override
    public ResponseEntity<Object> editar(Long idObjeto, EmpresaDTO objeto) throws Exception {
        
        Empresa dadosDto = empresaMapper.converterParaEntidade(objeto);
        Empresa paraEditar = empresaRepository.findById(idObjeto)
                .orElseThrow(()-> new NoSuchElementException("A Empresa com ID " + idObjeto + "não foi encontrada!"));
        
        dadosDto.setUltimaAtualizacao(LocalDateTime.now());
        dadosDto.setId(idObjeto);
        dadosDto.setCodigo(objeto.getCodigo());
        BeanUtils.copyProperties(dadosDto, paraEditar, "id");
        Empresa objetoAtualizado = empresaRepository.saveAndFlush(dadosDto);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(empresaMapper.converterParaDto(objetoAtualizado)));
    }

    @Override
    public ResponseEntity<Object> listarPorId(Long idObjeto) throws Exception {
       
        Empresa empresa = empresaRepository.findById(idObjeto)
                .orElseThrow(()-> new NoSuchElementException("A Empresa com ID " + idObjeto + " não foi encontrada!"));
        
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(empresaMapper.converterParaDto(empresa)));
    }

    @Override
    public ResponseEntity<Object> excluir(Long idObjeto) throws Exception {
       
        empresaRepository.findById(idObjeto)
                .orElseThrow(()-> new NoSuchElementException("A Empresa com ID " + idObjeto + " não foi encontrada!"));
        
        empresaRepository.deleteById(idObjeto);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>("A Empresa foi excluída com sucesso."));
    }

}
