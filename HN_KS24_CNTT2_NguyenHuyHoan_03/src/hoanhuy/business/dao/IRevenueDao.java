package hoanhuy.business.dao;

import hoanhuy.model.dto.Revenue;

import java.util.List;

public interface IRevenueDao {
    List<Revenue> getRevenuesByMonth(int month,int year);
    List<Revenue> getRevenuesByYear(int year);
}
