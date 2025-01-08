package org.imt.tournamentmaster.repository;

import org.imt.tournamentmaster.model.resultat.Resultat;

import java.util.List;

public interface Repository<T> {

    T findById(long id);

    List<T> findAll();
}
