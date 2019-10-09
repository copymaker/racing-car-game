package io.copymaker.racing.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.*;

class StrUtilTest {

    @Test
    void repeatPositiveTest() {
        assertThat(StrUtil.repeat(" ", 3)).isEqualTo("   ");
        assertThat(StrUtil.repeat("ab", 3)).isEqualTo("ababab");
        assertThat(StrUtil.repeat("abc", 3)).isEqualTo("abcabcabc");
    }

    @ParameterizedTest
    @MethodSource("provideRepeatNegativeFixtures")
    void repeatNegativeTest(String str, int count) {
        System.out.println( StrUtil.repeat(str, 0) );
    }

    private static Stream<Arguments> provideRepeatNegativeFixtures() {
        return Stream.of(
                Arguments.of(null, 3),
                Arguments.of("", 3),
                Arguments.of(null, -1),
                Arguments.of("", -1)
        );
    }

}