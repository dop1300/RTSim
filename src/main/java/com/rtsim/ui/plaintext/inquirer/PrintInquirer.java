package com.rtsim.ui.plaintext.inquirer;

import java.util.Scanner;

public class PrintInquirer extends Inquirer {
    private Scanner scanner;

    public PrintInquirer() {
        scanner = new Scanner(System.in);
    }
    
    @Override
    public void display(String text) {
        System.out.print(text);
    }

    @Override
    public String readLine(String prompt) {
        return scanner.nextLine();
    }

    
}
