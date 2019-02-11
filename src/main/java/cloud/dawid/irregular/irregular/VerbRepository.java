package cloud.dawid.irregular.irregular;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VerbRepository extends JpaRepository<Verb, Integer> {
    Optional<Verb> findVerbById(int verb);
}
