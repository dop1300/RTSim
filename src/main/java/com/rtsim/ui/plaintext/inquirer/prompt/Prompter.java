package com.rtsim.ui.plaintext.inquirer.prompt;

import com.rtsim.ui.plaintext.inquirer.Inquirer;

public class Prompter {
    private PrompterCommand base;
    private Inquirer inquirer;

    public Prompter(PrompterCommand base, Inquirer inquirer) {
        this.base = base;
        this.inquirer = inquirer;
    }

    public PromptResult prompt() {
        PrompterCommand current = base;
        while (current != null) {
            PromptResult result = current.runPrompt(inquirer);
            if (result.next() == null)
                return result;
            else
                current = result.next();
        }
        return null;
    }
}