package cloud.dawid.irregular.irregular;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VerbService {

    private final VerbRepository verbRepository;

    @Autowired
    public VerbService(VerbRepository verbRepository) {
        this.verbRepository = verbRepository;
    }

    public List<Verb> listVerbs(){
        return verbRepository.findAll();
    }

    public Optional<Verb> getOneByID(int verb) {
        return verbRepository.findVerbById(verb);
    }
}
