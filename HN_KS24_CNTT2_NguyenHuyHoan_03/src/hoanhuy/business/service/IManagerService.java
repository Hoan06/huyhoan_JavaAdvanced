package hoanhuy.business.service;

import hoanhuy.model.entity.Order;
import hoanhuy.model.entity.OrderItem;
import hoanhuy.utils.Color;
import hoanhuy.validate.Validator;

import java.util.List;
import java.util.Scanner;

public interface IManagerService {
    void browseOrderItems();
    void banAccount();
    void payOrder();
}
