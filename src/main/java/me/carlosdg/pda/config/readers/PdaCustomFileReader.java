package me.carlosdg.pda.config.readers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;
import java.util.Scanner;
import me.carlosdg.pda.config.PdaConfiguration;
import me.carlosdg.pda.config.PdaConfigurationBuilder;

/**
 * Custom PDA file reader for Pushdown Automata with no accepting states. The
 * files read with this reader have the following format
 *
 * <pre>
 * # Comments
 * p0 p1 q0 q2		# State names
 * a b c d e f		# Input alphabet symbol names
 * Q W E R T Y		# Stack alphabet symbol names
 * p0				# Initial state
 * Q				# Initial stack top
 * p0 a Q p1 W T	# Transition
 * ...				# More transitions
 * </pre>
 *
 * @author Carlos Domínguez García
 */
public class PdaCustomFileReader implements PdaReader {
	/** Regular expression for single line comment delimiter */
	static private String SINGLE_LINE_COMMENT_DELIMITER_REGEX = "#";
	/** Regular expression for token delimiter */
	static private String TOKEN_DELIMITER_REGEX = "\\s+";

	/** File reader */
	private Scanner fileReader;
	/** PDA configuration builder */
	private PdaConfigurationBuilder configBuilder;
	/** PDA configuration object obtained from the file read */
	private PdaConfiguration pdaConfiguration;

	/**
	 * Reads the given file initializing the PDA configuration object
	 *
	 * @param filePath The path of the file containing the PDA configuration
	 * @throws IOException If the given file path is not found or there is an error
	 *                     reading the file
	 */
	public PdaCustomFileReader(String filePath) throws IOException {
		fileReader = new Scanner(new BufferedReader(new FileReader(filePath)));
		configBuilder = new PdaConfigurationBuilder();

		// Parse the file
		parseStates();
		parseInputAlphabet();
		parseStackAlphabet();
		parseStartingState();
		parseInitialStackTop();
		parseTransitions();

		// Build the configuration object
		pdaConfiguration = configBuilder.build();
	}

	/**
	 * @see me.carlosdg.pda.config.readers.PdaReader#getConfig()
	 */
	@Override
	public PdaConfiguration getConfig() {
		return pdaConfiguration;
	}

	/**
	 * Strips comments from the line and returns the array of tokens (understanding
	 * as token words separated by TOKEN_DELIMITER_REGEX)
	 *
	 * @param line Line to parse
	 * @return Array of tokens gotten from parsing the given line
	 */
	private String[] parseLine(String line) {
		String lineWithoutComments = line.split(SINGLE_LINE_COMMENT_DELIMITER_REGEX)[0].trim();

		// If there are no tokens -> return an empty array
		if (lineWithoutComments.length() > 0) {
			return lineWithoutComments.split(TOKEN_DELIMITER_REGEX);
		} else {
			return new String[] {};
		}

	}

	/**
	 * Parses the next lines gotten from the file reader and returns the first one
	 * that has at least one token ignoring white spaces and comments
	 *
	 * @return An Optional array of tokens from the first non empty line, empty if
	 *         the end of the file is reached
	 */
	private Optional<String[]> getNextNonEmptyParsedLine() {
		String[] currentLineTokens;

		while (fileReader.hasNextLine()) {
			currentLineTokens = parseLine(fileReader.nextLine());
			if (currentLineTokens.length > 0) {
				return Optional.of(currentLineTokens);
			}
		}

		return Optional.empty();
	}

	/**
	 * Returns the tokens from the next non empty line or throws an IOException with
	 * the given message
	 *
	 * @param exceptionMessage Message of the IOException to throw if there are no
	 *                         more lines with tokens
	 * @return An array of tokens of the next non empty line
	 * @throws IOException If there are no more lines with tokens
	 */
	private String[] getNextNonEmptyParsedLineOrThrow(String exceptionMessage) throws IOException {
		return getNextNonEmptyParsedLine().orElseThrow(() -> new IOException(exceptionMessage));
	}

	/**
	 * Treats the next non empty line as the list of states and adds them to the
	 * configuration builder
	 *
	 * @throws IOException If there are no more non empty lines
	 */
	private void parseStates() throws IOException {
		String[] tokens;
		// Get set of states
		tokens = getNextNonEmptyParsedLineOrThrow("Found end of file before finding the set of states");
		configBuilder.addStateNameList(Arrays.asList(tokens));
	}

	/**
	 * Treats the next non empty line as the input alphabet and adds it to the
	 * configuration builder
	 *
	 * @throws IOException If there are no more non empty lines
	 */
	private void parseInputAlphabet() throws IOException {
		String[] tokens;
		tokens = getNextNonEmptyParsedLineOrThrow("Found end of file before finding the input alphabet");
		configBuilder.addInputAlphabetSymbolNameList(Arrays.asList(tokens));
	}

	/**
	 * Treats the next non empty line as the stack alphabet and adds it to the
	 * configuration builder
	 *
	 * @throws IOException If there are no more non empty lines
	 */
	private void parseStackAlphabet() throws IOException {
		String[] tokens;
		tokens = getNextNonEmptyParsedLineOrThrow("Found end of file before finding the stack alphabet");
		configBuilder.addStackAlphabetSymbolNameList(Arrays.asList(tokens));
	}

	/**
	 * Treats the next non empty line as the starting state and adds it to the
	 * configuration builder
	 *
	 * @throws IOException If there are no more non empty lines or if the parsed
	 *                     line has more than one token
	 */
	private void parseStartingState() throws IOException {
		String[] tokens;
		tokens = getNextNonEmptyParsedLineOrThrow("Found end of file before finding the starting state");
		if (tokens.length != 1) {
			throw new IOException("Invalid number of starting states, expected 1 but found " + tokens.length);
		}
		configBuilder.setStartingStateName(tokens[0]);
	}

	/**
	 * Treats the next non empty line as the initial top of stack and adds it to the
	 * configuration builder
	 *
	 * @throws IOException If there are no more non empty lines or if the parsed
	 *                     line has more than one token
	 */
	private void parseInitialStackTop() throws IOException {
		String[] tokens;
		tokens = getNextNonEmptyParsedLineOrThrow("Found end of file before finding the initial stack top");
		if (tokens.length != 1) {
			throw new IOException("Invalid number of initial stack top, expected 1 but found " + tokens.length);
		}
		configBuilder.setStartingStackTopSymbolName(tokens[0]);
	}

	/**
	 * Treats the rest of the non empty lines as the transition definitions and adds
	 * them to the configuration builder
	 */
	private void parseTransitions() {
		Optional<String[]> maybeTransitions = getNextNonEmptyParsedLine();
		while (maybeTransitions.isPresent()) {
			maybeTransitions.ifPresent(transition -> configBuilder.addTransition(Arrays.asList(transition)));
			maybeTransitions = getNextNonEmptyParsedLine();
		}
	}

}
