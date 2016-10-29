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

public class StringUtils {

    public boolean isEmpty(CharSequence cs) {
        return org.apache.commons.lang3.StringUtils.isEmpty(cs);
    }

    public boolean isNotEmpty(CharSequence cs) {
        return org.apache.commons.lang3.StringUtils.isNotEmpty(cs);
    }

    public boolean isAnyEmpty(CharSequence... css) {
        return org.apache.commons.lang3.StringUtils.isAnyEmpty(css);
    }

    public boolean isNoneEmpty(CharSequence... css) {
        return org.apache.commons.lang3.StringUtils.isNoneEmpty(css);
    }

    public boolean isBlank(CharSequence cs) {
        return org.apache.commons.lang3.StringUtils.isBlank(cs);
    }

    public boolean isNotBlank(CharSequence cs) {
        return org.apache.commons.lang3.StringUtils.isNotBlank(cs);
    }

    public boolean isAnyBlank(CharSequence... css) {
        return org.apache.commons.lang3.StringUtils.isAnyBlank(css);
    }

    public boolean isNoneBlank(CharSequence... css) {
        return org.apache.commons.lang3.StringUtils.isNoneBlank(css);
    }

    public String trim(String str) {
        return org.apache.commons.lang3.StringUtils.trim(str);
    }

    public String trimToNull(String str) {
        return org.apache.commons.lang3.StringUtils.trimToNull(str);
    }

    public String trimToEmpty(String str) {
        return org.apache.commons.lang3.StringUtils.trimToEmpty(str);
    }

    public String truncate(String str, int maxWidth) {
        return org.apache.commons.lang3.StringUtils.truncate(str, maxWidth);
    }

    public String truncate(String str, int offset, int maxWidth) {
        return org.apache.commons.lang3.StringUtils.truncate(str, offset, maxWidth);
    }

    public String strip(String str) {
        return org.apache.commons.lang3.StringUtils.strip(str);
    }

    public String stripToNull(String str) {
        return org.apache.commons.lang3.StringUtils.stripToNull(str);
    }

    public String stripToEmpty(String str) {
        return org.apache.commons.lang3.StringUtils.stripToEmpty(str);
    }

    public String strip(String str, String stripChars) {
        return org.apache.commons.lang3.StringUtils.strip(str, stripChars);
    }

    public String stripStart(String str, String stripChars) {
        return org.apache.commons.lang3.StringUtils.stripStart(str, stripChars);
    }

    public String stripEnd(String str, String stripChars) {
        return org.apache.commons.lang3.StringUtils.stripEnd(str, stripChars);
    }

    public String[] stripAll(String... strs) {
        return org.apache.commons.lang3.StringUtils.stripAll(strs);
    }

    public String[] stripAll(String[] strs, String stripChars) {
        return org.apache.commons.lang3.StringUtils.stripAll(strs, stripChars);
    }

    public String stripAccents(String input) {
        return org.apache.commons.lang3.StringUtils.stripAccents(input);
    }

    public boolean equals(CharSequence cs1, CharSequence cs2) {
        return org.apache.commons.lang3.StringUtils.equals(cs1, cs2);
    }

    public boolean equalsIgnoreCase(CharSequence str1, CharSequence str2) {
        return org.apache.commons.lang3.StringUtils.equalsIgnoreCase(str1, str2);
    }

    public int compare(String str1, String str2) {
        return org.apache.commons.lang3.StringUtils.compare(str1, str2);
    }

    public int compare(String str1, String str2, boolean nullIsLess) {
        return org.apache.commons.lang3.StringUtils.compare(str1, str2, nullIsLess);
    }

    public int compareIgnoreCase(String str1, String str2) {
        return org.apache.commons.lang3.StringUtils.compareIgnoreCase(str1, str2);
    }

    public int compareIgnoreCase(String str1, String str2, boolean nullIsLess) {
        return org.apache.commons.lang3.StringUtils.compareIgnoreCase(str1, str2, nullIsLess);
    }

    public boolean equalsAny(CharSequence string, CharSequence... searchStrings) {
        return org.apache.commons.lang3.StringUtils.equalsAny(string, searchStrings);
    }

    public boolean equalsAnyIgnoreCase(CharSequence string, CharSequence... searchStrings) {
        return org.apache.commons.lang3.StringUtils.equalsAnyIgnoreCase(string, searchStrings);
    }

    public int indexOf(CharSequence seq, int searchChar) {
        return org.apache.commons.lang3.StringUtils.indexOf(seq, searchChar);
    }

    public int indexOf(CharSequence seq, int searchChar, int startPos) {
        return org.apache.commons.lang3.StringUtils.indexOf(seq, searchChar, startPos);
    }

    public int indexOf(CharSequence seq, CharSequence searchSeq) {
        return org.apache.commons.lang3.StringUtils.indexOf(seq, searchSeq);
    }

    public int indexOf(CharSequence seq, CharSequence searchSeq, int startPos) {
        return org.apache.commons.lang3.StringUtils.indexOf(seq, searchSeq, startPos);
    }

    public int ordinalIndexOf(CharSequence str, CharSequence searchStr, int ordinal) {
        return org.apache.commons.lang3.StringUtils.ordinalIndexOf(str, searchStr, ordinal);
    }

    public int indexOfIgnoreCase(CharSequence str, CharSequence searchStr) {
        return org.apache.commons.lang3.StringUtils.indexOfIgnoreCase(str, searchStr);
    }

    public int indexOfIgnoreCase(CharSequence str, CharSequence searchStr, int startPos) {
        return org.apache.commons.lang3.StringUtils.indexOfIgnoreCase(str, searchStr, startPos);
    }

    public int lastIndexOf(CharSequence seq, int searchChar) {
        return org.apache.commons.lang3.StringUtils.lastIndexOf(seq, searchChar);
    }

    public int lastIndexOf(CharSequence seq, int searchChar, int startPos) {
        return org.apache.commons.lang3.StringUtils.lastIndexOf(seq, searchChar, startPos);
    }

    public int lastIndexOf(CharSequence seq, CharSequence searchSeq) {
        return org.apache.commons.lang3.StringUtils.lastIndexOf(seq, searchSeq);
    }

    public int lastOrdinalIndexOf(CharSequence str, CharSequence searchStr, int ordinal) {
        return org.apache.commons.lang3.StringUtils.lastOrdinalIndexOf(str, searchStr, ordinal);
    }

    public int lastIndexOf(CharSequence seq, CharSequence searchSeq, int startPos) {
        return org.apache.commons.lang3.StringUtils.lastIndexOf(seq, searchSeq, startPos);
    }

    public int lastIndexOfIgnoreCase(CharSequence str, CharSequence searchStr) {
        return org.apache.commons.lang3.StringUtils.lastIndexOfIgnoreCase(str, searchStr);
    }

    public int lastIndexOfIgnoreCase(CharSequence str, CharSequence searchStr, int startPos) {
        return org.apache.commons.lang3.StringUtils.lastIndexOfIgnoreCase(str, searchStr, startPos);
    }

    public boolean contains(CharSequence seq, int searchChar) {
        return org.apache.commons.lang3.StringUtils.contains(seq, searchChar);
    }

    public boolean contains(CharSequence seq, CharSequence searchSeq) {
        return org.apache.commons.lang3.StringUtils.contains(seq, searchSeq);
    }

    public boolean containsIgnoreCase(CharSequence str, CharSequence searchStr) {
        return org.apache.commons.lang3.StringUtils.containsIgnoreCase(str, searchStr);
    }

    public boolean containsWhitespace(CharSequence seq) {
        return org.apache.commons.lang3.StringUtils.containsWhitespace(seq);
    }

    public int indexOfAny(CharSequence cs, char... searchChars) {
        return org.apache.commons.lang3.StringUtils.indexOfAny(cs, searchChars);
    }

    public int indexOfAny(CharSequence cs, String searchChars) {
        return org.apache.commons.lang3.StringUtils.indexOfAny(cs, searchChars);
    }

    public boolean containsAny(CharSequence cs, char... searchChars) {
        return org.apache.commons.lang3.StringUtils.containsAny(cs, searchChars);
    }

    public boolean containsAny(CharSequence cs, CharSequence searchChars) {
        return org.apache.commons.lang3.StringUtils.containsAny(cs, searchChars);
    }

    public boolean containsAny(CharSequence cs, CharSequence... searchCharSequences) {
        return org.apache.commons.lang3.StringUtils.containsAny(cs, searchCharSequences);
    }

    public int indexOfAnyBut(CharSequence cs, char... searchChars) {
        return org.apache.commons.lang3.StringUtils.indexOfAnyBut(cs, searchChars);
    }

    public int indexOfAnyBut(CharSequence seq, CharSequence searchChars) {
        return org.apache.commons.lang3.StringUtils.indexOfAnyBut(seq, searchChars);
    }

    public boolean containsOnly(CharSequence cs, char... valid) {
        return org.apache.commons.lang3.StringUtils.containsOnly(cs, valid);
    }

    public boolean containsOnly(CharSequence cs, String validChars) {
        return org.apache.commons.lang3.StringUtils.containsOnly(cs, validChars);
    }

    public boolean containsNone(CharSequence cs, char... searchChars) {
        return org.apache.commons.lang3.StringUtils.containsNone(cs, searchChars);
    }

    public boolean containsNone(CharSequence cs, String invalidChars) {
        return org.apache.commons.lang3.StringUtils.containsNone(cs, invalidChars);
    }

    public int indexOfAny(CharSequence str, CharSequence... searchStrs) {
        return org.apache.commons.lang3.StringUtils.indexOfAny(str, searchStrs);
    }

    public int lastIndexOfAny(CharSequence str, CharSequence... searchStrs) {
        return org.apache.commons.lang3.StringUtils.lastIndexOfAny(str, searchStrs);
    }

    public String substring(String str, int start) {
        return org.apache.commons.lang3.StringUtils.substring(str, start);
    }

    public String substring(String str, int start, int end) {
        return org.apache.commons.lang3.StringUtils.substring(str, start, end);
    }

    public String left(String str, int len) {
        return org.apache.commons.lang3.StringUtils.left(str, len);
    }

    public String right(String str, int len) {
        return org.apache.commons.lang3.StringUtils.right(str, len);
    }

    public String mid(String str, int pos, int len) {
        return org.apache.commons.lang3.StringUtils.mid(str, pos, len);
    }

    public String substringBefore(String str, String separator) {
        return org.apache.commons.lang3.StringUtils.substringBefore(str, separator);
    }

    public String substringAfter(String str, String separator) {
        return org.apache.commons.lang3.StringUtils.substringAfter(str, separator);
    }

    public String substringBeforeLast(String str, String separator) {
        return org.apache.commons.lang3.StringUtils.substringBeforeLast(str, separator);
    }

    public String substringAfterLast(String str, String separator) {
        return org.apache.commons.lang3.StringUtils.substringAfterLast(str, separator);
    }

    public String substringBetween(String str, String tag) {
        return org.apache.commons.lang3.StringUtils.substringBetween(str, tag);
    }

    public String substringBetween(String str, String open, String close) {
        return org.apache.commons.lang3.StringUtils.substringBetween(str, open, close);
    }

    public String[] substringsBetween(String str, String open, String close) {
        return org.apache.commons.lang3.StringUtils.substringsBetween(str, open, close);
    }

    public String[] split(String str) {
        return org.apache.commons.lang3.StringUtils.split(str);
    }

    public String[] split(String str, char separatorChar) {
        return org.apache.commons.lang3.StringUtils.split(str, separatorChar);
    }

    public String[] split(String str, String separatorChars) {
        return org.apache.commons.lang3.StringUtils.split(str, separatorChars);
    }

    public String[] split(String str, String separatorChars, int max) {
        return org.apache.commons.lang3.StringUtils.split(str, separatorChars, max);
    }

    public String[] splitByWholeSeparator(String str, String separator) {
        return org.apache.commons.lang3.StringUtils.splitByWholeSeparator(str, separator);
    }

    public String[] splitByWholeSeparator(String str, String separator, int max) {
        return org.apache.commons.lang3.StringUtils.splitByWholeSeparator(str, separator, max);
    }

    public String[] splitByWholeSeparatorPreserveAllTokens(String str, String separator) {
        return org.apache.commons.lang3.StringUtils.splitByWholeSeparatorPreserveAllTokens(str, separator);
    }

    public String[] splitByWholeSeparatorPreserveAllTokens(String str, String separator, int max) {
        return org.apache.commons.lang3.StringUtils.splitByWholeSeparatorPreserveAllTokens(str, separator, max);
    }

    public String[] splitPreserveAllTokens(String str) {
        return org.apache.commons.lang3.StringUtils.splitPreserveAllTokens(str);
    }

    public String[] splitPreserveAllTokens(String str, char separatorChar) {
        return org.apache.commons.lang3.StringUtils.splitPreserveAllTokens(str, separatorChar);
    }

    public String[] splitPreserveAllTokens(String str, String separatorChars) {
        return org.apache.commons.lang3.StringUtils.splitPreserveAllTokens(str, separatorChars);
    }

    public String[] splitPreserveAllTokens(String str, String separatorChars, int max) {
        return org.apache.commons.lang3.StringUtils.splitPreserveAllTokens(str, separatorChars, max);
    }

    public String[] splitByCharacterType(String str) {
        return org.apache.commons.lang3.StringUtils.splitByCharacterType(str);
    }

    public String[] splitByCharacterTypeCamelCase(String str) {
        return org.apache.commons.lang3.StringUtils.splitByCharacterTypeCamelCase(str);
    }

    public <T> String join(T... elements) {
        return org.apache.commons.lang3.StringUtils.join(elements);
    }

    public String join(Object[] array, char separator) {
        return org.apache.commons.lang3.StringUtils.join(array, separator);
    }

    public String join(long[] array, char separator) {
        return org.apache.commons.lang3.StringUtils.join(array, separator);
    }

    public String join(int[] array, char separator) {
        return org.apache.commons.lang3.StringUtils.join(array, separator);
    }

    public String join(short[] array, char separator) {
        return org.apache.commons.lang3.StringUtils.join(array, separator);
    }

    public String join(byte[] array, char separator) {
        return org.apache.commons.lang3.StringUtils.join(array, separator);
    }

    public String join(char[] array, char separator) {
        return org.apache.commons.lang3.StringUtils.join(array, separator);
    }

    public String join(float[] array, char separator) {
        return org.apache.commons.lang3.StringUtils.join(array, separator);
    }

    public String join(double[] array, char separator) {
        return org.apache.commons.lang3.StringUtils.join(array, separator);
    }

    public String join(Object[] array, char separator, int startIndex, int endIndex) {
        return org.apache.commons.lang3.StringUtils.join(array, separator, startIndex, endIndex);
    }

    public String join(long[] array, char separator, int startIndex, int endIndex) {
        return org.apache.commons.lang3.StringUtils.join(array, separator, startIndex, endIndex);
    }

    public String join(int[] array, char separator, int startIndex, int endIndex) {
        return org.apache.commons.lang3.StringUtils.join(array, separator, startIndex, endIndex);
    }

    public String join(byte[] array, char separator, int startIndex, int endIndex) {
        return org.apache.commons.lang3.StringUtils.join(array, separator, startIndex, endIndex);
    }

    public String join(short[] array, char separator, int startIndex, int endIndex) {
        return org.apache.commons.lang3.StringUtils.join(array, separator, startIndex, endIndex);
    }

    public String join(char[] array, char separator, int startIndex, int endIndex) {
        return org.apache.commons.lang3.StringUtils.join(array, separator, startIndex, endIndex);
    }

    public String join(double[] array, char separator, int startIndex, int endIndex) {
        return org.apache.commons.lang3.StringUtils.join(array, separator, startIndex, endIndex);
    }

    public String join(float[] array, char separator, int startIndex, int endIndex) {
        return org.apache.commons.lang3.StringUtils.join(array, separator, startIndex, endIndex);
    }

    public String join(Object[] array, String separator) {
        return org.apache.commons.lang3.StringUtils.join(array, separator);
    }

    public String join(Object[] array, String separator, int startIndex, int endIndex) {
        return org.apache.commons.lang3.StringUtils.join(array, separator, startIndex, endIndex);
    }

    public String join(Iterator<?> iterator, char separator) {
        return org.apache.commons.lang3.StringUtils.join(iterator, separator);
    }

    public String join(Iterator<?> iterator, String separator) {
        return org.apache.commons.lang3.StringUtils.join(iterator, separator);
    }

    public String join(Iterable<?> iterable, char separator) {
        return org.apache.commons.lang3.StringUtils.join(iterable, separator);
    }

    public String join(Iterable<?> iterable, String separator) {
        return org.apache.commons.lang3.StringUtils.join(iterable, separator);
    }

    public String joinWith(String separator, Object... objects) {
        return org.apache.commons.lang3.StringUtils.joinWith(separator, objects);
    }

    public String deleteWhitespace(String str) {
        return org.apache.commons.lang3.StringUtils.deleteWhitespace(str);
    }

    public String removeStart(String str, String remove) {
        return org.apache.commons.lang3.StringUtils.removeStart(str, remove);
    }

    public String removeStartIgnoreCase(String str, String remove) {
        return org.apache.commons.lang3.StringUtils.removeStartIgnoreCase(str, remove);
    }

    public String removeEnd(String str, String remove) {
        return org.apache.commons.lang3.StringUtils.removeEnd(str, remove);
    }

    public String removeEndIgnoreCase(String str, String remove) {
        return org.apache.commons.lang3.StringUtils.removeEndIgnoreCase(str, remove);
    }

    public String remove(String str, String remove) {
        return org.apache.commons.lang3.StringUtils.remove(str, remove);
    }

    public String removeIgnoreCase(String str, String remove) {
        return org.apache.commons.lang3.StringUtils.removeIgnoreCase(str, remove);
    }

    public String remove(String str, char remove) {
        return org.apache.commons.lang3.StringUtils.remove(str, remove);
    }

    public String removeAll(String text, String regex) {
        return org.apache.commons.lang3.StringUtils.removeAll(text, regex);
    }

    public String removeFirst(String text, String regex) {
        return org.apache.commons.lang3.StringUtils.removeFirst(text, regex);
    }

    public String replaceOnce(String text, String searchString, String replacement) {
        return org.apache.commons.lang3.StringUtils.replaceOnce(text, searchString, replacement);
    }

    public String replaceOnceIgnoreCase(String text, String searchString, String replacement) {
        return org.apache.commons.lang3.StringUtils.replaceOnceIgnoreCase(text, searchString, replacement);
    }

    public String replacePattern(String source, String regex, String replacement) {
        return org.apache.commons.lang3.StringUtils.replacePattern(source, regex, replacement);
    }

    public String removePattern(String source, String regex) {
        return org.apache.commons.lang3.StringUtils.removePattern(source, regex);
    }

    public String replaceAll(String text, String regex, String replacement) {
        return org.apache.commons.lang3.StringUtils.replaceAll(text, regex, replacement);
    }

    public String replaceFirst(String text, String regex, String replacement) {
        return org.apache.commons.lang3.StringUtils.replaceFirst(text, regex, replacement);
    }

    public String replace(String text, String searchString, String replacement) {
        return org.apache.commons.lang3.StringUtils.replace(text, searchString, replacement);
    }

    public String replaceIgnoreCase(String text, String searchString, String replacement) {
        return org.apache.commons.lang3.StringUtils.replaceIgnoreCase(text, searchString, replacement);
    }

    public String replace(String text, String searchString, String replacement, int max) {
        return org.apache.commons.lang3.StringUtils.replace(text, searchString, replacement, max);
    }

    public String replaceIgnoreCase(String text, String searchString, String replacement, int max) {
        return org.apache.commons.lang3.StringUtils.replaceIgnoreCase(text, searchString, replacement, max);
    }

    public String replaceEach(String text, String[] searchList, String[] replacementList) {
        return org.apache.commons.lang3.StringUtils.replaceEach(text, searchList, replacementList);
    }

    public String replaceEachRepeatedly(String text, String[] searchList, String[] replacementList) {
        return org.apache.commons.lang3.StringUtils.replaceEachRepeatedly(text, searchList, replacementList);
    }

    public String replaceChars(String str, char searchChar, char replaceChar) {
        return org.apache.commons.lang3.StringUtils.replaceChars(str, searchChar, replaceChar);
    }

    public String replaceChars(String str, String searchChars, String replaceChars) {
        return org.apache.commons.lang3.StringUtils.replaceChars(str, searchChars, replaceChars);
    }

    public String overlay(String str, String overlay, int start, int end) {
        return org.apache.commons.lang3.StringUtils.overlay(str, overlay, start, end);
    }

    public String chomp(String str) {
        return org.apache.commons.lang3.StringUtils.chomp(str);
    }

    /** @deprecated
     * @param str
     * @param separator */
    @Deprecated
    public String chomp(String str, String separator) {
        return org.apache.commons.lang3.StringUtils.chomp(str, separator);
    }

    public String chop(String str) {
        return org.apache.commons.lang3.StringUtils.chop(str);
    }

    public String repeat(String str, int repeat) {
        return org.apache.commons.lang3.StringUtils.repeat(str, repeat);
    }

    public String repeat(String str, String separator, int repeat) {
        return org.apache.commons.lang3.StringUtils.repeat(str, separator, repeat);
    }

    public String repeat(char ch, int repeat) {
        return org.apache.commons.lang3.StringUtils.repeat(ch, repeat);
    }

    public String rightPad(String str, int size) {
        return org.apache.commons.lang3.StringUtils.rightPad(str, size);
    }

    public String rightPad(String str, int size, char padChar) {
        return org.apache.commons.lang3.StringUtils.rightPad(str, size, padChar);
    }

    public String rightPad(String str, int size, String padStr) {
        return org.apache.commons.lang3.StringUtils.rightPad(str, size, padStr);
    }

    public String leftPad(String str, int size) {
        return org.apache.commons.lang3.StringUtils.leftPad(str, size);
    }

    public String leftPad(String str, int size, char padChar) {
        return org.apache.commons.lang3.StringUtils.leftPad(str, size, padChar);
    }

    public String leftPad(String str, int size, String padStr) {
        return org.apache.commons.lang3.StringUtils.leftPad(str, size, padStr);
    }

    public int length(CharSequence cs) {
        return org.apache.commons.lang3.StringUtils.length(cs);
    }

    public String center(String str, int size) {
        return org.apache.commons.lang3.StringUtils.center(str, size);
    }

    public String center(String str, int size, char padChar) {
        return org.apache.commons.lang3.StringUtils.center(str, size, padChar);
    }

    public String center(String str, int size, String padStr) {
        return org.apache.commons.lang3.StringUtils.center(str, size, padStr);
    }

    public String upperCase(String str) {
        return org.apache.commons.lang3.StringUtils.upperCase(str);
    }

    public String upperCase(String str, Locale locale) {
        return org.apache.commons.lang3.StringUtils.upperCase(str, locale);
    }

    public String lowerCase(String str) {
        return org.apache.commons.lang3.StringUtils.lowerCase(str);
    }

    public String lowerCase(String str, Locale locale) {
        return org.apache.commons.lang3.StringUtils.lowerCase(str, locale);
    }

    public String capitalize(String str) {
        return org.apache.commons.lang3.StringUtils.capitalize(str);
    }

    public String uncapitalize(String str) {
        return org.apache.commons.lang3.StringUtils.uncapitalize(str);
    }

    public String swapCase(String str) {
        return org.apache.commons.lang3.StringUtils.swapCase(str);
    }

    public int countMatches(CharSequence str, CharSequence sub) {
        return org.apache.commons.lang3.StringUtils.countMatches(str, sub);
    }

    public int countMatches(CharSequence str, char ch) {
        return org.apache.commons.lang3.StringUtils.countMatches(str, ch);
    }

    public boolean isAlpha(CharSequence cs) {
        return org.apache.commons.lang3.StringUtils.isAlpha(cs);
    }

    public boolean isAlphaSpace(CharSequence cs) {
        return org.apache.commons.lang3.StringUtils.isAlphaSpace(cs);
    }

    public boolean isAlphanumeric(CharSequence cs) {
        return org.apache.commons.lang3.StringUtils.isAlphanumeric(cs);
    }

    public boolean isAlphanumericSpace(CharSequence cs) {
        return org.apache.commons.lang3.StringUtils.isAlphanumericSpace(cs);
    }

    public boolean isAsciiPrintable(CharSequence cs) {
        return org.apache.commons.lang3.StringUtils.isAsciiPrintable(cs);
    }

    public boolean isNumeric(CharSequence cs) {
        return org.apache.commons.lang3.StringUtils.isNumeric(cs);
    }

    public boolean isNumericSpace(CharSequence cs) {
        return org.apache.commons.lang3.StringUtils.isNumericSpace(cs);
    }

    public boolean isWhitespace(CharSequence cs) {
        return org.apache.commons.lang3.StringUtils.isWhitespace(cs);
    }

    public boolean isAllLowerCase(CharSequence cs) {
        return org.apache.commons.lang3.StringUtils.isAllLowerCase(cs);
    }

    public boolean isAllUpperCase(CharSequence cs) {
        return org.apache.commons.lang3.StringUtils.isAllUpperCase(cs);
    }

    public String defaultString(String str) {
        return org.apache.commons.lang3.StringUtils.defaultString(str);
    }

    public String defaultString(String str, String defaultStr) {
        return org.apache.commons.lang3.StringUtils.defaultString(str, defaultStr);
    }

    public <T extends CharSequence> T defaultIfBlank(T str, T defaultStr) {
        return org.apache.commons.lang3.StringUtils.defaultIfBlank(str, defaultStr);
    }

    public <T extends CharSequence> T defaultIfEmpty(T str, T defaultStr) {
        return org.apache.commons.lang3.StringUtils.defaultIfEmpty(str, defaultStr);
    }

    public String rotate(String str, int shift) {
        return org.apache.commons.lang3.StringUtils.rotate(str, shift);
    }

    public String reverse(String str) {
        return org.apache.commons.lang3.StringUtils.reverse(str);
    }

    public String reverseDelimited(String str, char separatorChar) {
        return org.apache.commons.lang3.StringUtils.reverseDelimited(str, separatorChar);
    }

    public String abbreviate(String str, int maxWidth) {
        return org.apache.commons.lang3.StringUtils.abbreviate(str, maxWidth);
    }

    public String abbreviate(String str, int offset, int maxWidth) {
        return org.apache.commons.lang3.StringUtils.abbreviate(str, offset, maxWidth);
    }

    public String abbreviateMiddle(String str, String middle, int length) {
        return org.apache.commons.lang3.StringUtils.abbreviateMiddle(str, middle, length);
    }

    public String difference(String str1, String str2) {
        return org.apache.commons.lang3.StringUtils.difference(str1, str2);
    }

    public int indexOfDifference(CharSequence cs1, CharSequence cs2) {
        return org.apache.commons.lang3.StringUtils.indexOfDifference(cs1, cs2);
    }

    public int indexOfDifference(CharSequence... css) {
        return org.apache.commons.lang3.StringUtils.indexOfDifference(css);
    }

    public String getCommonPrefix(String... strs) {
        return org.apache.commons.lang3.StringUtils.getCommonPrefix(strs);
    }

    public int getLevenshteinDistance(CharSequence s, CharSequence t) {
        return org.apache.commons.lang3.StringUtils.getLevenshteinDistance(s, t);
    }

    public int getLevenshteinDistance(CharSequence s, CharSequence t, int threshold) {
        return org.apache.commons.lang3.StringUtils.getLevenshteinDistance(s, t, threshold);
    }

    public double getJaroWinklerDistance(CharSequence first, CharSequence second) {
        return org.apache.commons.lang3.StringUtils.getJaroWinklerDistance(first, second);
    }

    public int getFuzzyDistance(CharSequence term, CharSequence query, Locale locale) {
        return org.apache.commons.lang3.StringUtils.getFuzzyDistance(term, query, locale);
    }

    public boolean startsWith(CharSequence str, CharSequence prefix) {
        return org.apache.commons.lang3.StringUtils.startsWith(str, prefix);
    }

    public boolean startsWithIgnoreCase(CharSequence str, CharSequence prefix) {
        return org.apache.commons.lang3.StringUtils.startsWithIgnoreCase(str, prefix);
    }

    public boolean startsWithAny(CharSequence sequence, CharSequence... searchStrings) {
        return org.apache.commons.lang3.StringUtils.startsWithAny(sequence, searchStrings);
    }

    public boolean endsWith(CharSequence str, CharSequence suffix) {
        return org.apache.commons.lang3.StringUtils.endsWith(str, suffix);
    }

    public boolean endsWithIgnoreCase(CharSequence str, CharSequence suffix) {
        return org.apache.commons.lang3.StringUtils.endsWithIgnoreCase(str, suffix);
    }

    public String normalizeSpace(String str) {
        return org.apache.commons.lang3.StringUtils.normalizeSpace(str);
    }

    public boolean endsWithAny(CharSequence sequence, CharSequence... searchStrings) {
        return org.apache.commons.lang3.StringUtils.endsWithAny(sequence, searchStrings);
    }

    public String appendIfMissing(String str, CharSequence suffix, CharSequence... suffixes) {
        return org.apache.commons.lang3.StringUtils.appendIfMissing(str, suffix, suffixes);
    }

    public String appendIfMissingIgnoreCase(String str, CharSequence suffix, CharSequence... suffixes) {
        return org.apache.commons.lang3.StringUtils.appendIfMissingIgnoreCase(str, suffix, suffixes);
    }

    public String prependIfMissing(String str, CharSequence prefix, CharSequence... prefixes) {
        return org.apache.commons.lang3.StringUtils.prependIfMissing(str, prefix, prefixes);
    }

    public String prependIfMissingIgnoreCase(String str, CharSequence prefix, CharSequence... prefixes) {
        return org.apache.commons.lang3.StringUtils.prependIfMissingIgnoreCase(str, prefix, prefixes);
    }

    /** @deprecated
     * @param bytes
     * @param charsetName */
    @Deprecated
    public String toString(byte[] bytes, String charsetName) throws UnsupportedEncodingException {
        return org.apache.commons.lang3.StringUtils.toString(bytes, charsetName);
    }

    public String toEncodedString(byte[] bytes, Charset charset) {
        return org.apache.commons.lang3.StringUtils.toEncodedString(bytes, charset);
    }

    public String wrap(String str, char wrapWith) {
        return org.apache.commons.lang3.StringUtils.wrap(str, wrapWith);
    }

    public String wrap(String str, String wrapWith) {
        return org.apache.commons.lang3.StringUtils.wrap(str, wrapWith);
    }

    public String wrapIfMissing(String str, char wrapWith) {
        return org.apache.commons.lang3.StringUtils.wrapIfMissing(str, wrapWith);
    }

    public String wrapIfMissing(String str, String wrapWith) {
        return org.apache.commons.lang3.StringUtils.wrapIfMissing(str, wrapWith);
    }
}
