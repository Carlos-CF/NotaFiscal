/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.br.NotaFiscal.service.impl;

import com.br.NotaFiscal.mapper.CustomObjectMapper;
import com.br.NotaFiscal.model.Usuario;
import com.br.NotaFiscal.model.dto.UsuarioDTO;
import com.br.NotaFiscal.repository.UsuarioRepository;
import com.br.NotaFiscal.service.UsuarioService;
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
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CustomObjectMapper<Usuario, UsuarioDTO> usuarioMapper;

    @Override
    public ResponseEntity<Object> cadastrar(UsuarioDTO objeto) throws Exception {

        if (usuarioRepository.existsByEmail(objeto.getEmail())) {
            return ResponseEntity.badRequest().body(new ApiResponse<>("Não é possivel cadastrar o Usuário, Já existe outro email com o mesmo nome."));
        }

        if(objeto.getSenha().length() < 8){
            return ResponseEntity.badRequest().body(new ApiResponse<>("A senha precisa ter pelomenos 8 digitos"));
        }
            
        Usuario usuario = new Usuario();
        usuario.setNomeCompleto(objeto.getNomeCompleto());
        usuario.setEmail(objeto.getEmail());
        usuario.setSenha(objeto.getSenha());

        Usuario objetoCriado = usuarioRepository.saveAndFlush(usuario);
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

        List<UsuarioDTO> usuarioDTOs = usuarioMapper.converterParaListaDeDtos(usuarioRepository.findAll());
        if (usuarioDTOs.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>("Não existe Usuários cadastrado no Sistema"));
        }

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(usuarioDTOs));
    }

    @Override
    public ResponseEntity<Object> editar(Long idObjeto, UsuarioDTO objeto) throws Exception {

        Usuario dadosDto = usuarioMapper.converterParaEntidade(objeto);
        Usuario paraEditar = usuarioRepository.findById(idObjeto)
                .orElseThrow(() -> new NoSuchElementException("O Usuário com ID " + idObjeto + "não foi encontrada!"));

        if (usuarioRepository.existsByEmail(objeto.getEmail())) {
            return ResponseEntity.badRequest().body(new ApiResponse<>("Não é possivel cadastrar o Usuário, Já existe outro Usuário com o mesmo email."));
        }
        
        if(objeto.getSenha().length() < 8){
            return ResponseEntity.badRequest().body(new ApiResponse<>("A senha precisa ter pelomenos 8 digitos"));
        }

        dadosDto.setUltimaAtualizacao(LocalDateTime.now());
        dadosDto.setId(idObjeto);
        BeanUtils.copyProperties(dadosDto, paraEditar, "id");
        Usuario objetoAtualizado = usuarioRepository.saveAndFlush(dadosDto);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(usuarioMapper.converterParaDto(objetoAtualizado)));
    }

    @Override
    public ResponseEntity<Object> listarPorId(Long idObjeto) throws Exception {

        Usuario usuario = usuarioRepository.findById(idObjeto)
                .orElseThrow(() -> new NoSuchElementException("O Usuário com ID " + idObjeto + " não foi encontrada"));

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(usuarioMapper.converterParaDto(usuario)));
    }

    @Override
    public ResponseEntity<Object> excluir(Long idObjeto) throws Exception {

        usuarioRepository.findById(idObjeto)
                .orElseThrow(() -> new NoSuchElementException("O Usuário com o ID " + idObjeto + " não foi encontrada!"));

        usuarioRepository.deleteById(idObjeto);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>("O Usuário foi excluída com sucesso."));
    }

}
