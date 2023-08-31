package dev.danvega.yamlreader.store;

import dev.danvega.yamlreader.product.Product;

import java.util.List;

public record Store(String title, String description, List<Product> products) {
}
