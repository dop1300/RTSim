package com.rtsim.ui.plaintext.inquirer.prompt;

import com.rtsim.ui.plaintext.inquirer.Inquirer;


import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @author Sarah Pritchard
 * Command for retrieving multiple values then returning the result of a function of these inputs.
 */
public class PrompterPipeCommand extends PrompterCommand  {
    private PrompterCommand[] inputs;
    private Function<Map<String, Object>, Object> output;
    private String valueId;

    public PrompterPipeCommand(String description, String valueId, PrompterCommand[] inputs, Function<Map<String, Object>, Object> output) {
        super(description);
        this.inputs = inputs;
        this.output = output;
        this.valueId = valueId;
    }

    /**
     * Runs inputs and runs a function with every result as a parameter.
     * @return the output of the function
     */
    public PromptResult runPrompt(Inquirer inquirer) {
        Map<String, Object> values = new HashMap<>();
        for(int index = 0; index < inputs.length; index++) {
            inquirer.display("Prompt " + (index + 1) + "/" + inputs.length);
            PromptResult answer = inputs[index].runPrompt(inquirer);
            values.put(answer.valueId(), answer.value());
        }
        return new PromptResult(output.apply(values), valueId, null);
    }
}