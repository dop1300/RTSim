package com.rtsim.ui.plaintext.inquirer.prompt;

import com.rtsim.ui.plaintext.inquirer.Inquirer;

/***
 * @author Sarah Pritchard
 * Get a floating point from a prompt.
 */
public class PrompterNumberCommand extends PrompterCommand  {
    private String prompt;
    private String valueId;

    public PrompterNumberCommand(String description, String valueId, String prompt) {
        super(description);
        this.prompt = prompt;
        this.valueId = valueId;
    }

    /**
     * Prompts the user for a floating point
     */
    public PromptResult runPrompt(Inquirer inquirer) {
        float number = inquirer.inquireFloat(prompt);
        return new PromptResult(number, valueId, null);
    }
}