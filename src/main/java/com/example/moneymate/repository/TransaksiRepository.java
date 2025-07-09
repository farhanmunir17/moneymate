package com.example.moneymate.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.moneymate.model.Transaksi;

public interface TransaksiRepository extends JpaRepository<Transaksi, Long> {
}
