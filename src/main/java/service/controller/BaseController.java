package service.controller;

public abstract class BaseController {
    Integer startPage = 0;
    Integer pageLimit = 20;
    String sortDefault = "id";
    String orderDefault = "asc";
}
