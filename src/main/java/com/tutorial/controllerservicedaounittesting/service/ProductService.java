package com.tutorial.controllerservicedaounittesting.service;



import com.tutorial.controllerservicedaounittesting.Exception.ProductAlreadyExistsException;
import com.tutorial.controllerservicedaounittesting.entities.Product;

import java.util.List;

public interface ProductService {

    Product addProduct (Product product) throws ProductAlreadyExistsException;

    List<Product> getAllProducts ();

    Product getProductByid (int id);

    Product deleteProductById (int id);



}
