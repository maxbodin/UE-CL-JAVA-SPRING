package org.imt.tournamentmaster.repository;

import java.util.List;

public interface Repository<T> {

    T findById(long id);

    List<T> findAll();
}
