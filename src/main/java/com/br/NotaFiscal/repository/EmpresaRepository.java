/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.br.NotaFiscal.repository;

import com.br.NotaFiscal.model.Empresa;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author carlos.fernandes
 */

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long>{
    
    public boolean existsByNome(String nome);
    
    public boolean existsByCodigo(Long codigo);
    
    public Optional<Empresa> findByNome(String nome);
}
