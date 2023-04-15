package br.com.dicasdeumdev.cursotestes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.dicasdeumdev.cursotestes.domain.People;

@Repository
public interface UserRepository extends JpaRepository<People, Integer> {

}
