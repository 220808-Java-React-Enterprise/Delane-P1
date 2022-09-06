
package com.revature.reimbapi.daos;

import java.util.List;
import java.util.UUID;

public interface CrudDAO<T> {

    void save(T obj);
    List<T> getAll();
    void update(T obj);
    void delete(UUID id);
}
