package com.rtsim.ui.plaintext.inquirer.prompt;

import com.rtsim.ui.plaintext.inquirer.Inquirer;

/**
 * Selects and runs a given prompt.
 */
public class PrompterSelectionCommand extends PrompterMenuCommand  {

    public PrompterSelectionCommand(String description, PrompterCommand[] items) {
        super(description, items);
    }

    @Override
    public PromptResult runPrompt(Inquirer inquirer) {
        return super.runPrompt(inquirer).next().runPrompt(inquirer);
    }
}