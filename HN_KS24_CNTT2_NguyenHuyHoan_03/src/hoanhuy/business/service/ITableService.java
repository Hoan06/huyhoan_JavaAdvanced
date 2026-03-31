package hoanhuy.business.service;

import hoanhuy.model.entity.Account;
import hoanhuy.model.entity.Table;

import java.sql.SQLException;
import java.util.List;

public interface ITableService {
    List<Table> getAllTables();
    void insertTable();
    void updateTable();
    void deleteTable();
    void returnTable(int idTable);
    Table findTableById(int id);
    List<Table> getTableByPage(int page , int pagesize);
    int countTable();
}
