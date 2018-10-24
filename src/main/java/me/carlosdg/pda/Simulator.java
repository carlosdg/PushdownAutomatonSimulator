package me.carlosdg.pda;

import java.io.IOException;
import java.util.Optional;

import me.carlosdg.pda.config.PdaConfiguration;
import me.carlosdg.pda.config.readers.EmptyStackPdaCustomFileReader;
import me.carlosdg.pda.config.readers.PdaReader;
import me.carlosdg.pda.definition.EmptyStackPdaDefinition;
import me.carlosdg.pda.sets.exceptions.DuplicatedStringInSetException;
import me.carlosdg.pda.sets.exceptions.SymbolNotFoundInSetException;
import me.carlosdg.pda.simulator.EmptyStackPdaSimulator;
import me.carlosdg.pda.simulator.spies.PdaExecutionConsoleLogger;
import me.carlosdg.pda.simulator.spies.PdaExecutionSpy;
import me.carlosdg.pda.word.Word;
import me.carlosdg.pda.word.readers.WordFileReader;
import me.carlosdg.pda.word.readers.WordReader;

public class Simulator {

	public static void main(String[] args) {
		if (args.length != 3) {
			System.err.println("Usage: \n\t java Simulator <pda_configuration_file_path> <input_word_file_path> [0|1]");
			return;
		}
		String configurationFilePath = args[0];
		String inputWordFilePath = args[1];
		String debug = args[2];

		try {
			PdaReader reader = new EmptyStackPdaCustomFileReader(configurationFilePath);
			PdaConfiguration configuration = reader.getConfig();
			EmptyStackPdaDefinition pdaDefinition = new EmptyStackPdaDefinition(configuration);
			EmptyStackPdaSimulator simulator = new EmptyStackPdaSimulator(pdaDefinition);
			WordReader wordReader = new WordFileReader(inputWordFilePath);
			Word inputWord = wordReader.getWord();
			Optional<PdaExecutionSpy> maybeLogger = debug == "0" ? Optional.empty()
					: Optional.of(new PdaExecutionConsoleLogger());
			boolean isAccepted = simulator.accepts(inputWord, maybeLogger);
			System.out.println("Accepted: " + isAccepted);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DuplicatedStringInSetException e) {
			e.printStackTrace();
		} catch (SymbolNotFoundInSetException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
