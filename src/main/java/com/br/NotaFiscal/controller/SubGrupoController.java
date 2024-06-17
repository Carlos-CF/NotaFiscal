/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.br.NotaFiscal.controller;

import com.br.NotaFiscal.model.dto.SubGrupoDTO;
import com.br.NotaFiscal.service.SubGrupoService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author carlos.fernandes
 */

@RestController
@RequestMapping(value = "/subGrupo")
public class SubGrupoController {
    
    @Autowired
    private SubGrupoService subGrupoService;
    
     @PostMapping
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Criado (Created)", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = SubGrupoDTO.class))}),
        @ApiResponse(responseCode = "401", description = "Não Autorizado (Unauthorized)"),
        @ApiResponse(responseCode = "404", description = "Não Encontrado (Not Found)"),
        @ApiResponse(responseCode = "500", description = "Erro interno (Internal Server Error)")
    })
    public ResponseEntity<Object> cadastrar(@RequestBody SubGrupoDTO objeto) throws Exception {
        return subGrupoService.cadastrar(objeto);
    }
    
     @GetMapping
     @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Criado (Created)", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = SubGrupoDTO.class))}),
        @ApiResponse(responseCode = "401", description = "Não Autorizado (Unauthorized)"),
        @ApiResponse(responseCode = "404", description = "Não Encontrado (Not Found)"),
        @ApiResponse(responseCode = "500", description = "Erro interno (Internal Server Error)")
    })
    public ResponseEntity<Object> listar() throws Exception {
        return subGrupoService.listar();
    }

     @GetMapping("/{idObjeto}")
     @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Criado (Created)", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = SubGrupoDTO.class))}),
        @ApiResponse(responseCode = "401", description = "Não Autorizado (Unauthorized)"),
        @ApiResponse(responseCode = "404", description = "Não Encontrado (Not Found)"),
        @ApiResponse(responseCode = "500", description = "Erro interno (Internal Server Error)")
    })
    public ResponseEntity<Object> listarPorId(@PathVariable Long idObjeto) throws Exception {
        return subGrupoService.listarPorId(idObjeto);
    }

    @PutMapping("/{idObjeto}")
     @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Criado (Created)", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = SubGrupoDTO.class))}),
        @ApiResponse(responseCode = "401", description = "Não Autorizado (Unauthorized)"),
        @ApiResponse(responseCode = "404", description = "Não Encontrado (Not Found)"),
        @ApiResponse(responseCode = "500", description = "Erro interno (Internal Server Error)")
    })
    public ResponseEntity<Object> editar(@PathVariable Long idObjeto, @RequestBody SubGrupoDTO objeto) throws Exception {
        return subGrupoService.editar(idObjeto, objeto);
    }

    @DeleteMapping("/{idObjeto}")
     @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Criado (Created)", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = SubGrupoDTO.class))}),
        @ApiResponse(responseCode = "401", description = "Não Autorizado (Unauthorized)"),
        @ApiResponse(responseCode = "404", description = "Não Encontrado (Not Found)"),
        @ApiResponse(responseCode = "500", description = "Erro interno (Internal Server Error)")
    })
    public ResponseEntity<Object> excluir(@PathVariable Long idObjeto) throws Exception {
        return subGrupoService.excluir(idObjeto);
    }

    @PatchMapping("/{idObjeto}/status")
     @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Criado (Created)", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = SubGrupoDTO.class))}),
        @ApiResponse(responseCode = "401", description = "Não Autorizado (Unauthorized)"),
        @ApiResponse(responseCode = "404", description = "Não Encontrado (Not Found)"),
        @ApiResponse(responseCode = "500", description = "Erro interno (Internal Server Error)")
    })
    public ResponseEntity<Object> mudarStatus(@PathVariable Long idObjeto) throws Exception {
        return subGrupoService.mudarStatus(idObjeto);
    }
}
