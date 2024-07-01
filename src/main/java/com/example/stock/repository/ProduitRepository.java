package com.example.stock.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.stock.pojo.Produit;

public interface ProduitRepository extends JpaRepository<Produit, Long> {

}
