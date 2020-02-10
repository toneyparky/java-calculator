package study;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * 클래스 이름 : StringTest.java
 *
 * @author S.W.PARK
 * @author K.S.KIM
 * @version 1.0
 * <p>
 * 날짜 : 2020.02.05 수요일
 */

public class StringTest {
	@Test
	@DisplayName("String Test")
	void split() {
		String value = "1,2";
		String[] result = value.split(",");
		Assertions.assertThat(result).contains("1");
		Assertions.assertThat(result).contains("2");
		Assertions.assertThat(result).containsExactly("1", "2");
	}

	@Test
	@DisplayName("substring Test")
	void substring() {
		String value = "(1, 2)";
		String result = value.substring(1, value.length() - 1);
		Assertions.assertThat(result).contains("1, 2");
	}

	@Test
	@DisplayName("charAt Test")
	void charAt() {
		Assertions.assertThatThrownBy(() -> {
			String value = "abc";
			char charOfIndexZero = value.charAt(0);
			char charOfIndexOne = value.charAt(1);
			char charOfIndexTwo = value.charAt(2);
			char charOfIndexThree = value.charAt(3);
		}).isInstanceOf(StringIndexOutOfBoundsException.class)
				.hasMessageContaining("String index out of range: 3");
	}
}
