package dev.danvega.yamlreader.product;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;

@Repository
public class ProductRepository {

    private final JdbcClient jdbcClient;

    public ProductRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<Product> findAll() {
        return jdbcClient.sql("SELECT id,name,price FROM Product")
                .query(Product.class)
                .list();
    }

    public void create(Product product) {
        var updated = jdbcClient.sql("INSERT INTO Product(id,name,price) values(?,?,?)")
                .params(List.of(product.id(),product.name(),product.price()))
                .update();

        Assert.state(updated == 1, "Failed to create product " + product.name());
    }

}
