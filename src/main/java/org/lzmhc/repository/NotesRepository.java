package org.lzmhc.repository;

import org.lzmhc.entity.ToDo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface NotesRepository extends CrudRepository<ToDo,String> {
    /**
     * 查询所有数据
     */
    List<ToDo> findAll();
    /**
     * 插入或更新数据
     */
    ToDo save(ToDo entity);
    /**
     * 删除
     */
    void deleteById(String id);
}
