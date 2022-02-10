package shangrila.council.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import shangrila.council.app.model.ResidentUser;

/* Adding @Repository will allow to have CRUD operations in residentUser entity */
//@Repository
public interface ResidentUserRepository extends JpaRepository<ResidentUser, Integer>{
	ResidentUser findByEmail(String email);
}



