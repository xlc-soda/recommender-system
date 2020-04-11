package service.service;

import dao.CartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private JsonService jsonService;

    // TODO: 增删改查
}
