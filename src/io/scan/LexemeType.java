package io.scan;

/**
 * Regular Expressions for simple tokens
 *
 * @author Anton Chepurov
 */
@SuppressWarnings({"EnumeratedConstantNamingConvention"})
public enum LexemeType {
	IDENTIFIER("[a-zA-Z_][\\w\\.]*"),
	OP_EQ("="), OP_ADD("\\+"), OP_SUBTR("\\-"), OP_DIV("/"), OP_AMP("&"), OP_MULT("\\*"),
	NUMBER("\\d+"),
	SEMICOLON(";"),
	COLON(":"),
	COMMA(","),
	SINGLE_QUOTE("\'"),
	DOUBLE_QUOTE("\""),
	OPEN_BRACKET("\\("),
	CLOSE_BRACKET("\\)"),
	LT("<"),
	GT(">"),
	OPEN_SQUARE_BRACKET("\\["),
	CLOSE_SQUARE_BRACKET("\\]"),
	VERTICAL_LINE("\\|"),
	SHARP("#");

	private final String regexp;

	LexemeType(String regexp) {
		this.regexp = regexp;
	}


	@SuppressWarnings({"BooleanMethodNameMustStartWithQuestion"})
	public boolean accepts(String lexemeValue) {
		return lexemeValue.matches(regexp);
	}

	/**
	 * @param startingChar first char of lexeme
	 * @return diagnosed {@link LexemeType}
	 * @throws Exception if the LexemeType could not be determined for some
	 *                   starting character
	 */
	public static LexemeType diagnoseType(char startingChar) throws Exception {

		if (Character.isLetter(startingChar) || startingChar == '_') {
			return IDENTIFIER;
		} else if (Character.isDigit(startingChar)) {
			return NUMBER;
		} else if (startingChar == '=') {
			return OP_EQ;
		} else if (startingChar == '+') {
			return OP_ADD;
		} else if (startingChar == '-') {
			return OP_SUBTR;
		} else if (startingChar == '/') {
			return OP_DIV;
		} else if (startingChar == '&') {
			return OP_AMP;
		} else if (startingChar == '*') {
			return OP_MULT;
		} else if (startingChar == ';') {
			return SEMICOLON;
		} else if (startingChar == ':') {
			return COLON;
		} else if (startingChar == ',') {
			return COMMA;
		} else if (startingChar == '\'') {
			return SINGLE_QUOTE;
		} else if (startingChar == '\"') {
			return DOUBLE_QUOTE;
		} else if (startingChar == '(') {
			return OPEN_BRACKET;
		} else if (startingChar == ')') {
			return CLOSE_BRACKET;
		} else if (startingChar == '<') {
			return LT;
		} else if (startingChar == '>') {
			return GT;
		} else if (startingChar == '[') {
			return OPEN_SQUARE_BRACKET;
		} else if (startingChar == ']') {
			return CLOSE_SQUARE_BRACKET;
		} else if (startingChar == '|') {
			return VERTICAL_LINE;
		} else if (startingChar == '#') {
			return SHARP;
		} else {
			System.out.println("incorrect char: " + startingChar + ".");
			throw new Exception("Cannot diagnose LexemeType for the following first character: " + startingChar);
		}

	}

	public String getRegexp() {
		return regexp;
	}
}
