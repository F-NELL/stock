package com.example.stock.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.stock.pojo.Produit;
import com.example.stock.service.ProduitService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RequestMapping("/api/produit")
@RestController
public class ProduitController {

    @Autowired
    private ProduitService produitService;

    @PostMapping
    public void createProduit(@Valid @RequestBody Produit produit) {
        this.produitService.createProduit(produit);
    }

    @GetMapping
    public List<Produit> getAllProduits() {
        return this.produitService.getAllProduit();
    }

    @DeleteMapping("/{id}")
    public void deleteProduitById(@PathVariable("id") Long id) {
        this.produitService.deleteProduitById(id);
    }
}
