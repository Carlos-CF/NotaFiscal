/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.br.NotaFiscal.service;

import com.br.NotaFiscal.model.dto.GrupoDTO;
import com.br.NotaFiscal.service.util.CrudService;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author carlos.fernandes
 */
public interface GrupoService extends CrudService<GrupoDTO>{
    
    ResponseEntity<Object> mudarStatus(Long idObjeto) throws Exception;
}
