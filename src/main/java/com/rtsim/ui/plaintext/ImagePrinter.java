package com.rtsim.ui.plaintext;

import com.rtsim.ui.plaintext.inquirer.Inquirer;

public class ImagePrinter {
    private boolean colors;
    private Inquirer inquirer;

    public ImagePrinter(boolean colors, Inquirer inquirer) {
        this.colors = colors;
        this.inquirer = inquirer;
    }

    
}
