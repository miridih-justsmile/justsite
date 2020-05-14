package comm.justsmile.justsite.springboot.web.global.domain.visitor.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findByLastVisit(Long visitorIdx);
}
