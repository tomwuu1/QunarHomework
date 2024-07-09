package org.example.implement;

import org.example.api.CategoryExtractor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimpleCategoryExtractorTest {

    private static final String VALID_URI = "/category/item";
    private static final String EXPECTED_CATEGORY = "category";
    private static final String INVALID_URI = "/unknown";
    private static final String EMPTY_URI = "";
    private static final String UNKNOWN_CATEGORY = "unknown";

    private CategoryExtractor categoryExtractor;

    @BeforeEach
    public void setUp() {
        categoryExtractor = new SimpleCategoryExtractor();
    }

    @Test
    public void testExtractCategory_ValidUri() {
        String result = categoryExtractor.extractCategory(VALID_URI);
        assertEquals(EXPECTED_CATEGORY, result);
    }

    @Test
    public void testExtractCategory_InvalidUri() {
        String result = categoryExtractor.extractCategory(INVALID_URI);
        assertEquals(UNKNOWN_CATEGORY, result);
    }

    @Test
    public void testExtractCategory_EmptyUri() {
        String result = categoryExtractor.extractCategory(EMPTY_URI);
        assertEquals(UNKNOWN_CATEGORY, result);
    }
}