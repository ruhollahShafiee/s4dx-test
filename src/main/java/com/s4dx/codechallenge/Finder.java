package com.s4dx.codechallenge;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Finder {

//    multiplying prime numbers we will always end up with unique products
//    this property is used for mapping characters ASCII values to primes property indexes
    private final int[] primes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199, 211, 223, 227, 229, 233, 239, 241, 251, 257, 263, 269, 271, 277, 281, 283, 293, 307, 311, 313, 317, 331, 337, 347, 349, 353, 359, 367, 373, 379, 383, 389, 397, 401, 409, 419, 421, 431, 433, 439, 443, 449, 457, 461, 463, 467, 479, 487, 491, 499, 503, 509, 521, 523, 541, 547, 557, 563, 569, 571, 577, 587, 593, 599, 601, 607, 613, 617, 619, 631, 641, 643, 647, 653, 659, 661, 673, 677, 683, 691, 701, 709, 719};
    private final Map<Long, List<String>> map;

    public Finder(String[] param) {

//        using a HashMap because we prioritize performance over memory consumption
        this.map = new HashMap<>();
        for (String s : param) {
            long multipliedNumber = convertStringToMultipliedNumber(s);
            List<String> tmpArr = (map.get(multipliedNumber) == null ? new LinkedList<>() : map.get(multipliedNumber));
            tmpArr.add(s);
            this.map.put(multipliedNumber, tmpArr);
        }

    }

    public String[] find(String param) {

        long multipliedNumber = convertStringToMultipliedNumber(param);
        return map.get(multipliedNumber) == null ? new String[0] : map.get(multipliedNumber).toArray(String[]::new);

    }

//    multiplying prime numbers we will always end up with unique products
    private long convertStringToMultipliedNumber(String param) {

        long number = 1L;
        for (Character index : param.toCharArray()) {
            number = number * (this.primes[index]);
        }
        return number;

    }
}
