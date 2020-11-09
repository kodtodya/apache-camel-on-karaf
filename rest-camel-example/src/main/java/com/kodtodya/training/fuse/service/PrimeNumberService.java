package com.kodtodya.training.fuse.service;

public class PrimeNumberService {

    public String isPrime(int number) {

        String response = "Prime";
        int half = number / 2;
        if (number <= 1) {
            response = "Not " + response;
        } else {
            for (int counter = 2; counter <= half; counter++) {
                if (0 == number % counter) {
                    response = "Not " + response;
                    break;
                }
            }

        }
        return number + " is " + response;
    }
}