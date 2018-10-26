package me.carlosdg.pda;

import java.util.Optional;

import me.carlosdg.pda.config.PdaConfiguration;
import me.carlosdg.pda.config.readers.EmptyStackPdaCustomFileReader;
import me.carlosdg.pda.config.readers.PdaReader;
import me.carlosdg.pda.definition.EmptyStackPdaDefinition;
import me.carlosdg.pda.simulator.EmptyStackPdaSimulator;
import me.carlosdg.pda.simulator.spies.PdaExecutionConsoleLogger;
import me.carlosdg.pda.simulator.spies.PdaExecutionSpy;
import me.carlosdg.pda.word.Word;
import me.carlosdg.pda.word.readers.WordFileReader;
import me.carlosdg.pda.word.readers.WordReader;

public class Simulator {

	public static void main(String[] args) {
		try {
			if (args.length != 3) {
				throw new Exception(
						"Usage: \n\t java Simulator <pda_configuration_file_path> <input_word_file_path> [0|1]");
			}

			String configurationFilePath = args[0];
			String inputWordFilePath = args[1];
			boolean debug = args[2].equals("0");

			// Read configuration file
			PdaReader reader = new EmptyStackPdaCustomFileReader(configurationFilePath);
			PdaConfiguration configuration = reader.getConfig();

			// Parse the raw configuration to get the PDA definition (throws if the
			// configuration is not valid)
			EmptyStackPdaDefinition pdaDefinition = new EmptyStackPdaDefinition(configuration);

			// Instantiate the simulator with the PDA definition
			EmptyStackPdaSimulator simulator = new EmptyStackPdaSimulator(pdaDefinition);

			// Read the input word file
			WordReader wordReader = new WordFileReader(inputWordFilePath);
			Word inputWord = wordReader.getWord();

			// Instantiate an optional Spy to log the progress of the simulator when given
			// the input word
			Optional<PdaExecutionSpy> maybeLogger = debug ? Optional.empty()
					: Optional.of(new PdaExecutionConsoleLogger());

			// Run the simulation and print whether the word was accepted or not
			boolean isAccepted = simulator.accepts(inputWord, maybeLogger);
			System.out.println("Accepted: " + isAccepted);
		} catch (Exception genericException) {
			System.err.println(genericException.getMessage());
		}
	}

}
