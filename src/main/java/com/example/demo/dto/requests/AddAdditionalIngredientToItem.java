package com.example.demo.dto.requests;

import jakarta.validation.constraints.NotNull;

public record AddAdditionalIngredientToItem(@NotNull int amount, @NotNull long ingredient_id) {
}
