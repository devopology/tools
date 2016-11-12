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

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;

public interface DigestUtils {
    MessageDigest getDigest(String algorithm);

    MessageDigest getMd2Digest();

    MessageDigest getMd5Digest();

    MessageDigest getSha1Digest();

    MessageDigest getSha256Digest();

    MessageDigest getSha384Digest();

    MessageDigest getSha512Digest();

    @Deprecated
    MessageDigest getShaDigest();

    byte[] md2(byte[] data);

    byte[] md2(InputStream data) throws IOException;

    byte[] md2(String data);

    String md2Hex(byte[] data);

    String md2Hex(InputStream data) throws IOException;

    String md2Hex(String data);

    byte[] md5(byte[] data);

    byte[] md5(InputStream data) throws IOException;

    byte[] md5(String data);

    String md5Hex(byte[] data);

    String md5Hex(InputStream data) throws IOException;

    String md5Hex(String data);

    @Deprecated
    byte[] sha(byte[] data);

    @Deprecated
    byte[] sha(InputStream data) throws IOException;

    @Deprecated
    byte[] sha(String data);

    byte[] sha1(byte[] data);

    byte[] sha1(InputStream data) throws IOException;

    byte[] sha1(String data);

    String sha1Hex(byte[] data);

    String sha1Hex(InputStream data) throws IOException;

    String sha1Hex(String data);

    byte[] sha256(byte[] data);

    byte[] sha256(InputStream data) throws IOException;

    byte[] sha256(String data);

    String sha256Hex(byte[] data);

    String sha256Hex(InputStream data) throws IOException;

    String sha256Hex(String data);

    byte[] sha384(byte[] data);

    byte[] sha384(InputStream data) throws IOException;

    byte[] sha384(String data);

    String sha384Hex(byte[] data);

    String sha384Hex(InputStream data) throws IOException;

    String sha384Hex(String data);

    byte[] sha512(byte[] data);

    byte[] sha512(InputStream data) throws IOException;

    byte[] sha512(String data);

    String sha512Hex(byte[] data);

    String sha512Hex(InputStream data) throws IOException;

    String sha512Hex(String data);

    @Deprecated
    String shaHex(byte[] data);

    @Deprecated
    String shaHex(InputStream data) throws IOException;

    @Deprecated
    String shaHex(String data);

    MessageDigest updateDigest(MessageDigest messageDigest, byte[] valueToDigest);

    MessageDigest updateDigest(MessageDigest digest, InputStream data) throws IOException;

    MessageDigest updateDigest(MessageDigest messageDigest, String valueToDigest);
}
