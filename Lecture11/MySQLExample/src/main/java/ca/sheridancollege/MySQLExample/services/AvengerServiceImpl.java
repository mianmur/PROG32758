package ca.sheridancollege.MySQLExample.services;

import ca.sheridancollege.MySQLExample.entity.Avenger;
import ca.sheridancollege.MySQLExample.repository.AvengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AvengerServiceImpl implements AvengerService {

    @Autowired
    private AvengerRepository avengerRepository;

    public AvengerServiceImpl(AvengerRepository avengerRepository) { this.avengerRepository=avengerRepository;}

    @Override
    public Avenger findById(int id) {
        return avengerRepository.findById(id);
    }
}
