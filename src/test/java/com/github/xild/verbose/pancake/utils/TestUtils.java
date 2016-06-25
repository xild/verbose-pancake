/**
 * @Description:
 * @Author: lvieir9
 * @Since: Jun 3, 2016
 * @Version: 
 */
package com.github.xild.verbose.pancake.utils;

import static io.github.benas.randombeans.FieldDefinitionBuilder.field;

import java.util.Random;
import java.util.UUID;

import io.github.benas.randombeans.EnhancedRandomBuilder;
import io.github.benas.randombeans.FieldDefinitionBuilder;
import io.github.benas.randombeans.api.EnhancedRandom;
import io.github.benas.randombeans.api.Randomizer;
import io.github.benas.randombeans.randomizers.CityRandomizer;
import io.github.benas.randombeans.randomizers.EmailRandomizer;
import io.github.benas.randombeans.randomizers.FullNameRandomizer;
import io.github.benas.randombeans.randomizers.ParagraphRandomizer;
import io.github.benas.randombeans.randomizers.PhoneNumberRandomizer;
import io.github.benas.randombeans.randomizers.StateRandomizer;
import io.github.benas.randombeans.randomizers.StreetRandomizer;
import io.github.benas.randombeans.randomizers.WordRandomizer;
import io.github.benas.randombeans.randomizers.ZipCodeRandomizer;

/**
 * @author Luis Vieira
 *
 */
public class TestUtils {

	public static Object generateObject(Class clazz) {
		EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandomBuilder().maxCollectionSize(1)
				.maxStringLength(20).seed(123L)
				.randomize(field().named("street").ofType(String.class).inClass(clazz).get(), new StreetRandomizer())
				.randomize(field().named("city").ofType(String.class).inClass(clazz).get(), new CityRandomizer())
				.randomize(field().named("state").ofType(String.class).inClass(clazz).get(), new StateRandomizer())
				.randomize(field().named("zipCode").ofType(String.class).inClass(clazz).get(), new ZipCodeRandomizer(9))
				.randomize(field().named("neighborhood").ofType(String.class).inClass(clazz).get(),
						new WordRandomizer())
				.randomize(field().named("addressDetail").ofType(String.class).inClass(clazz).get(),
						new WordRandomizer())

				.build();
		Object obj = random.nextObject(clazz);
		return obj;

	}

}
