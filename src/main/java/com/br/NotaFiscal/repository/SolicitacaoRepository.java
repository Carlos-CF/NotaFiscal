/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.br.NotaFiscal.repository;

import com.br.NotaFiscal.model.Empresa;
import com.br.NotaFiscal.model.Solicitacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author carlos.fernandes
 */

@Repository
public interface SolicitacaoRepository extends JpaRepository<Solicitacao, Long>{
    
    boolean existsByNumeroAndEmpresa(Long numero, Empresa empresa);
    
    boolean existsByNumeroAndEmpresaAndIdNot(Long numero, Empresa empresa, Long id);
}
