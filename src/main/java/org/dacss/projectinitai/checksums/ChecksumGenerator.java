package org.dacss.projectinitai.checksums;

import uk.ac.manchester.tornado.api.*;
import uk.ac.manchester.tornado.api.annotations.Parallel;
import uk.ac.manchester.tornado.api.enums.DataTransferMode;
import uk.ac.manchester.tornado.api.exceptions.TornadoExecutionPlanException;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * <h1>{@link ChecksumGenerator}</h1>
 * Generates a SHA-512 checksum for a given LLM.
 * The implementation is based on the SHA-512 algorithm as described in the
 * FIPS PUB 180-4 Secure Hash Standard (SHS).
 * We use the raw algorithm for maximum performance.
 */
public class ChecksumGenerator {

    /**
     * The SHA-512 constants used in the algorithm.
     */
    private static final long[] K = {
        0x428a2f98d728ae22L, 0x7137449123ef65cdL, 0xb5c0fbcfec4d3b2fL, 0xe9b5dba58189dbbcL,
        0x3956c25bf348b538L, 0x59f111f1b605d019L, 0x923f82a4af194f9bL, 0xab1c5ed5da6d8118L,
        0xd807aa98a3030242L, 0x12835b0145706fbeL, 0x243185be4ee4b28cL, 0x550c7dc3d5ffb4e2L,
        0x72be5d74f27b896fL, 0x80deb1fe3b1696b1L, 0x9bdc06a725c71235L, 0xc19bf174cf692694L,
        0xe49b69c19ef14ad2L, 0xefbe4786384f25e3L, 0x0fc19dc68b8cd5b5L, 0x240ca1cc77ac9c65L,
        0x2de92c6f592b0275L, 0x4a7484aa6ea6e483L, 0x5cb0a9dcbd41fbd4L, 0x76f988da831153b5L,
        0x983e5152ee66dfabL, 0xa831c66d2db43210L, 0xb00327c898fb213fL, 0xbf597fc7beef0ee4L,
        0xc6e00bf33da88fc2L, 0xd5a79147930aa725L, 0x06ca6351e003826fL, 0x142929670a0e6e70L,
        0x27b70a8546d22ffcL, 0x2e1b21385c26c926L, 0x4d2c6dfc5ac42aedL, 0x53380d139d95b3dfL,
        0x650a73548baf63deL, 0x766a0abb3c77b2a8L, 0x81c2c92e47edaee6L, 0x92722c851482353bL,
        0xa2bfe8a14cf10364L, 0xa81a664bbc423001L, 0xc24b8b70d0f89791L, 0xc76c51a30654be30L,
        0xd192e819d6ef5218L, 0xd69906245565a910L, 0xf40e35855771202aL, 0x106aa07032bbd1b8L,
        0x19a4c116b8d2d0c8L, 0x1e376c085141ab53L, 0x2748774cdf8eeb99L, 0x34b0bcb5e19b48a8L,
        0x391c0cb3c5c95a63L, 0x4ed8aa4ae3418acbL, 0x5b9cca4f7763e373L, 0x682e6ff3d6b2b8a3L,
        0x748f82ee5defb2fcL, 0x78a5636f43172f60L, 0x84c87814a1f0ab72L, 0x8cc702081a6439ecL,
        0x90befffa23631e28L, 0xa4506cebde82bde9L, 0xbef9a3f7b2c67915L, 0xc67178f2e372532bL,
        0xca273eceea26619cL, 0xd186b8c721c0c207L, 0xeada7dd6cde0eb1eL, 0xf57d4f7fee6ed178L,
        0x06f067aa72176fbaL, 0x0a637dc5a2c898a6L, 0x113f9804bef90daeL, 0x1b710b35131c471bL,
        0x28db77f523047d84L, 0x32caab7b40c72493L, 0x3c9ebe0a15c9bebcL, 0x431d67c49c100d4cL,
        0x4cc5d4becb3e42b6L, 0x597f299cfc657e2aL, 0x5fcb6fab3ad6faecL, 0x6c44198c4a475817L
    };

    /**
     * The initial hash values used in the SHA-512 algorithm.
     */
    private static final long[] H = {
        0x6a09e667f3bcc908L, 0xbb67ae8584caa73bL, 0x3c6ef372fe94f82bL, 0xa54ff53a5f1d36f1L,
        0x510e527fade682d1L, 0x9b05688c2b3e6c1fL, 0x1f83d9abfb41bd6bL, 0x5be0cd19137e2179L
    };

    /**
     * Generates a SHA-512 checksum for the given file.
     * @param filePath the path to the file
     * @return the SHA-512 checksum as a hexadecimal string
     * @throws IOException if an I/O error occurs
     */
    public static String generateSHA512(String filePath) throws IOException {
        long[] h = H.clone();
        try (FileInputStream fis = new FileInputStream(filePath)) {
            byte[] buffer = new byte[128];
            int bytesRead;
            long totalBytes = 0;

            while ((bytesRead = fis.read(buffer)) != -1) {
                totalBytes += bytesRead;
                if (bytesRead < 128) {
                    buffer = padMessage(buffer, bytesRead, totalBytes * 8);
                }
                processBlock(buffer, h);
            }
        }

        StringBuilder hash = new StringBuilder();
        for (long value : h) {
            hash.append(String.format("%016x", value));
        }
        return hash.toString();
    }

    /**
     * Pads the message according to the SHA-512 padding rules.
     * @param buffer    the message buffer
     * @param bytesRead the number of bytes read
     * @param bitLength the length of the message in bits
     * @return the padded message
     */
    private static byte[] padMessage(byte[] buffer, int bytesRead, long bitLength) {
        ByteBuffer paddedBuffer = ByteBuffer.allocate(128).order(ByteOrder.BIG_ENDIAN);
        paddedBuffer.put(buffer, 0, bytesRead);
        paddedBuffer.put((byte) 0x80);
        while (paddedBuffer.position() < 112) {
            paddedBuffer.put((byte) 0x00);
        }
        paddedBuffer.putLong(bitLength);
        return paddedBuffer.array();
    }

    /**
     * Processes a single 1024-bit block of the message.
     * @param block the message block
     * @param h     the current hash values
     */
    private static void processBlock(byte[] block, long[] h) {
        // Initialize the message schedule array
        long[] w = new long[80];

        // Wrap the block in a ByteBuffer with big-endian order
        ByteBuffer buffer = ByteBuffer.wrap(block).order(ByteOrder.BIG_ENDIAN);

        // Populate the first 16 words of the message schedule array
        for (int i = 0; i < 16; i++) {
            w[i] = buffer.getLong();
        }

        // Extend the first 16 words into the remaining 64 words of the message schedule array
        for (int i = 16; i < 80; i++) {
            w[i] = sigma1(w[i - 2]) + w[i - 7] + sigma0(w[i - 15]) + w[i - 16];
        }

        // Initialize working variables with current hash values
        long a = h[0];
        long b = h[1];
        long c = h[2];
        long d = h[3];
        long e = h[4];
        long f = h[5];
        long g = h[6];
        long h0 = h[7];

        // Create a task graph for the SHA-512 kernel
        TaskGraph taskGraph = new TaskGraph("sha512")
                .transferToDevice(DataTransferMode.FIRST_EXECUTION, w)
                .task("sha512", ChecksumGenerator::sha512Kernel, w, a, b, c, d, e, f, g, h0)
                .transferToHost(DataTransferMode.EVERY_EXECUTION, a, b, c, d, e, f, g, h0);

        // Create an immutable snapshot of the task graph
        ImmutableTaskGraph immutableTaskGraph = taskGraph.snapshot();

        // Execute the task graph using TornadoVM with dynamic reconfiguration
        try (TornadoExecutionPlan executionPlan = new TornadoExecutionPlan(immutableTaskGraph)) {
            executionPlan.withDynamicReconfiguration(Policy.PERFORMANCE, DRMode.PARALLEL)
                    .execute();
        } catch (TornadoExecutionPlanException tornadoExc) {
            throw new RuntimeException("TornadoVM execution failed", tornadoExc);
        }

        // Update the hash values with the results from the working variables
        h[0] += a;
        h[1] += b;
        h[2] += c;
        h[3] += d;
        h[4] += e;
        h[5] += f;
        h[6] += g;
        h[7] += h0;
    }

    /**
     * The SHA-512 kernel function to be executed in parallel.
     * @param w  the message schedule array
     * @param a  the first hash value
     * @param b  the second hash value
     * @param c  the third hash value
     * @param d  the fourth hash value
     * @param e  the fifth hash value
     * @param f  the sixth hash value
     * @param g  the seventh hash value
     * @param h0 the eighth hash value
     */
    private static void sha512Kernel(@Parallel long[] w, long a, long b, long c, long d, long e, long f, long g, long h0) {
        for (int i = 0; i < 80; i++) {
            long t1 = h0 + Sigma1(e) + Ch(e, f, g) + K[i] + w[i];
            long t2 = Sigma0(a) + Maj(a, b, c);
            h0 = g;
            g = f;
            f = e;
            e = d + t1;
            d = c;
            c = b;
            b = a;
            a = t1 + t2;
        }
    }

    /**
     * The choice function used in the SHA-512 algorithm.
     * @param x the first input
     * @param y the second input
     * @param z the third input
     * @return the result of the choice function
     */
    private static long Ch(long x, long y, long z) {
        return (x & y) ^ (~x & z);
    }

    /**
     * The majority function used in the SHA-512 algorithm.
     * @param x the first input
     * @param y the second input
     * @param z the third input
     * @return the result of the majority function
     */
    private static long Maj(long x, long y, long z) {
        return (x & y) ^ (x & z) ^ (y & z);
    }

    /**
     * The Sigma0 function used in the SHA-512 algorithm.
     * @param x the input
     * @return the result of the Sigma0 function
     */
    private static long Sigma0(long x) {
        return Long.rotateRight(x, 28) ^ Long.rotateRight(x, 34) ^ Long.rotateRight(x, 39);
    }

    /**
     * The Sigma1 function used in the SHA-512 algorithm.
     * @param x the input
     * @return the result of the Sigma1 function
     */
    private static long Sigma1(long x) {
        return Long.rotateRight(x, 14) ^ Long.rotateRight(x, 18) ^ Long.rotateRight(x, 41);
    }

    /**
     * The sigma0 function used in the SHA-512 algorithm.
     * @param x the input
     * @return the result of the sigma0 function
     */
    private static long sigma0(long x) {
        return Long.rotateRight(x, 1) ^ Long.rotateRight(x, 8) ^ (x >>> 7);
    }

    /**
     * The sigma1 function used in the SHA-512 algorithm.
     * @param x the input
     * @return the result of the sigma1 function
     */
    private static long sigma1(long x) {
        return Long.rotateRight(x, 19) ^ Long.rotateRight(x, 61) ^ (x >>> 6);
    }
}
