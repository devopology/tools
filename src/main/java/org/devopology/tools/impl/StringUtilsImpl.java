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

package org.devopology.tools.impl;

import org.devopology.tools.StringUtils;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Locale;

public class StringUtilsImpl implements StringUtils {

    @Override
    public boolean isEmpty(CharSequence cs) {
        return org.apache.commons.lang3.StringUtils.isEmpty(cs);
    }

    @Override
    public boolean isNotEmpty(CharSequence cs) {
        return org.apache.commons.lang3.StringUtils.isNotEmpty(cs);
    }

    @Override
    public boolean isAnyEmpty(CharSequence... css) {
        return org.apache.commons.lang3.StringUtils.isAnyEmpty(css);
    }

    @Override
    public boolean isNoneEmpty(CharSequence... css) {
        return org.apache.commons.lang3.StringUtils.isNoneEmpty(css);
    }

    @Override
    public boolean isBlank(CharSequence cs) {
        return org.apache.commons.lang3.StringUtils.isBlank(cs);
    }

    @Override
    public boolean isNotBlank(CharSequence cs) {
        return org.apache.commons.lang3.StringUtils.isNotBlank(cs);
    }

    @Override
    public boolean isAnyBlank(CharSequence... css) {
        return org.apache.commons.lang3.StringUtils.isAnyBlank(css);
    }

    @Override
    public boolean isNoneBlank(CharSequence... css) {
        return org.apache.commons.lang3.StringUtils.isNoneBlank(css);
    }

    @Override
    public String trim(String str) {
        return org.apache.commons.lang3.StringUtils.trim(str);
    }

    @Override
    public String trimToNull(String str) {
        return org.apache.commons.lang3.StringUtils.trimToNull(str);
    }

    @Override
    public String trimToEmpty(String str) {
        return org.apache.commons.lang3.StringUtils.trimToEmpty(str);
    }

    @Override
    public String truncate(String str, int maxWidth) {
        return org.apache.commons.lang3.StringUtils.truncate(str, maxWidth);
    }

    @Override
    public String truncate(String str, int offset, int maxWidth) {
        return org.apache.commons.lang3.StringUtils.truncate(str, offset, maxWidth);
    }

    @Override
    public String strip(String str) {
        return org.apache.commons.lang3.StringUtils.strip(str);
    }

    @Override
    public String stripToNull(String str) {
        return org.apache.commons.lang3.StringUtils.stripToNull(str);
    }

    @Override
    public String stripToEmpty(String str) {
        return org.apache.commons.lang3.StringUtils.stripToEmpty(str);
    }

    @Override
    public String strip(String str, String stripChars) {
        return org.apache.commons.lang3.StringUtils.strip(str, stripChars);
    }

    @Override
    public String stripStart(String str, String stripChars) {
        return org.apache.commons.lang3.StringUtils.stripStart(str, stripChars);
    }

    @Override
    public String stripEnd(String str, String stripChars) {
        return org.apache.commons.lang3.StringUtils.stripEnd(str, stripChars);
    }

    @Override
    public String[] stripAll(String... strs) {
        return org.apache.commons.lang3.StringUtils.stripAll(strs);
    }

    @Override
    public String[] stripAll(String[] strs, String stripChars) {
        return org.apache.commons.lang3.StringUtils.stripAll(strs, stripChars);
    }

    @Override
    public String stripAccents(String input) {
        return org.apache.commons.lang3.StringUtils.stripAccents(input);
    }

    @Override
    public boolean equals(CharSequence cs1, CharSequence cs2) {
        return org.apache.commons.lang3.StringUtils.equals(cs1, cs2);
    }

    @Override
    public boolean equalsIgnoreCase(CharSequence str1, CharSequence str2) {
        return org.apache.commons.lang3.StringUtils.equalsIgnoreCase(str1, str2);
    }

    @Override
    public int compare(String str1, String str2) {
        return org.apache.commons.lang3.StringUtils.compare(str1, str2);
    }

    @Override
    public int compare(String str1, String str2, boolean nullIsLess) {
        return org.apache.commons.lang3.StringUtils.compare(str1, str2, nullIsLess);
    }

    @Override
    public int compareIgnoreCase(String str1, String str2) {
        return org.apache.commons.lang3.StringUtils.compareIgnoreCase(str1, str2);
    }

    @Override
    public int compareIgnoreCase(String str1, String str2, boolean nullIsLess) {
        return org.apache.commons.lang3.StringUtils.compareIgnoreCase(str1, str2, nullIsLess);
    }

    @Override
    public boolean equalsAny(CharSequence string, CharSequence... searchStrings) {
        return org.apache.commons.lang3.StringUtils.equalsAny(string, searchStrings);
    }

    @Override
    public boolean equalsAnyIgnoreCase(CharSequence string, CharSequence... searchStrings) {
        return org.apache.commons.lang3.StringUtils.equalsAnyIgnoreCase(string, searchStrings);
    }

    @Override
    public int indexOf(CharSequence seq, int searchChar) {
        return org.apache.commons.lang3.StringUtils.indexOf(seq, searchChar);
    }

    @Override
    public int indexOf(CharSequence seq, int searchChar, int startPos) {
        return org.apache.commons.lang3.StringUtils.indexOf(seq, searchChar, startPos);
    }

    @Override
    public int indexOf(CharSequence seq, CharSequence searchSeq) {
        return org.apache.commons.lang3.StringUtils.indexOf(seq, searchSeq);
    }

    @Override
    public int indexOf(CharSequence seq, CharSequence searchSeq, int startPos) {
        return org.apache.commons.lang3.StringUtils.indexOf(seq, searchSeq, startPos);
    }

    @Override
    public int ordinalIndexOf(CharSequence str, CharSequence searchStr, int ordinal) {
        return org.apache.commons.lang3.StringUtils.ordinalIndexOf(str, searchStr, ordinal);
    }

    @Override
    public int indexOfIgnoreCase(CharSequence str, CharSequence searchStr) {
        return org.apache.commons.lang3.StringUtils.indexOfIgnoreCase(str, searchStr);
    }

    @Override
    public int indexOfIgnoreCase(CharSequence str, CharSequence searchStr, int startPos) {
        return org.apache.commons.lang3.StringUtils.indexOfIgnoreCase(str, searchStr, startPos);
    }

    @Override
    public int lastIndexOf(CharSequence seq, int searchChar) {
        return org.apache.commons.lang3.StringUtils.lastIndexOf(seq, searchChar);
    }

    @Override
    public int lastIndexOf(CharSequence seq, int searchChar, int startPos) {
        return org.apache.commons.lang3.StringUtils.lastIndexOf(seq, searchChar, startPos);
    }

    @Override
    public int lastIndexOf(CharSequence seq, CharSequence searchSeq) {
        return org.apache.commons.lang3.StringUtils.lastIndexOf(seq, searchSeq);
    }

    @Override
    public int lastOrdinalIndexOf(CharSequence str, CharSequence searchStr, int ordinal) {
        return org.apache.commons.lang3.StringUtils.lastOrdinalIndexOf(str, searchStr, ordinal);
    }

    @Override
    public int lastIndexOf(CharSequence seq, CharSequence searchSeq, int startPos) {
        return org.apache.commons.lang3.StringUtils.lastIndexOf(seq, searchSeq, startPos);
    }

    @Override
    public int lastIndexOfIgnoreCase(CharSequence str, CharSequence searchStr) {
        return org.apache.commons.lang3.StringUtils.lastIndexOfIgnoreCase(str, searchStr);
    }

    @Override
    public int lastIndexOfIgnoreCase(CharSequence str, CharSequence searchStr, int startPos) {
        return org.apache.commons.lang3.StringUtils.lastIndexOfIgnoreCase(str, searchStr, startPos);
    }

    @Override
    public boolean contains(CharSequence seq, int searchChar) {
        return org.apache.commons.lang3.StringUtils.contains(seq, searchChar);
    }

    @Override
    public boolean contains(CharSequence seq, CharSequence searchSeq) {
        return org.apache.commons.lang3.StringUtils.contains(seq, searchSeq);
    }

    @Override
    public boolean containsIgnoreCase(CharSequence str, CharSequence searchStr) {
        return org.apache.commons.lang3.StringUtils.containsIgnoreCase(str, searchStr);
    }

    @Override
    public boolean containsWhitespace(CharSequence seq) {
        return org.apache.commons.lang3.StringUtils.containsWhitespace(seq);
    }

    @Override
    public int indexOfAny(CharSequence cs, char... searchChars) {
        return org.apache.commons.lang3.StringUtils.indexOfAny(cs, searchChars);
    }

    @Override
    public int indexOfAny(CharSequence cs, String searchChars) {
        return org.apache.commons.lang3.StringUtils.indexOfAny(cs, searchChars);
    }

    @Override
    public boolean containsAny(CharSequence cs, char... searchChars) {
        return org.apache.commons.lang3.StringUtils.containsAny(cs, searchChars);
    }

    @Override
    public boolean containsAny(CharSequence cs, CharSequence searchChars) {
        return org.apache.commons.lang3.StringUtils.containsAny(cs, searchChars);
    }

    @Override
    public boolean containsAny(CharSequence cs, CharSequence... searchCharSequences) {
        return org.apache.commons.lang3.StringUtils.containsAny(cs, searchCharSequences);
    }

    @Override
    public int indexOfAnyBut(CharSequence cs, char... searchChars) {
        return org.apache.commons.lang3.StringUtils.indexOfAnyBut(cs, searchChars);
    }

    @Override
    public int indexOfAnyBut(CharSequence seq, CharSequence searchChars) {
        return org.apache.commons.lang3.StringUtils.indexOfAnyBut(seq, searchChars);
    }

    @Override
    public boolean containsOnly(CharSequence cs, char... valid) {
        return org.apache.commons.lang3.StringUtils.containsOnly(cs, valid);
    }

    @Override
    public boolean containsOnly(CharSequence cs, String validChars) {
        return org.apache.commons.lang3.StringUtils.containsOnly(cs, validChars);
    }

    @Override
    public boolean containsNone(CharSequence cs, char... searchChars) {
        return org.apache.commons.lang3.StringUtils.containsNone(cs, searchChars);
    }

    @Override
    public boolean containsNone(CharSequence cs, String invalidChars) {
        return org.apache.commons.lang3.StringUtils.containsNone(cs, invalidChars);
    }

    @Override
    public int indexOfAny(CharSequence str, CharSequence... searchStrs) {
        return org.apache.commons.lang3.StringUtils.indexOfAny(str, searchStrs);
    }

    @Override
    public int lastIndexOfAny(CharSequence str, CharSequence... searchStrs) {
        return org.apache.commons.lang3.StringUtils.lastIndexOfAny(str, searchStrs);
    }

    @Override
    public String substring(String str, int start) {
        return org.apache.commons.lang3.StringUtils.substring(str, start);
    }

    @Override
    public String substring(String str, int start, int end) {
        return org.apache.commons.lang3.StringUtils.substring(str, start, end);
    }

    @Override
    public String left(String str, int len) {
        return org.apache.commons.lang3.StringUtils.left(str, len);
    }

    @Override
    public String right(String str, int len) {
        return org.apache.commons.lang3.StringUtils.right(str, len);
    }

    @Override
    public String mid(String str, int pos, int len) {
        return org.apache.commons.lang3.StringUtils.mid(str, pos, len);
    }

    @Override
    public String substringBefore(String str, String separator) {
        return org.apache.commons.lang3.StringUtils.substringBefore(str, separator);
    }

    @Override
    public String substringAfter(String str, String separator) {
        return org.apache.commons.lang3.StringUtils.substringAfter(str, separator);
    }

    @Override
    public String substringBeforeLast(String str, String separator) {
        return org.apache.commons.lang3.StringUtils.substringBeforeLast(str, separator);
    }

    @Override
    public String substringAfterLast(String str, String separator) {
        return org.apache.commons.lang3.StringUtils.substringAfterLast(str, separator);
    }

    @Override
    public String substringBetween(String str, String tag) {
        return org.apache.commons.lang3.StringUtils.substringBetween(str, tag);
    }

    @Override
    public String substringBetween(String str, String open, String close) {
        return org.apache.commons.lang3.StringUtils.substringBetween(str, open, close);
    }

    @Override
    public String[] substringsBetween(String str, String open, String close) {
        return org.apache.commons.lang3.StringUtils.substringsBetween(str, open, close);
    }

    @Override
    public String[] split(String str) {
        return org.apache.commons.lang3.StringUtils.split(str);
    }

    @Override
    public String[] split(String str, char separatorChar) {
        return org.apache.commons.lang3.StringUtils.split(str, separatorChar);
    }

    @Override
    public String[] split(String str, String separatorChars) {
        return org.apache.commons.lang3.StringUtils.split(str, separatorChars);
    }

    @Override
    public String[] split(String str, String separatorChars, int max) {
        return org.apache.commons.lang3.StringUtils.split(str, separatorChars, max);
    }

    @Override
    public String[] splitByWholeSeparator(String str, String separator) {
        return org.apache.commons.lang3.StringUtils.splitByWholeSeparator(str, separator);
    }

    @Override
    public String[] splitByWholeSeparator(String str, String separator, int max) {
        return org.apache.commons.lang3.StringUtils.splitByWholeSeparator(str, separator, max);
    }

    @Override
    public String[] splitByWholeSeparatorPreserveAllTokens(String str, String separator) {
        return org.apache.commons.lang3.StringUtils.splitByWholeSeparatorPreserveAllTokens(str, separator);
    }

    @Override
    public String[] splitByWholeSeparatorPreserveAllTokens(String str, String separator, int max) {
        return org.apache.commons.lang3.StringUtils.splitByWholeSeparatorPreserveAllTokens(str, separator, max);
    }

    @Override
    public String[] splitPreserveAllTokens(String str) {
        return org.apache.commons.lang3.StringUtils.splitPreserveAllTokens(str);
    }

    @Override
    public String[] splitPreserveAllTokens(String str, char separatorChar) {
        return org.apache.commons.lang3.StringUtils.splitPreserveAllTokens(str, separatorChar);
    }

    @Override
    public String[] splitPreserveAllTokens(String str, String separatorChars) {
        return org.apache.commons.lang3.StringUtils.splitPreserveAllTokens(str, separatorChars);
    }

    @Override
    public String[] splitPreserveAllTokens(String str, String separatorChars, int max) {
        return org.apache.commons.lang3.StringUtils.splitPreserveAllTokens(str, separatorChars, max);
    }

    @Override
    public String[] splitByCharacterType(String str) {
        return org.apache.commons.lang3.StringUtils.splitByCharacterType(str);
    }

    @Override
    public String[] splitByCharacterTypeCamelCase(String str) {
        return org.apache.commons.lang3.StringUtils.splitByCharacterTypeCamelCase(str);
    }

    @Override
    public <T> String join(T... elements) {
        return org.apache.commons.lang3.StringUtils.join(elements);
    }

    @Override
    public String join(Object[] array, char separator) {
        return org.apache.commons.lang3.StringUtils.join(array, separator);
    }

    @Override
    public String join(long[] array, char separator) {
        return org.apache.commons.lang3.StringUtils.join(array, separator);
    }

    @Override
    public String join(int[] array, char separator) {
        return org.apache.commons.lang3.StringUtils.join(array, separator);
    }

    @Override
    public String join(short[] array, char separator) {
        return org.apache.commons.lang3.StringUtils.join(array, separator);
    }

    @Override
    public String join(byte[] array, char separator) {
        return org.apache.commons.lang3.StringUtils.join(array, separator);
    }

    @Override
    public String join(char[] array, char separator) {
        return org.apache.commons.lang3.StringUtils.join(array, separator);
    }

    @Override
    public String join(float[] array, char separator) {
        return org.apache.commons.lang3.StringUtils.join(array, separator);
    }

    @Override
    public String join(double[] array, char separator) {
        return org.apache.commons.lang3.StringUtils.join(array, separator);
    }

    @Override
    public String join(Object[] array, char separator, int startIndex, int endIndex) {
        return org.apache.commons.lang3.StringUtils.join(array, separator, startIndex, endIndex);
    }

    @Override
    public String join(long[] array, char separator, int startIndex, int endIndex) {
        return org.apache.commons.lang3.StringUtils.join(array, separator, startIndex, endIndex);
    }

    @Override
    public String join(int[] array, char separator, int startIndex, int endIndex) {
        return org.apache.commons.lang3.StringUtils.join(array, separator, startIndex, endIndex);
    }

    @Override
    public String join(byte[] array, char separator, int startIndex, int endIndex) {
        return org.apache.commons.lang3.StringUtils.join(array, separator, startIndex, endIndex);
    }

    @Override
    public String join(short[] array, char separator, int startIndex, int endIndex) {
        return org.apache.commons.lang3.StringUtils.join(array, separator, startIndex, endIndex);
    }

    @Override
    public String join(char[] array, char separator, int startIndex, int endIndex) {
        return org.apache.commons.lang3.StringUtils.join(array, separator, startIndex, endIndex);
    }

    @Override
    public String join(double[] array, char separator, int startIndex, int endIndex) {
        return org.apache.commons.lang3.StringUtils.join(array, separator, startIndex, endIndex);
    }

    @Override
    public String join(float[] array, char separator, int startIndex, int endIndex) {
        return org.apache.commons.lang3.StringUtils.join(array, separator, startIndex, endIndex);
    }

    @Override
    public String join(Object[] array, String separator) {
        return org.apache.commons.lang3.StringUtils.join(array, separator);
    }

    @Override
    public String join(Object[] array, String separator, int startIndex, int endIndex) {
        return org.apache.commons.lang3.StringUtils.join(array, separator, startIndex, endIndex);
    }

    @Override
    public String join(Iterator<?> iterator, char separator) {
        return org.apache.commons.lang3.StringUtils.join(iterator, separator);
    }

    @Override
    public String join(Iterator<?> iterator, String separator) {
        return org.apache.commons.lang3.StringUtils.join(iterator, separator);
    }

    @Override
    public String join(Iterable<?> iterable, char separator) {
        return org.apache.commons.lang3.StringUtils.join(iterable, separator);
    }

    @Override
    public String join(Iterable<?> iterable, String separator) {
        return org.apache.commons.lang3.StringUtils.join(iterable, separator);
    }

    @Override
    public String joinWith(String separator, Object... objects) {
        return org.apache.commons.lang3.StringUtils.joinWith(separator, objects);
    }

    @Override
    public String deleteWhitespace(String str) {
        return org.apache.commons.lang3.StringUtils.deleteWhitespace(str);
    }

    @Override
    public String removeStart(String str, String remove) {
        return org.apache.commons.lang3.StringUtils.removeStart(str, remove);
    }

    @Override
    public String removeStartIgnoreCase(String str, String remove) {
        return org.apache.commons.lang3.StringUtils.removeStartIgnoreCase(str, remove);
    }

    @Override
    public String removeEnd(String str, String remove) {
        return org.apache.commons.lang3.StringUtils.removeEnd(str, remove);
    }

    @Override
    public String removeEndIgnoreCase(String str, String remove) {
        return org.apache.commons.lang3.StringUtils.removeEndIgnoreCase(str, remove);
    }

    @Override
    public String remove(String str, String remove) {
        return org.apache.commons.lang3.StringUtils.remove(str, remove);
    }

    @Override
    public String removeIgnoreCase(String str, String remove) {
        return org.apache.commons.lang3.StringUtils.removeIgnoreCase(str, remove);
    }

    @Override
    public String remove(String str, char remove) {
        return org.apache.commons.lang3.StringUtils.remove(str, remove);
    }

    @Override
    public String removeAll(String text, String regex) {
        return org.apache.commons.lang3.StringUtils.removeAll(text, regex);
    }

    @Override
    public String removeFirst(String text, String regex) {
        return org.apache.commons.lang3.StringUtils.removeFirst(text, regex);
    }

    @Override
    public String replaceOnce(String text, String searchString, String replacement) {
        return org.apache.commons.lang3.StringUtils.replaceOnce(text, searchString, replacement);
    }

    @Override
    public String replaceOnceIgnoreCase(String text, String searchString, String replacement) {
        return org.apache.commons.lang3.StringUtils.replaceOnceIgnoreCase(text, searchString, replacement);
    }

    @Override
    public String replacePattern(String source, String regex, String replacement) {
        return org.apache.commons.lang3.StringUtils.replacePattern(source, regex, replacement);
    }

    @Override
    public String removePattern(String source, String regex) {
        return org.apache.commons.lang3.StringUtils.removePattern(source, regex);
    }

    @Override
    public String replaceAll(String text, String regex, String replacement) {
        return org.apache.commons.lang3.StringUtils.replaceAll(text, regex, replacement);
    }

    @Override
    public String replaceFirst(String text, String regex, String replacement) {
        return org.apache.commons.lang3.StringUtils.replaceFirst(text, regex, replacement);
    }

    @Override
    public String replace(String text, String searchString, String replacement) {
        return org.apache.commons.lang3.StringUtils.replace(text, searchString, replacement);
    }

    @Override
    public String replaceIgnoreCase(String text, String searchString, String replacement) {
        return org.apache.commons.lang3.StringUtils.replaceIgnoreCase(text, searchString, replacement);
    }

    @Override
    public String replace(String text, String searchString, String replacement, int max) {
        return org.apache.commons.lang3.StringUtils.replace(text, searchString, replacement, max);
    }

    @Override
    public String replaceIgnoreCase(String text, String searchString, String replacement, int max) {
        return org.apache.commons.lang3.StringUtils.replaceIgnoreCase(text, searchString, replacement, max);
    }

    @Override
    public String replaceEach(String text, String[] searchList, String[] replacementList) {
        return org.apache.commons.lang3.StringUtils.replaceEach(text, searchList, replacementList);
    }

    @Override
    public String replaceEachRepeatedly(String text, String[] searchList, String[] replacementList) {
        return org.apache.commons.lang3.StringUtils.replaceEachRepeatedly(text, searchList, replacementList);
    }

    @Override
    public String replaceChars(String str, char searchChar, char replaceChar) {
        return org.apache.commons.lang3.StringUtils.replaceChars(str, searchChar, replaceChar);
    }

    @Override
    public String replaceChars(String str, String searchChars, String replaceChars) {
        return org.apache.commons.lang3.StringUtils.replaceChars(str, searchChars, replaceChars);
    }

    @Override
    public String overlay(String str, String overlay, int start, int end) {
        return org.apache.commons.lang3.StringUtils.overlay(str, overlay, start, end);
    }

    @Override
    public String chomp(String str) {
        return org.apache.commons.lang3.StringUtils.chomp(str);
    }

    /** @deprecated
     * @param str
     * @param separator */
    @Override
    @Deprecated
    public String chomp(String str, String separator) {
        return org.apache.commons.lang3.StringUtils.chomp(str, separator);
    }

    @Override
    public String chop(String str) {
        return org.apache.commons.lang3.StringUtils.chop(str);
    }

    @Override
    public String repeat(String str, int repeat) {
        return org.apache.commons.lang3.StringUtils.repeat(str, repeat);
    }

    @Override
    public String repeat(String str, String separator, int repeat) {
        return org.apache.commons.lang3.StringUtils.repeat(str, separator, repeat);
    }

    @Override
    public String repeat(char ch, int repeat) {
        return org.apache.commons.lang3.StringUtils.repeat(ch, repeat);
    }

    @Override
    public String rightPad(String str, int size) {
        return org.apache.commons.lang3.StringUtils.rightPad(str, size);
    }

    @Override
    public String rightPad(String str, int size, char padChar) {
        return org.apache.commons.lang3.StringUtils.rightPad(str, size, padChar);
    }

    @Override
    public String rightPad(String str, int size, String padStr) {
        return org.apache.commons.lang3.StringUtils.rightPad(str, size, padStr);
    }

    @Override
    public String leftPad(String str, int size) {
        return org.apache.commons.lang3.StringUtils.leftPad(str, size);
    }

    @Override
    public String leftPad(String str, int size, char padChar) {
        return org.apache.commons.lang3.StringUtils.leftPad(str, size, padChar);
    }

    @Override
    public String leftPad(String str, int size, String padStr) {
        return org.apache.commons.lang3.StringUtils.leftPad(str, size, padStr);
    }

    @Override
    public int length(CharSequence cs) {
        return org.apache.commons.lang3.StringUtils.length(cs);
    }

    @Override
    public String center(String str, int size) {
        return org.apache.commons.lang3.StringUtils.center(str, size);
    }

    @Override
    public String center(String str, int size, char padChar) {
        return org.apache.commons.lang3.StringUtils.center(str, size, padChar);
    }

    @Override
    public String center(String str, int size, String padStr) {
        return org.apache.commons.lang3.StringUtils.center(str, size, padStr);
    }

    @Override
    public String upperCase(String str) {
        return org.apache.commons.lang3.StringUtils.upperCase(str);
    }

    @Override
    public String upperCase(String str, Locale locale) {
        return org.apache.commons.lang3.StringUtils.upperCase(str, locale);
    }

    @Override
    public String lowerCase(String str) {
        return org.apache.commons.lang3.StringUtils.lowerCase(str);
    }

    @Override
    public String lowerCase(String str, Locale locale) {
        return org.apache.commons.lang3.StringUtils.lowerCase(str, locale);
    }

    @Override
    public String capitalize(String str) {
        return org.apache.commons.lang3.StringUtils.capitalize(str);
    }

    @Override
    public String uncapitalize(String str) {
        return org.apache.commons.lang3.StringUtils.uncapitalize(str);
    }

    @Override
    public String swapCase(String str) {
        return org.apache.commons.lang3.StringUtils.swapCase(str);
    }

    @Override
    public int countMatches(CharSequence str, CharSequence sub) {
        return org.apache.commons.lang3.StringUtils.countMatches(str, sub);
    }

    @Override
    public int countMatches(CharSequence str, char ch) {
        return org.apache.commons.lang3.StringUtils.countMatches(str, ch);
    }

    @Override
    public boolean isAlpha(CharSequence cs) {
        return org.apache.commons.lang3.StringUtils.isAlpha(cs);
    }

    @Override
    public boolean isAlphaSpace(CharSequence cs) {
        return org.apache.commons.lang3.StringUtils.isAlphaSpace(cs);
    }

    @Override
    public boolean isAlphanumeric(CharSequence cs) {
        return org.apache.commons.lang3.StringUtils.isAlphanumeric(cs);
    }

    @Override
    public boolean isAlphanumericSpace(CharSequence cs) {
        return org.apache.commons.lang3.StringUtils.isAlphanumericSpace(cs);
    }

    @Override
    public boolean isAsciiPrintable(CharSequence cs) {
        return org.apache.commons.lang3.StringUtils.isAsciiPrintable(cs);
    }

    @Override
    public boolean isNumeric(CharSequence cs) {
        return org.apache.commons.lang3.StringUtils.isNumeric(cs);
    }

    @Override
    public boolean isNumericSpace(CharSequence cs) {
        return org.apache.commons.lang3.StringUtils.isNumericSpace(cs);
    }

    @Override
    public boolean isWhitespace(CharSequence cs) {
        return org.apache.commons.lang3.StringUtils.isWhitespace(cs);
    }

    @Override
    public boolean isAllLowerCase(CharSequence cs) {
        return org.apache.commons.lang3.StringUtils.isAllLowerCase(cs);
    }

    @Override
    public boolean isAllUpperCase(CharSequence cs) {
        return org.apache.commons.lang3.StringUtils.isAllUpperCase(cs);
    }

    @Override
    public String defaultString(String str) {
        return org.apache.commons.lang3.StringUtils.defaultString(str);
    }

    @Override
    public String defaultString(String str, String defaultStr) {
        return org.apache.commons.lang3.StringUtils.defaultString(str, defaultStr);
    }

    @Override
    public <T extends CharSequence> T defaultIfBlank(T str, T defaultStr) {
        return org.apache.commons.lang3.StringUtils.defaultIfBlank(str, defaultStr);
    }

    @Override
    public <T extends CharSequence> T defaultIfEmpty(T str, T defaultStr) {
        return org.apache.commons.lang3.StringUtils.defaultIfEmpty(str, defaultStr);
    }

    @Override
    public String rotate(String str, int shift) {
        return org.apache.commons.lang3.StringUtils.rotate(str, shift);
    }

    @Override
    public String reverse(String str) {
        return org.apache.commons.lang3.StringUtils.reverse(str);
    }

    @Override
    public String reverseDelimited(String str, char separatorChar) {
        return org.apache.commons.lang3.StringUtils.reverseDelimited(str, separatorChar);
    }

    @Override
    public String abbreviate(String str, int maxWidth) {
        return org.apache.commons.lang3.StringUtils.abbreviate(str, maxWidth);
    }

    @Override
    public String abbreviate(String str, int offset, int maxWidth) {
        return org.apache.commons.lang3.StringUtils.abbreviate(str, offset, maxWidth);
    }

    @Override
    public String abbreviateMiddle(String str, String middle, int length) {
        return org.apache.commons.lang3.StringUtils.abbreviateMiddle(str, middle, length);
    }

    @Override
    public String difference(String str1, String str2) {
        return org.apache.commons.lang3.StringUtils.difference(str1, str2);
    }

    @Override
    public int indexOfDifference(CharSequence cs1, CharSequence cs2) {
        return org.apache.commons.lang3.StringUtils.indexOfDifference(cs1, cs2);
    }

    @Override
    public int indexOfDifference(CharSequence... css) {
        return org.apache.commons.lang3.StringUtils.indexOfDifference(css);
    }

    @Override
    public String getCommonPrefix(String... strs) {
        return org.apache.commons.lang3.StringUtils.getCommonPrefix(strs);
    }

    @Override
    public int getLevenshteinDistance(CharSequence s, CharSequence t) {
        return org.apache.commons.lang3.StringUtils.getLevenshteinDistance(s, t);
    }

    @Override
    public int getLevenshteinDistance(CharSequence s, CharSequence t, int threshold) {
        return org.apache.commons.lang3.StringUtils.getLevenshteinDistance(s, t, threshold);
    }

    @Override
    public double getJaroWinklerDistance(CharSequence first, CharSequence second) {
        return org.apache.commons.lang3.StringUtils.getJaroWinklerDistance(first, second);
    }

    @Override
    public int getFuzzyDistance(CharSequence term, CharSequence query, Locale locale) {
        return org.apache.commons.lang3.StringUtils.getFuzzyDistance(term, query, locale);
    }

    @Override
    public boolean startsWith(CharSequence str, CharSequence prefix) {
        return org.apache.commons.lang3.StringUtils.startsWith(str, prefix);
    }

    @Override
    public boolean startsWithIgnoreCase(CharSequence str, CharSequence prefix) {
        return org.apache.commons.lang3.StringUtils.startsWithIgnoreCase(str, prefix);
    }

    @Override
    public boolean startsWithAny(CharSequence sequence, CharSequence... searchStrings) {
        return org.apache.commons.lang3.StringUtils.startsWithAny(sequence, searchStrings);
    }

    @Override
    public boolean endsWith(CharSequence str, CharSequence suffix) {
        return org.apache.commons.lang3.StringUtils.endsWith(str, suffix);
    }

    @Override
    public boolean endsWithIgnoreCase(CharSequence str, CharSequence suffix) {
        return org.apache.commons.lang3.StringUtils.endsWithIgnoreCase(str, suffix);
    }

    @Override
    public String normalizeSpace(String str) {
        return org.apache.commons.lang3.StringUtils.normalizeSpace(str);
    }

    @Override
    public boolean endsWithAny(CharSequence sequence, CharSequence... searchStrings) {
        return org.apache.commons.lang3.StringUtils.endsWithAny(sequence, searchStrings);
    }

    @Override
    public String appendIfMissing(String str, CharSequence suffix, CharSequence... suffixes) {
        return org.apache.commons.lang3.StringUtils.appendIfMissing(str, suffix, suffixes);
    }

    @Override
    public String appendIfMissingIgnoreCase(String str, CharSequence suffix, CharSequence... suffixes) {
        return org.apache.commons.lang3.StringUtils.appendIfMissingIgnoreCase(str, suffix, suffixes);
    }

    @Override
    public String prependIfMissing(String str, CharSequence prefix, CharSequence... prefixes) {
        return org.apache.commons.lang3.StringUtils.prependIfMissing(str, prefix, prefixes);
    }

    @Override
    public String prependIfMissingIgnoreCase(String str, CharSequence prefix, CharSequence... prefixes) {
        return org.apache.commons.lang3.StringUtils.prependIfMissingIgnoreCase(str, prefix, prefixes);
    }

    /** @deprecated
     * @param bytes
     * @param charsetName */
    @Override
    @Deprecated
    public String toString(byte[] bytes, String charsetName) throws UnsupportedEncodingException {
        return org.apache.commons.lang3.StringUtils.toString(bytes, charsetName);
    }

    @Override
    public String toEncodedString(byte[] bytes, Charset charset) {
        return org.apache.commons.lang3.StringUtils.toEncodedString(bytes, charset);
    }

    @Override
    public String wrap(String str, char wrapWith) {
        return org.apache.commons.lang3.StringUtils.wrap(str, wrapWith);
    }

    @Override
    public String wrap(String str, String wrapWith) {
        return org.apache.commons.lang3.StringUtils.wrap(str, wrapWith);
    }

    @Override
    public String wrapIfMissing(String str, char wrapWith) {
        return org.apache.commons.lang3.StringUtils.wrapIfMissing(str, wrapWith);
    }

    @Override
    public String wrapIfMissing(String str, String wrapWith) {
        return org.apache.commons.lang3.StringUtils.wrapIfMissing(str, wrapWith);
    }
}
