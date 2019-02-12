package cloud.dawid.irregular.irregular;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VerbRepository extends JpaRepository<Verb, Integer> {
    Optional<Verb> findVerbById(int verb);

//    @Query("SELECT v FROM Verb v WHERE v.first_in_unit = '1' AND v.unit = ?1")
//    Optional<Verb> getFirstInThisUnit(int unit);

    Optional<Verb> getVerbByFirstInUnitAndUnit(boolean one, int unit);
}
