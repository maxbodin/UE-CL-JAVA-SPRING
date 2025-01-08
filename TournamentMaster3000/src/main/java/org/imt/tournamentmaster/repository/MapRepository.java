package org.imt.tournamentmaster.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class MapRepository<T> implements Repository<T> {
    private Map<Long, T> map;

    public abstract Map<Long, T> initData();

    @Override
    public T findById(long id) {
        if (map == null ){
            map = initData();
        }

        return map.get(id);
    }

    @Override
    public List<T> findAll() {
        if (map == null ){
            map = initData();
        }

        return new ArrayList<>(map.values());
    }

}
