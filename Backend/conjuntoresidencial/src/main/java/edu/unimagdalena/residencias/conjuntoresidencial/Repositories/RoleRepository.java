package edu.unimagdalena.residencias.conjuntoresidencial.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.unimagdalena.residencias.conjuntoresidencial.entities.ERole;
import edu.unimagdalena.residencias.conjuntoresidencial.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long>{
    Optional<Role> findByName(ERole name);
    
}
