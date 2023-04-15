package com.rtsim.ui.plaintext.inquirer.prompt;

import com.rtsim.ui.plaintext.inquirer.Inquirer;

/**
 * Prompts the user to select a command to run next.
 */
public class PrompterMenuCommand extends PrompterCommand {
    private PrompterCommand[] items;

    public PrompterMenuCommand(String description, PrompterCommand[] items) {
        super(description);
        this.items = items;
    }

    public PromptResult runPrompt(Inquirer inquirer) {
        StringBuilder builder = new StringBuilder();
        builder.append("Choose an item:\n");
        for (int index = 0; index < items.length; index++) {
            builder.append((index  + 1) + ")\t" + items[index].getDescription() + "\n");
        }
        inquirer.display(builder.toString());
        int chosen = inquirer.inquireInt("> ", i -> i > 0 && i < 1 + items.length) - 1;
        return new PromptResult(null, null, items[chosen]);
    }
}