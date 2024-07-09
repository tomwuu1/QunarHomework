package org.example.implement;

import org.example.api.CategoryExtractor;

/**
 * @author 93253
 */
public class SimpleCategoryExtractor implements CategoryExtractor {

    private static final String URI_DELIMITER = "/";
    private static final int CATEGORY_POSITION = 1;
    @Override
    public String extractCategory(String uri) {
        String[] parts = uri.split(URI_DELIMITER);
        if (parts.length > CATEGORY_POSITION) {
            return parts[CATEGORY_POSITION];
        }
        return "unknown";
    }
}