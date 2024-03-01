package com.example.demo.coffeShop.Repositori;

import com.example.demo.coffeShop.Entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreRepositori extends JpaRepository<Store , String> {

    List<Store> findAllByIsActiveTrue();

}
