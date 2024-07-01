package com.example.stock.service;

import java.util.List;

import com.example.stock.pojo.Produit;

import jakarta.validation.Valid;

public interface ProduitService {
    List<Produit> getAllProduit();

    void createProduit(@Valid Produit produit);

    void updateProduit(Long id, Produit produit);

    Produit getProduitById(Long id);

    void deleteProduitById(Long id);

    void updateProduitQuantity(Long produitId, Integer quantite);
}
