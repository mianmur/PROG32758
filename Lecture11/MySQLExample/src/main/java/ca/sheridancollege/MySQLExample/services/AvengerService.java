package ca.sheridancollege.MySQLExample.services;

import ca.sheridancollege.MySQLExample.entity.Avenger;

public interface AvengerService {
    Avenger findById(int id);
}
