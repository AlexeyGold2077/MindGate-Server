package com.alexeygold2077;

import com.alexeygold2077.api.proxyapi.Proxyapi;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static Proxyapi proxyapi = new Proxyapi("sk-YLhidrIF1tbb6UKEizF4tIW1nTKY1yGK", "gpt-4o");

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.print("Введите запрос: ");
            System.out.println(proxyapi.sendMessage("user", in.nextLine()));
        }
    }
}