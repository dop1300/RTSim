package com.rtsim.ui.plaintext.inquirer.prompt;

import com.rtsim.ui.plaintext.inquirer.Inquirer;

/*
 * @author Sarah Pritchard
 * Runs no prompt. Simply returns a value and no further commands.
 */
public class PrompterPromptlessCommand extends PrompterCommand  {
    private Object value;
    private String valueId;

    public PrompterPromptlessCommand(String description, String valueId, Object value) {
        super(description);
        this.valueId = valueId;
        this.value = value;
    }

    /**
     * Returns a value and id without prompting.
     * @return A value and id.
     */
    public PromptResult runPrompt(Inquirer inquirer) {
        return new PromptResult(value, valueId, null);
    }
}