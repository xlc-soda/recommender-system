package service.controller;

public abstract class BaseController {
    private Integer startPage = 1;
    private Integer pageLimit = 10;
    private String sortDefault = "Id";
    private String orderDefault = "asc";
}
