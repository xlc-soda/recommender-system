package service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import service.service.CartService;

@RestController
public class CartController extends BaseController {

    @Autowired
    private CartService cartService;

    // TODO: 增删改查
}
