package platform.repo;

import org.springframework.data.repository.CrudRepository;
import platform.models.Code;


public interface CodeRepository extends CrudRepository<Code, Integer> {
}
