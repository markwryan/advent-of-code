package com.markwryan.adventofcode.day15;

enum Ingredient {
    FROSTING(4, -2, 0, 0, 5),
    CANDY(0, 5, -1, 0, 8),
    BUTTERSCOTCH(-1, 0, 5, 0, 6),
    SUGAR(0, 0, -2, 2, 1);

    private int capacity, durability, flavor, texture, calories;

    private Ingredient(int c, int d, int f, int t, int cal) {
        this.capacity = c;
        this.durability = d;
        this.flavor = f;
        this.texture = t;
        this.calories = cal;
    }

    public static int getScore(int[] amounts) {
        int tCapacity = FROSTING.capacity * amounts[0] + CANDY.capacity * amounts[1] +
                BUTTERSCOTCH.capacity * amounts[2] + SUGAR.capacity * amounts[3];

        int tDurability = FROSTING.durability * amounts[0] + CANDY.durability * amounts[1] +
                BUTTERSCOTCH.durability * amounts[2] + SUGAR.durability * amounts[3];

        int tFlavor = FROSTING.flavor * amounts[0] + CANDY.flavor * amounts[1] +
                BUTTERSCOTCH.flavor * amounts[2] + SUGAR.flavor * amounts[3];

        int tTexture = FROSTING.texture * amounts[0] + CANDY.texture * amounts[1] +
                BUTTERSCOTCH.texture * amounts[2] + SUGAR.texture * amounts[3];

        int tCalories = FROSTING.calories * amounts[0] + CANDY.calories * amounts[1] +
                BUTTERSCOTCH.calories * amounts[2] + SUGAR.calories * amounts[3];

        if (tCapacity < 0) {
            tCapacity = 0;
        }
        if (tDurability < 0) {
            tDurability = 0;
        }
        if (tFlavor < 0) {
            tFlavor = 0;
        }
        if (tTexture < 0) {
            tTexture = 0;
        }
        if (tCalories < 0) {
            tCalories = 0;
        }
        if (tCalories == 500) {

            return tCapacity * tDurability * tFlavor * tTexture;
        }
        return -1;
    }
}

/*
Frosting: capacity 4, durability -2, flavor 0, texture 0, calories 5
Candy: capacity 0, durability 5, flavor -1, texture 0, calories 8
Butterscotch: capacity -1, durability 0, flavor 5, texture 0, calories 6
Sugar: capacity 0, durability 0, flavor -2, texture 2, calories 1
 */

/**
 * Created by mark on 12/20/15.
 */
public class RecipeCalculator {
    public static void main(String[] args) {
        System.out.println("Best score: " + calculateBest());
    }

    public static int calculateBest() {
        int[] bestAmounts = {0, 0, 0, 0};
        int bestScore = 0;
        for (int frosting = 1; frosting <= 100; frosting++) {
            for (int candy = 1; candy <= 100; candy++) {
                for (int butterscotch = 1; butterscotch <= 100; butterscotch++) {
                    for (int sugar = 1; sugar <= 100; sugar++) {
                        int totalAmount = frosting + candy + butterscotch + sugar;
                        if (totalAmount == 100) {
                            int[] amounts = new int[5];
                            amounts[0] = frosting;
                            amounts[1] = candy;
                            amounts[2] = butterscotch;
                            amounts[3] = sugar;
                            int score = Ingredient.getScore(amounts);
                            if (score > bestScore) {
                                bestScore = score;
                                bestAmounts = amounts;
                            }
                        }
                    }
                }
            }
        }
        return bestScore;
    }
}














