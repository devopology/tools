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

import org.apache.commons.codec.digest.DigestUtils;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;

public class DigestUtilsImpl implements org.devopology.tools.DigestUtils {

    @Override
    public MessageDigest getDigest(String algorithm) {
        return DigestUtils.getDigest(algorithm);
    }

    @Override
    public MessageDigest getMd2Digest() {
        return DigestUtils.getMd2Digest();
    }

    @Override
    public MessageDigest getMd5Digest() {
        return DigestUtils.getMd5Digest();
    }

    @Override
    public MessageDigest getSha1Digest() {
        return DigestUtils.getSha1Digest();
    }

    @Override
    public MessageDigest getSha256Digest() {
        return DigestUtils.getSha256Digest();
    }

    @Override
    public MessageDigest getSha384Digest() {
        return DigestUtils.getSha384Digest();
    }

    @Override
    public MessageDigest getSha512Digest() {
        return DigestUtils.getSha512Digest();
    }

    /** @deprecated */
    @Override
    @Deprecated
    public MessageDigest getShaDigest() {
        return DigestUtils.getShaDigest();
    }

    @Override
    public byte[] md2(byte[] data) {
        return DigestUtils.md2(data);
    }

    @Override
    public byte[] md2(InputStream data) throws IOException {
        return DigestUtils.md2(data);
    }

    @Override
    public byte[] md2(String data) {
        return DigestUtils.md2(data);
    }

    @Override
    public String md2Hex(byte[] data) {
        return DigestUtils.md2Hex(data);
    }

    @Override
    public String md2Hex(InputStream data) throws IOException {
        return DigestUtils.md2Hex(data);
    }

    @Override
    public String md2Hex(String data) {
        return DigestUtils.md2Hex(data);
    }

    @Override
    public byte[] md5(byte[] data) {
        return DigestUtils.md5(data);
    }

    @Override
    public byte[] md5(InputStream data) throws IOException {
        return DigestUtils.md5(data);
    }

    @Override
    public byte[] md5(String data) {
        return DigestUtils.md5(data);
    }

    @Override
    public String md5Hex(byte[] data) {
        return DigestUtils.md5Hex(data);
    }

    @Override
    public String md5Hex(InputStream data) throws IOException {
        return DigestUtils.md5Hex(data);
    }

    @Override
    public String md5Hex(String data) {
        return DigestUtils.md5Hex(data);
    }

    /** @deprecated
     * @param data */
    @Override
    @Deprecated
    public byte[] sha(byte[] data) {
        return DigestUtils.sha(data);
    }

    /** @deprecated
     * @param data */
    @Override
    @Deprecated
    public byte[] sha(InputStream data) throws IOException {
        return DigestUtils.sha(data);
    }

    /** @deprecated
     * @param data */
    @Override
    @Deprecated
    public byte[] sha(String data) {
        return DigestUtils.sha(data);
    }

    @Override
    public byte[] sha1(byte[] data) {
        return DigestUtils.sha1(data);
    }

    @Override
    public byte[] sha1(InputStream data) throws IOException {
        return DigestUtils.sha1(data);
    }

    @Override
    public byte[] sha1(String data) {
        return DigestUtils.sha1(data);
    }

    @Override
    public String sha1Hex(byte[] data) {
        return DigestUtils.sha1Hex(data);
    }

    @Override
    public String sha1Hex(InputStream data) throws IOException {
        return DigestUtils.sha1Hex(data);
    }

    @Override
    public String sha1Hex(String data) {
        return DigestUtils.sha1Hex(data);
    }

    @Override
    public byte[] sha256(byte[] data) {
        return DigestUtils.sha256(data);
    }

    @Override
    public byte[] sha256(InputStream data) throws IOException {
        return DigestUtils.sha256(data);
    }

    @Override
    public byte[] sha256(String data) {
        return DigestUtils.sha256(data);
    }

    @Override
    public String sha256Hex(byte[] data) {
        return DigestUtils.sha256Hex(data);
    }

    @Override
    public String sha256Hex(InputStream data) throws IOException {
        return DigestUtils.sha256Hex(data);
    }

    @Override
    public String sha256Hex(String data) {
        return DigestUtils.sha256Hex(data);
    }

    @Override
    public byte[] sha384(byte[] data) {
        return DigestUtils.sha384(data);
    }

    @Override
    public byte[] sha384(InputStream data) throws IOException {
        return DigestUtils.sha384(data);
    }

    @Override
    public byte[] sha384(String data) {
        return DigestUtils.sha384(data);
    }

    @Override
    public String sha384Hex(byte[] data) {
        return DigestUtils.sha384Hex(data);
    }

    @Override
    public String sha384Hex(InputStream data) throws IOException {
        return DigestUtils.sha384Hex(data);
    }

    @Override
    public String sha384Hex(String data) {
        return DigestUtils.sha384Hex(data);
    }

    @Override
    public byte[] sha512(byte[] data) {
        return DigestUtils.sha512(data);
    }

    @Override
    public byte[] sha512(InputStream data) throws IOException {
        return DigestUtils.sha512(data);
    }

    @Override
    public byte[] sha512(String data) {
        return DigestUtils.sha512(data);
    }

    @Override
    public String sha512Hex(byte[] data) {
        return DigestUtils.sha512Hex(data);
    }

    @Override
    public String sha512Hex(InputStream data) throws IOException {
        return DigestUtils.sha512Hex(data);
    }

    @Override
    public String sha512Hex(String data) {
        return DigestUtils.sha512Hex(data);
    }

    /** @deprecated
     * @param data */
    @Override
    @Deprecated
    public String shaHex(byte[] data) {
        return DigestUtils.shaHex(data);
    }

    /** @deprecated
     * @param data */
    @Override
    @Deprecated
    public String shaHex(InputStream data) throws IOException {
        return DigestUtils.shaHex(data);
    }

    /** @deprecated
     * @param data */
    @Override
    @Deprecated
    public String shaHex(String data) {
        return DigestUtils.shaHex(data);
    }

    @Override
    public MessageDigest updateDigest(MessageDigest messageDigest, byte[] valueToDigest) {
        return DigestUtils.updateDigest(messageDigest, valueToDigest);
    }

    @Override
    public MessageDigest updateDigest(MessageDigest digest, InputStream data) throws IOException {
        return DigestUtils.updateDigest(digest, data);
    }

    @Override
    public MessageDigest updateDigest(MessageDigest messageDigest, String valueToDigest) {
        return DigestUtils.updateDigest(messageDigest, valueToDigest);
    }

    private org.apache.commons.codec.digest.DigestUtils digestUtils = null;
}
