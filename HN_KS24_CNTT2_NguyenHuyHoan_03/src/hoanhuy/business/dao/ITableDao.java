package hoanhuy.business.dao;

import hoanhuy.model.entity.Table;

import java.util.List;

public interface ITableDao {
    List<Table> findAllTable();
    boolean insert(Table table);
    boolean update(int id , int numberUpdate);
    boolean updateIsEmpty(int idTable);
    boolean delete(int id);
    boolean returnTable(int idTable);
    Table findById(int id);
    List<Table> findByPage(int page, int pageSize);
    int countTable();
}
