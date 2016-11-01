/*
 * Copyright 2016 Doug Hoard
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.devopology.tools;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Locale;

public interface StringUtils {

    boolean isEmpty(CharSequence cs);

    boolean isNotEmpty(CharSequence cs);

    boolean isAnyEmpty(CharSequence... css);

    boolean isNoneEmpty(CharSequence... css);

    boolean isBlank(CharSequence cs);

    boolean isNotBlank(CharSequence cs);

    boolean isAnyBlank(CharSequence... css);

    boolean isNoneBlank(CharSequence... css);

    String trim(String str);

    String trimToNull(String str);

    String trimToEmpty(String str);

    String truncate(String str, int maxWidth);

    String truncate(String str, int offset, int maxWidth);

    String strip(String str);

    String stripToNull(String str);

    String stripToEmpty(String str);

    String strip(String str, String stripChars);

    String stripStart(String str, String stripChars);

    String stripEnd(String str, String stripChars);

    String[] stripAll(String... strs);

    String[] stripAll(String[] strs, String stripChars);

    String stripAccents(String input);

    boolean equals(CharSequence cs1, CharSequence cs2);

    boolean equalsIgnoreCase(CharSequence str1, CharSequence str2);

    int compare(String str1, String str2);

    int compare(String str1, String str2, boolean nullIsLess);

    int compareIgnoreCase(String str1, String str2);

    int compareIgnoreCase(String str1, String str2, boolean nullIsLess);

    boolean equalsAny(CharSequence string, CharSequence... searchStrings);

    boolean equalsAnyIgnoreCase(CharSequence string, CharSequence... searchStrings);

    int indexOf(CharSequence seq, int searchChar);

    int indexOf(CharSequence seq, int searchChar, int startPos);

    int indexOf(CharSequence seq, CharSequence searchSeq);

    int indexOf(CharSequence seq, CharSequence searchSeq, int startPos);

    int ordinalIndexOf(CharSequence str, CharSequence searchStr, int ordinal);

    int indexOfIgnoreCase(CharSequence str, CharSequence searchStr);

    int indexOfIgnoreCase(CharSequence str, CharSequence searchStr, int startPos);

    int lastIndexOf(CharSequence seq, int searchChar);

    int lastIndexOf(CharSequence seq, int searchChar, int startPos);

    int lastIndexOf(CharSequence seq, CharSequence searchSeq);

    int lastOrdinalIndexOf(CharSequence str, CharSequence searchStr, int ordinal);

    int lastIndexOf(CharSequence seq, CharSequence searchSeq, int startPos);

    int lastIndexOfIgnoreCase(CharSequence str, CharSequence searchStr);

    int lastIndexOfIgnoreCase(CharSequence str, CharSequence searchStr, int startPos);

    boolean contains(CharSequence seq, int searchChar);

    boolean contains(CharSequence seq, CharSequence searchSeq);

    boolean containsIgnoreCase(CharSequence str, CharSequence searchStr);

    boolean containsWhitespace(CharSequence seq);

    int indexOfAny(CharSequence cs, char... searchChars);

    int indexOfAny(CharSequence cs, String searchChars);

    boolean containsAny(CharSequence cs, char... searchChars);

    boolean containsAny(CharSequence cs, CharSequence searchChars);

    boolean containsAny(CharSequence cs, CharSequence... searchCharSequences);

    int indexOfAnyBut(CharSequence cs, char... searchChars);

    int indexOfAnyBut(CharSequence seq, CharSequence searchChars);

    boolean containsOnly(CharSequence cs, char... valid);

    boolean containsOnly(CharSequence cs, String validChars);

    boolean containsNone(CharSequence cs, char... searchChars);

    boolean containsNone(CharSequence cs, String invalidChars);

    int indexOfAny(CharSequence str, CharSequence... searchStrs);

    int lastIndexOfAny(CharSequence str, CharSequence... searchStrs);

    String substring(String str, int start);

    String substring(String str, int start, int end);

    String left(String str, int len);

    String right(String str, int len);

    String mid(String str, int pos, int len);

    String substringBefore(String str, String separator);

    String substringAfter(String str, String separator);

    String substringBeforeLast(String str, String separator);

    String substringAfterLast(String str, String separator);

    String substringBetween(String str, String tag);

    String substringBetween(String str, String open, String close);

    String[] substringsBetween(String str, String open, String close);

    String[] split(String str);

    String[] split(String str, char separatorChar);

    String[] split(String str, String separatorChars);

    String[] split(String str, String separatorChars, int max);

    String[] splitByWholeSeparator(String str, String separator);

    String[] splitByWholeSeparator(String str, String separator, int max);

    String[] splitByWholeSeparatorPreserveAllTokens(String str, String separator);

    String[] splitByWholeSeparatorPreserveAllTokens(String str, String separator, int max);

    String[] splitPreserveAllTokens(String str);

    String[] splitPreserveAllTokens(String str, char separatorChar);

    String[] splitPreserveAllTokens(String str, String separatorChars);

    String[] splitPreserveAllTokens(String str, String separatorChars, int max);

    String[] splitByCharacterType(String str);

    String[] splitByCharacterTypeCamelCase(String str);

    <T> String join(T... elements);

    String join(Object[] array, char separator);

    String join(long[] array, char separator);

    String join(int[] array, char separator);

    String join(short[] array, char separator);

    String join(byte[] array, char separator);

    String join(char[] array, char separator);

    String join(float[] array, char separator);

    String join(double[] array, char separator);

    String join(Object[] array, char separator, int startIndex, int endIndex);

    String join(long[] array, char separator, int startIndex, int endIndex);

    String join(int[] array, char separator, int startIndex, int endIndex);

    String join(byte[] array, char separator, int startIndex, int endIndex);

    String join(short[] array, char separator, int startIndex, int endIndex);

    String join(char[] array, char separator, int startIndex, int endIndex);

    String join(double[] array, char separator, int startIndex, int endIndex);

    String join(float[] array, char separator, int startIndex, int endIndex);

    String join(Object[] array, String separator);

    String join(Object[] array, String separator, int startIndex, int endIndex);

    String join(Iterator<?> iterator, char separator);

    String join(Iterator<?> iterator, String separator);

    String join(Iterable<?> iterable, char separator);

    String join(Iterable<?> iterable, String separator);

    String joinWith(String separator, Object... objects);

    String deleteWhitespace(String str);

    String removeStart(String str, String remove);

    String removeStartIgnoreCase(String str, String remove);

    String removeEnd(String str, String remove);

    String removeEndIgnoreCase(String str, String remove);

    String remove(String str, String remove);

    String removeIgnoreCase(String str, String remove);

    String remove(String str, char remove);

    String removeAll(String text, String regex);

    String removeFirst(String text, String regex);

    String replaceOnce(String text, String searchString, String replacement);

    String replaceOnceIgnoreCase(String text, String searchString, String replacement);

    String replacePattern(String source, String regex, String replacement);

    String removePattern(String source, String regex);

    String replaceAll(String text, String regex, String replacement);

    String replaceFirst(String text, String regex, String replacement);

    String replace(String text, String searchString, String replacement);

    String replaceIgnoreCase(String text, String searchString, String replacement);

    String replace(String text, String searchString, String replacement, int max);

    String replaceIgnoreCase(String text, String searchString, String replacement, int max);

    String replaceEach(String text, String[] searchList, String[] replacementList);

    String replaceEachRepeatedly(String text, String[] searchList, String[] replacementList);

    String replaceChars(String str, char searchChar, char replaceChar);

    String replaceChars(String str, String searchChars, String replaceChars);

    String overlay(String str, String overlay, int start, int end);

    String chomp(String str);

    @Deprecated
    String chomp(String str, String separator);

    String chop(String str);

    String repeat(String str, int repeat);

    String repeat(String str, String separator, int repeat);

    String repeat(char ch, int repeat);

    String rightPad(String str, int size);

    String rightPad(String str, int size, char padChar);

    String rightPad(String str, int size, String padStr);

    String leftPad(String str, int size);

    String leftPad(String str, int size, char padChar);

    String leftPad(String str, int size, String padStr);

    int length(CharSequence cs);

    String center(String str, int size);

    String center(String str, int size, char padChar);

    String center(String str, int size, String padStr);

    String upperCase(String str);

    String upperCase(String str, Locale locale);

    String lowerCase(String str);

    String lowerCase(String str, Locale locale);

    String capitalize(String str);

    String uncapitalize(String str);

    String swapCase(String str);

    int countMatches(CharSequence str, CharSequence sub);

    int countMatches(CharSequence str, char ch);

    boolean isAlpha(CharSequence cs);

    boolean isAlphaSpace(CharSequence cs);

    boolean isAlphanumeric(CharSequence cs);

    boolean isAlphanumericSpace(CharSequence cs);

    boolean isAsciiPrintable(CharSequence cs);

    boolean isNumeric(CharSequence cs);

    boolean isNumericSpace(CharSequence cs);

    boolean isWhitespace(CharSequence cs);

    boolean isAllLowerCase(CharSequence cs);

    boolean isAllUpperCase(CharSequence cs);

    String defaultString(String str);

    String defaultString(String str, String defaultStr);

    <T extends CharSequence> T defaultIfBlank(T str, T defaultStr);

    <T extends CharSequence> T defaultIfEmpty(T str, T defaultStr);

    String rotate(String str, int shift);

    String reverse(String str);

    String reverseDelimited(String str, char separatorChar);

    String abbreviate(String str, int maxWidth);

    String abbreviate(String str, int offset, int maxWidth);

    String abbreviateMiddle(String str, String middle, int length);

    String difference(String str1, String str2);

    int indexOfDifference(CharSequence cs1, CharSequence cs2);

    int indexOfDifference(CharSequence... css);

    String getCommonPrefix(String... strs);

    int getLevenshteinDistance(CharSequence s, CharSequence t);

    int getLevenshteinDistance(CharSequence s, CharSequence t, int threshold);

    double getJaroWinklerDistance(CharSequence first, CharSequence second);

    int getFuzzyDistance(CharSequence term, CharSequence query, Locale locale);

    boolean startsWith(CharSequence str, CharSequence prefix);

    boolean startsWithIgnoreCase(CharSequence str, CharSequence prefix);

    boolean startsWithAny(CharSequence sequence, CharSequence... searchStrings);

    boolean endsWith(CharSequence str, CharSequence suffix);

    boolean endsWithIgnoreCase(CharSequence str, CharSequence suffix);

    String normalizeSpace(String str);

    boolean endsWithAny(CharSequence sequence, CharSequence... searchStrings);

    String appendIfMissing(String str, CharSequence suffix, CharSequence... suffixes);

    String appendIfMissingIgnoreCase(String str, CharSequence suffix, CharSequence... suffixes);

    String prependIfMissing(String str, CharSequence prefix, CharSequence... prefixes);

    String prependIfMissingIgnoreCase(String str, CharSequence prefix, CharSequence... prefixes);

    @Deprecated
    String toString(byte[] bytes, String charsetName) throws UnsupportedEncodingException;

    String toEncodedString(byte[] bytes, Charset charset);

    String wrap(String str, char wrapWith);

    String wrap(String str, String wrapWith);

    String wrapIfMissing(String str, char wrapWith);

    String wrapIfMissing(String str, String wrapWith);
}
