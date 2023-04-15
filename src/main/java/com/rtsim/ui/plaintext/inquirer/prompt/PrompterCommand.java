package com.rtsim.ui.plaintext.inquirer.prompt;

import com.rtsim.ui.plaintext.inquirer.Inquirer;

public abstract class PrompterCommand {
    private String description;

    protected PrompterCommand(String description) {
        this.description = description;
    }

    public abstract PromptResult runPrompt(Inquirer inquirer);

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return description;
   }
}