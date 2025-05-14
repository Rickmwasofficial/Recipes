package com.example.recipes.data

import com.example.recipes.R
import com.example.recipes.model.IngredientsModel
import com.example.recipes.model.MealModel

object MealData {
    private val allMeals = listOf(
        MealModel(
            img1 = R.drawable.pancakes,
            img2 = R.drawable.pancakes_alt,
            name = R.string.pancakes_name,
            shortDesc = R.string.pancakes_short_desc,
            about = R.string.pancakes_about,
            time = R.string.pancakes_time,
            servings = R.string.pancakes_servings,
            category = R.string.breakfast_category,
            ingredients = listOf(
                IngredientsModel(R.string.flour, R.string.flour_qty, R.drawable.flour_img),
                IngredientsModel(R.string.eggs, R.string.eggs_qty, R.drawable.eggs_img),
                IngredientsModel(R.string.milk, R.string.milk_qty, R.drawable.milk_img),
                IngredientsModel(R.string.butter, R.string.butter_qty, R.drawable.butter_img),
                IngredientsModel(R.string.syrup, R.string.syrup_qty, R.drawable.syrup_img)
            )
        ),
        MealModel(
            img1 = R.drawable.pasta_dish,
            img2 = R.drawable.pasta_dish_alt,
            name = R.string.pasta_name,
            shortDesc = R.string.pasta_short_desc,
            about = R.string.pasta_about,
            time = R.string.pasta_time,
            servings = R.string.pasta_servings,
            category = R.string.main_course_category,
            ingredients = listOf(
                IngredientsModel(R.string.pasta, R.string.pasta_qty, R.drawable.pasta_img),
                IngredientsModel(R.string.tomato_sauce, R.string.tomato_sauce_qty, R.drawable.tomato_sauce_img),
                IngredientsModel(R.string.garlic, R.string.garlic_qty, R.drawable.garlic_img),
                IngredientsModel(R.string.olive_oil, R.string.olive_oil_qty, R.drawable.olive_oil_img)
            )
        ),
        MealModel(
            img1 = R.drawable.vegan_bowl,
            img2 = R.drawable.vegan_bowl_alt,
            name = R.string.vegan_bowl_name,
            shortDesc = R.string.vegan_bowl_short_desc,
            about = R.string.vegan_bowl_about,
            time = R.string.vegan_bowl_time,
            servings = R.string.vegan_bowl_servings,
            category = R.string.salads_category,
            ingredients = listOf(
                IngredientsModel(R.string.quinoa, R.string.quinoa_qty, R.drawable.quinoa_img),
                IngredientsModel(R.string.chickpeas, R.string.chickpeas_qty, R.drawable.chickpeas_img),
                IngredientsModel(R.string.spinach, R.string.spinach_qty, R.drawable.spinach_img),
                IngredientsModel(R.string.avocado, R.string.avocado_qty, R.drawable.avocado_img)
            )
        ),
        MealModel(
            img1 = R.drawable.grilled_cheese,
            img2 = R.drawable.grilled_cheese_alt,
            name = R.string.grilled_cheese_name,
            shortDesc = R.string.grilled_cheese_short_desc,
            about = R.string.grilled_cheese_about,
            time = R.string.grilled_cheese_time,
            servings = R.string.grilled_cheese_servings,
            category = R.string.snacks_category,
            ingredients = listOf(
                IngredientsModel(R.string.bread, R.string.bread_qty, R.drawable.bread_img),
                IngredientsModel(R.string.cheese, R.string.cheese_qty, R.drawable.cheese_img),
                IngredientsModel(R.string.butter, R.string.butter_qty, R.drawable.butter_img)
            )
        ),
        MealModel(
            img1 = R.drawable.chicken_curry,
            img2 = R.drawable.chicken_curry_alt,
            name = R.string.chicken_curry_name,
            shortDesc = R.string.chicken_curry_short_desc,
            about = R.string.chicken_curry_about,
            time = R.string.chicken_curry_time,
            servings = R.string.chicken_curry_servings,
            category = R.string.main_course_category,
            ingredients = listOf(
                IngredientsModel(R.string.chicken, R.string.chicken_qty, R.drawable.chicken_img),
                IngredientsModel(R.string.garlic, R.string.garlic_qty, R.drawable.garlic_img),
                IngredientsModel(R.string.onion, R.string.onion_qty, R.drawable.onion_img),
                IngredientsModel(R.string.tomato_sauce, R.string.tomato_sauce_qty, R.drawable.tomato_sauce_img),
                IngredientsModel(R.string.olive_oil, R.string.olive_oil_qty, R.drawable.olive_oil_img),
                IngredientsModel(R.string.curry_powder, R.string.curry_powder_qty, R.drawable.curry_powder_img)
            )
        ),
        MealModel(
            img1 = R.drawable.veggie_burger,
            img2 = R.drawable.veggie_burger_alt,
            name = R.string.veggie_burger_name,
            shortDesc = R.string.veggie_burger_short_desc,
            about = R.string.veggie_burger_about,
            time = R.string.veggie_burger_time,
            servings = R.string.veggie_burger_servings,
            category = R.string.snacks_category,
            ingredients = listOf(
                IngredientsModel(R.string.bread, R.string.bread_qty, R.drawable.bread_img),
                IngredientsModel(R.string.tomato, R.string.tomato_qty, R.drawable.tomato_img),
                IngredientsModel(R.string.lettuce, R.string.lettuce_qty, R.drawable.lettuce_img),
                IngredientsModel(R.string.veggie_patty, R.string.veggie_patty_qty, R.drawable.veggie_patty_img),
                IngredientsModel(R.string.cheese, R.string.cheese_qty, R.drawable.cheese_img)
            )
        ),
        MealModel(
            img1 = R.drawable.chicken_salad,
            img2 = R.drawable.chicken_salad_alt,
            name = R.string.chicken_salad_name,
            shortDesc = R.string.chicken_salad_short_desc,
            about = R.string.chicken_salad_about,
            time = R.string.chicken_salad_time,
            servings = R.string.chicken_salad_servings,
            category = R.string.salads_category,
            ingredients = listOf(
                IngredientsModel(R.string.chicken, R.string.chicken_qty, R.drawable.chicken_img),
                IngredientsModel(R.string.lettuce, R.string.lettuce_qty, R.drawable.lettuce_img),
                IngredientsModel(R.string.tomato, R.string.tomato_qty, R.drawable.tomato_img),
                IngredientsModel(R.string.cucumber, R.string.cucumber_qty, R.drawable.cucumber_img),
                IngredientsModel(R.string.olive_oil, R.string.olive_oil_qty, R.drawable.olive_oil_img)
            )
        ),
        MealModel(
            img1 = R.drawable.spaghetti_bolognese,
            img2 = R.drawable.spaghetti_bolognese_alt,
            name = R.string.spaghetti_bolognese_name,
            shortDesc = R.string.spaghetti_bolognese_short_desc,
            about = R.string.spaghetti_bolognese_about,
            time = R.string.spaghetti_bolognese_time,
            servings = R.string.spaghetti_bolognese_servings,
            category = R.string.main_course_category,
            ingredients = listOf(
                IngredientsModel(R.string.spaghetti, R.string.spaghetti_qty, R.drawable.spaghetti_img),
                IngredientsModel(R.string.ground_beef, R.string.ground_beef_qty, R.drawable.ground_beef_img),
                IngredientsModel(R.string.tomato_sauce, R.string.tomato_sauce_qty, R.drawable.tomato_sauce_img),
                IngredientsModel(R.string.garlic, R.string.garlic_qty, R.drawable.garlic_img),
                IngredientsModel(R.string.onion, R.string.onion_qty, R.drawable.onion_img),
                IngredientsModel(R.string.olive_oil, R.string.olive_oil_qty, R.drawable.olive_oil_img)
            )
        ),
        MealModel(
            img1 = R.drawable.mango_smoothie,
            img2 = R.drawable.mango_smoothie_alt,
            name = R.string.mango_smoothie_name,
            shortDesc = R.string.mango_smoothie_short_desc,
            about = R.string.mango_smoothie_about,
            time = R.string.mango_smoothie_time,
            servings = R.string.mango_smoothie_servings,
            category = R.string.desserts_category,
            ingredients = listOf(
                IngredientsModel(R.string.mango, R.string.mango_qty, R.drawable.mango_img),
                IngredientsModel(R.string.yogurt, R.string.yogurt_qty, R.drawable.yogurt_img),
                IngredientsModel(R.string.honey, R.string.honey_qty, R.drawable.honey_img),
                IngredientsModel(R.string.ice, R.string.ice_qty, R.drawable.ice_img)
            )
        ),
        MealModel(
            img1 = R.drawable.steak,
            img2 = R.drawable.steak_alt,
            name = R.string.steak_name,
            shortDesc = R.string.steak_short_desc,
            about = R.string.steak_about,
            time = R.string.steak_time,
            servings = R.string.steak_servings,
            category = R.string.main_course_category,
            ingredients = listOf(
                IngredientsModel(R.string.steak, R.string.steak_qty, R.drawable.steak_img),
                IngredientsModel(R.string.salt, R.string.salt_qty, R.drawable.salt_img),
                IngredientsModel(R.string.pepper, R.string.pepper_qty, R.drawable.pepper_img),
                IngredientsModel(R.string.olive_oil, R.string.olive_oil_qty, R.drawable.olive_oil_img)
            )
        ),
        MealModel(
            img1 = R.drawable.fruit_salad,
            img2 = R.drawable.fruit_salad_alt,
            name = R.string.fruit_salad_name,
            shortDesc = R.string.fruit_salad_short_desc,
            about = R.string.fruit_salad_about,
            time = R.string.fruit_salad_time,
            servings = R.string.fruit_salad_servings,
            category = R.string.salads_category,
            ingredients = listOf(
                IngredientsModel(R.string.apple, R.string.apple_qty, R.drawable.apple_img),
                IngredientsModel(R.string.grapes, R.string.grapes_qty, R.drawable.grapes_img),
                IngredientsModel(R.string.banana, R.string.banana_qty, R.drawable.banana_img),
                IngredientsModel(R.string.orange, R.string.orange_qty, R.drawable.orange_img)
            )
        ),
        MealModel(
            img1 = R.drawable.tacos,
            img2 = R.drawable.tacos_alt,
            name = R.string.tacos_name,
            shortDesc = R.string.tacos_short_desc,
            about = R.string.tacos_about,
            time = R.string.tacos_time,
            servings = R.string.tacos_servings,
            category = R.string.snacks_category,
            ingredients = listOf(
                IngredientsModel(R.string.taco_shells, R.string.taco_shells_qty, R.drawable.taco_shells_img),
                IngredientsModel(R.string.ground_beef, R.string.ground_beef_qty, R.drawable.ground_beef_img),
                IngredientsModel(R.string.cheese, R.string.cheese_qty, R.drawable.cheese_img),
                IngredientsModel(R.string.sour_cream, R.string.sour_cream_qty, R.drawable.sour_cream_img)
            )
        ),
        MealModel(
            img1 = R.drawable.sushi_roll,
            img2 = R.drawable.sushi_roll_alt,
            name = R.string.sushi_roll_name,
            shortDesc = R.string.sushi_roll_short_desc,
            about = R.string.sushi_roll_about,
            time = R.string.sushi_roll_time,
            servings = R.string.sushi_roll_servings,
            category = R.string.main_course_category,
            ingredients = listOf(
                IngredientsModel(R.string.sushi_rice, R.string.sushi_rice_qty, R.drawable.sushi_rice_img),
                IngredientsModel(R.string.nori, R.string.nori_qty, R.drawable.nori_img),
                IngredientsModel(R.string.avocado, R.string.avocado_qty, R.drawable.avocado_img),
                IngredientsModel(R.string.cucumber, R.string.cucumber_qty, R.drawable.cucumber_img),
                IngredientsModel(R.string.salmon, R.string.salmon_qty, R.drawable.salmon_img)
            )
        ),
        MealModel(
            img1 = R.drawable.vegetable_stir_fry,
            img2 = R.drawable.vegetable_stir_fry_alt,
            name = R.string.vegetable_stir_fry_name,
            shortDesc = R.string.vegetable_stir_fry_short_desc,
            about = R.string.vegetable_stir_fry_about,
            time = R.string.vegetable_stir_fry_time,
            servings = R.string.vegetable_stir_fry_servings,
            category = R.string.main_course_category,
            ingredients = listOf(
                IngredientsModel(R.string.bell_pepper, R.string.bell_pepper_qty, R.drawable.bell_pepper_img),
                IngredientsModel(R.string.carrot, R.string.carrot_qty, R.drawable.carrot_img),
                IngredientsModel(R.string.broccoli, R.string.broccoli_qty, R.drawable.broccoli_img),
                IngredientsModel(R.string.garlic, R.string.garlic_qty, R.drawable.garlic_img),
                IngredientsModel(R.string.soy_sauce, R.string.soy_sauce_qty, R.drawable.soy_sauce_img)
            )
        ),

        MealModel(
            img1 = R.drawable.pizza,
            img2 = R.drawable.pizza_alt,
            name = R.string.pizza_name,
            shortDesc = R.string.pizza_short_desc,
            about = R.string.pizza_about,
            time = R.string.pizza_time,
            servings = R.string.pizza_servings,
            category = R.string.snacks_category,
            ingredients = listOf(
                IngredientsModel(R.string.pizza_dough, R.string.pizza_dough_qty, R.drawable.pizza_dough_img),
                IngredientsModel(R.string.tomato_sauce, R.string.tomato_sauce_qty, R.drawable.tomato_sauce_img),
                IngredientsModel(R.string.cheese, R.string.cheese_qty, R.drawable.cheese_img),
                IngredientsModel(R.string.pepperoni, R.string.pepperoni_qty, R.drawable.pepperoni_img),
                IngredientsModel(R.string.olive_oil, R.string.olive_oil_qty, R.drawable.olive_oil_img)
            )
        ),

        MealModel(
            img1 = R.drawable.chocolate_cake,
            img2 = R.drawable.chocolate_cake_alt,
            name = R.string.chocolate_cake_name,
            shortDesc = R.string.chocolate_cake_short_desc,
            about = R.string.chocolate_cake_about,
            time = R.string.chocolate_cake_time,
            servings = R.string.chocolate_cake_servings,
            category = R.string.desserts_category,
            ingredients = listOf(
                IngredientsModel(R.string.flour, R.string.flour_qty, R.drawable.flour_img),
                IngredientsModel(R.string.cocoa_powder, R.string.cocoa_powder_qty, R.drawable.cocoa_powder_img),
                IngredientsModel(R.string.eggs, R.string.eggs_qty, R.drawable.eggs_img),
                IngredientsModel(R.string.sugar, R.string.sugar_qty, R.drawable.sugar_img),
                IngredientsModel(R.string.butter, R.string.butter_qty, R.drawable.butter_img)
            )
        ),

        MealModel(
            img1 = R.drawable.omelette,
            img2 = R.drawable.omelette_alt,
            name = R.string.omelette_name,
            shortDesc = R.string.omelette_short_desc,
            about = R.string.omelette_about,
            time = R.string.omelette_time,
            servings = R.string.omelette_servings,
            category = R.string.breakfast_category,
            ingredients = listOf(
                IngredientsModel(R.string.eggs, R.string.eggs_qty, R.drawable.eggs_img),
                IngredientsModel(R.string.cheese, R.string.cheese_qty, R.drawable.cheese_img),
                IngredientsModel(R.string.milk, R.string.milk_qty, R.drawable.milk_img),
                IngredientsModel(R.string.butter, R.string.butter_qty, R.drawable.butter_img),
                IngredientsModel(R.string.olive_oil, R.string.olive_oil_qty, R.drawable.olive_oil_img)
            )
        ),
        MealModel(
            img1 = R.drawable.falafel,
            img2 = R.drawable.falafel_alt,
            name = R.string.falafel_name,
            shortDesc = R.string.falafel_short_desc,
            about = R.string.falafel_about,
            time = R.string.falafel_time,
            servings = R.string.falafel_servings,
            category = R.string.snacks_category,
            ingredients = listOf(
                IngredientsModel(R.string.chickpeas, R.string.chickpeas_qty, R.drawable.chickpeas_img),
                IngredientsModel(R.string.onion, R.string.onion_qty, R.drawable.onion_img),
                IngredientsModel(R.string.garlic, R.string.garlic_qty, R.drawable.garlic_img),
                IngredientsModel(R.string.coriander, R.string.coriander_qty, R.drawable.coriander_img),
                IngredientsModel(R.string.sesame_seeds, R.string.sesame_seeds_qty, R.drawable.sesame_seeds_img)
            )
        ),

        MealModel(
            img1 = R.drawable.vegetable_pasta,
            img2 = R.drawable.vegetable_pasta_alt,
            name = R.string.vegetable_pasta_name,
            shortDesc = R.string.vegetable_pasta_short_desc,
            about = R.string.vegetable_pasta_about,
            time = R.string.vegetable_pasta_time,
            servings = R.string.vegetable_pasta_servings,
            category = R.string.main_course_category,
            ingredients = listOf(
                IngredientsModel(R.string.pasta, R.string.pasta_qty, R.drawable.pasta_img),
                IngredientsModel(R.string.zucchini, R.string.zucchini_qty, R.drawable.zucchini_img),
                IngredientsModel(R.string.bell_pepper, R.string.bell_pepper_qty, R.drawable.bell_pepper_img),
                IngredientsModel(R.string.olives, R.string.olives_qty, R.drawable.olives_img),
                IngredientsModel(R.string.olive_oil, R.string.olive_oil_qty, R.drawable.olive_oil_img)
            )
        ),

        MealModel(
            img1 = R.drawable.french_toast,
            img2 = R.drawable.french_toast_alt,
            name = R.string.french_toast_name,
            shortDesc = R.string.french_toast_short_desc,
            about = R.string.french_toast_about,
            time = R.string.french_toast_time,
            servings = R.string.french_toast_servings,
            category = R.string.breakfast_category,
            ingredients = listOf(
                IngredientsModel(R.string.bread, R.string.bread_qty, R.drawable.bread_img),
                IngredientsModel(R.string.eggs, R.string.eggs_qty, R.drawable.eggs_img),
                IngredientsModel(R.string.milk, R.string.milk_qty, R.drawable.milk_img),
                IngredientsModel(R.string.cinnamon, R.string.cinnamon_qty, R.drawable.cinnamon_img),
                IngredientsModel(R.string.sugar, R.string.sugar_qty, R.drawable.sugar_img)
            )
        ),

        MealModel(
            img1 = R.drawable.vegan_stir_fry,
            img2 = R.drawable.vegan_stir_fry_alt,
            name = R.string.vegan_stir_fry_name,
            shortDesc = R.string.vegan_stir_fry_short_desc,
            about = R.string.vegan_stir_fry_about,
            time = R.string.vegan_stir_fry_time,
            servings = R.string.vegan_stir_fry_servings,
            category = R.string.main_course_category,
            ingredients = listOf(
                IngredientsModel(R.string.tofu, R.string.tofu_qty, R.drawable.tofu_img),
                IngredientsModel(R.string.broccoli, R.string.broccoli_qty, R.drawable.broccoli_img),
                IngredientsModel(R.string.carrot, R.string.carrot_qty, R.drawable.carrot_img),
                IngredientsModel(R.string.soy_sauce, R.string.soy_sauce_qty, R.drawable.soy_sauce_img),
                IngredientsModel(R.string.garlic, R.string.garlic_qty, R.drawable.garlic_img)
            )
        ),

        MealModel(
            img1 = R.drawable.breakfast_bowl,
            img2 = R.drawable.breakfast_bowl_alt,
            name = R.string.breakfast_bowl_name,
            shortDesc = R.string.breakfast_bowl_short_desc,
            about = R.string.breakfast_bowl_about,
            time = R.string.breakfast_bowl_time,
            servings = R.string.breakfast_bowl_servings,
            category = R.string.breakfast_category,
            ingredients = listOf(
                IngredientsModel(R.string.oats, R.string.oats_qty, R.drawable.oats_img),
                IngredientsModel(R.string.banana, R.string.banana_qty, R.drawable.banana_img),
                IngredientsModel(R.string.chia_seeds, R.string.chia_seeds_qty, R.drawable.chia_seeds_img),
                IngredientsModel(R.string.milk, R.string.milk_qty, R.drawable.milk_img),
                IngredientsModel(R.string.honey, R.string.honey_qty, R.drawable.honey_img)
            )
        ),
        MealModel(
            img1 = R.drawable.stuffed_peppers,
            img2 = R.drawable.stuffed_peppers_alt,
            name = R.string.stuffed_peppers_name,
            shortDesc = R.string.stuffed_peppers_short_desc,
            about = R.string.stuffed_peppers_about,
            time = R.string.stuffed_peppers_time,
            servings = R.string.stuffed_peppers_servings,
            category = R.string.main_course_category,
            ingredients = listOf(
                IngredientsModel(R.string.bell_pepper, R.string.bell_pepper_qty, R.drawable.bell_pepper_img),
                IngredientsModel(R.string.quinoa, R.string.quinoa_qty, R.drawable.quinoa_img),
                IngredientsModel(R.string.tomato, R.string.tomato_qty, R.drawable.tomato_img),
                IngredientsModel(R.string.cheese, R.string.cheese_qty, R.drawable.cheese_img),
                IngredientsModel(R.string.olive_oil, R.string.olive_oil_qty, R.drawable.olive_oil_img)
            )
        ),

        MealModel(
            img1 = R.drawable.grilled_vegetables,
            img2 = R.drawable.grilled_vegetables_alt,
            name = R.string.grilled_vegetables_name,
            shortDesc = R.string.grilled_vegetables_short_desc,
            about = R.string.grilled_vegetables_about,
            time = R.string.grilled_vegetables_time,
            servings = R.string.grilled_vegetables_servings,
            category = R.string.side_dish_category,
            ingredients = listOf(
                IngredientsModel(R.string.zucchini, R.string.zucchini_qty, R.drawable.zucchini_img),
                IngredientsModel(R.string.bell_pepper, R.string.bell_pepper_qty, R.drawable.bell_pepper_img),
                IngredientsModel(R.string.red_onion, R.string.red_onion_qty, R.drawable.red_onion_img),
                IngredientsModel(R.string.olive_oil, R.string.olive_oil_qty, R.drawable.olive_oil_img),
                IngredientsModel(R.string.salt, R.string.salt_qty, R.drawable.salt_img)
            )
        ),

        MealModel(
            img1 = R.drawable.mushroom_risotto,
            img2 = R.drawable.mushroom_risotto_alt,
            name = R.string.mushroom_risotto_name,
            shortDesc = R.string.mushroom_risotto_short_desc,
            about = R.string.mushroom_risotto_about,
            time = R.string.mushroom_risotto_time,
            servings = R.string.mushroom_risotto_servings,
            category = R.string.main_course_category,
            ingredients = listOf(
                IngredientsModel(R.string.rice, R.string.rice_qty, R.drawable.rice_img),
                IngredientsModel(R.string.mushrooms, R.string.mushrooms_qty, R.drawable.mushrooms_img),
                IngredientsModel(R.string.onion, R.string.onion_qty, R.drawable.onion_img),
                IngredientsModel(R.string.butter, R.string.butter_qty, R.drawable.butter_img),
                IngredientsModel(R.string.parmesan, R.string.parmesan_qty, R.drawable.parmesan_img)
            )
        ),
        MealModel(
            img1 = R.drawable.baked_ziti,
            img2 = R.drawable.baked_ziti_alt,
            name = R.string.baked_ziti_name,
            shortDesc = R.string.baked_ziti_short_desc,
            about = R.string.baked_ziti_about,
            time = R.string.baked_ziti_time,
            servings = R.string.baked_ziti_servings,
            category = R.string.main_course_category,
            ingredients = listOf(
                IngredientsModel(R.string.ziti_pasta, R.string.ziti_pasta_qty, R.drawable.ziti_pasta_img),
                IngredientsModel(R.string.marinara_sauce, R.string.marinara_sauce_qty, R.drawable.marinara_sauce_img),
                IngredientsModel(R.string.cheese, R.string.cheese_qty, R.drawable.cheese_img),
                IngredientsModel(R.string.basil, R.string.basil_qty, R.drawable.basil_img)
            )
        ),

        MealModel(
            img1 = R.drawable.pulled_pork_sandwich,
            img2 = R.drawable.pulled_pork_sandwich_alt,
            name = R.string.pulled_pork_sandwich_name,
            shortDesc = R.string.pulled_pork_sandwich_short_desc,
            about = R.string.pulled_pork_sandwich_about,
            time = R.string.pulled_pork_sandwich_time,
            servings = R.string.pulled_pork_sandwich_servings,
            category = R.string.sandwiches_category,
            ingredients = listOf(
                IngredientsModel(R.string.pulled_pork, R.string.pulled_pork_qty, R.drawable.pulled_pork_img),
                IngredientsModel(R.string.buns, R.string.buns_qty, R.drawable.buns_img),
                IngredientsModel(R.string.coleslaw, R.string.coleslaw_qty, R.drawable.coleslaw_img),
                IngredientsModel(R.string.bbq_sauce, R.string.bbq_sauce_qty, R.drawable.bbq_sauce_img)
            )
        ),

        MealModel(
            img1 = R.drawable.vegetable_stir_fry,
            img2 = R.drawable.vegetable_stir_fry_alt,
            name = R.string.vegetable_stir_fry_name,
            shortDesc = R.string.vegetable_stir_fry_short_desc,
            about = R.string.vegetable_stir_fry_about,
            time = R.string.vegetable_stir_fry_time,
            servings = R.string.vegetable_stir_fry_servings,
            category = R.string.vegetarian_category,
            ingredients = listOf(
                IngredientsModel(R.string.broccoli, R.string.broccoli_qty, R.drawable.broccoli_img),
                IngredientsModel(R.string.carrot, R.string.carrot_qty, R.drawable.carrot_img),
                IngredientsModel(R.string.soy_sauce, R.string.soy_sauce_qty, R.drawable.soy_sauce_img),
                IngredientsModel(R.string.garlic, R.string.garlic_qty, R.drawable.garlic_img),
                IngredientsModel(R.string.olive_oil, R.string.olive_oil_qty, R.drawable.olive_oil_img)
            )
        ),
        MealModel(
            img1 = R.drawable.vegetable_biryani,
            img2 = R.drawable.vegetable_biryani_alt,
            name = R.string.vegetable_biryani_name,
            shortDesc = R.string.vegetable_biryani_short_desc,
            about = R.string.vegetable_biryani_about,
            time = R.string.vegetable_biryani_time,
            servings = R.string.vegetable_biryani_servings,
            category = R.string.main_course_category,
            ingredients = listOf(
                IngredientsModel(R.string.basmati_rice, R.string.basmati_rice_qty, R.drawable.basmati_rice_img),
                IngredientsModel(R.string.carrot, R.string.carrot_qty, R.drawable.carrot_img),
                IngredientsModel(R.string.peas, R.string.peas_qty, R.drawable.peas_img),
                IngredientsModel(R.string.spices_biryani, R.string.spices_biryani_qty, R.drawable.spices_biryani_img)
            )
        ),

        MealModel(
            img1 = R.drawable.spaghetti_carbonara,
            img2 = R.drawable.spaghetti_carbonara_alt,
            name = R.string.spaghetti_carbonara_name,
            shortDesc = R.string.spaghetti_carbonara_short_desc,
            about = R.string.spaghetti_carbonara_about,
            time = R.string.spaghetti_carbonara_time,
            servings = R.string.spaghetti_carbonara_servings,
            category = R.string.main_course_category,
            ingredients = listOf(
                IngredientsModel(R.string.spaghetti, R.string.spaghetti_qty, R.drawable.spaghetti_img),
                IngredientsModel(R.string.pancetta, R.string.pancetta_qty, R.drawable.pancetta_img),
                IngredientsModel(R.string.egg_yolk, R.string.egg_yolk_qty, R.drawable.egg_yolk_img),
                IngredientsModel(R.string.parmesan, R.string.parmesan_qty, R.drawable.parmesan_img)
            )
        )
    )

    fun getMeals(): List<MealModel> = allMeals
}