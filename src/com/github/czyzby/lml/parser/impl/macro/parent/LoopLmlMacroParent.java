package com.github.czyzby.lml.parser.impl.macro.parent;

import com.badlogic.gdx.utils.Array;
import com.github.czyzby.lml.parser.LmlParser;
import com.github.czyzby.lml.parser.impl.dto.LmlParent;

public class LoopLmlMacroParent extends AbstractLoopLmlMacroParent {
	public LoopLmlMacroParent(final String tagName, final LmlParent<?> parent, final Array<String> arguments) {
		super(tagName, parent, arguments);
	}

	@Override
	public void closeTag(final LmlParser parser) {
		final String textInTag = getAppenedTextWithStrippedEndTag();
		if (textInTag.length() == 0) {
			throwErrorIfStrict(parser, "Loop has to have a body.");
			return;
		}
		if (arguments.size == 0) {
			throwErrorIfStrict(parser, "Loop has to be limited by an int - iterations amount.");
		}
		final StringBuilder textToAppend = new StringBuilder();
		final String indexArgumentKey = getIndexArgumentKey();
		final int iterationsAmount = Integer.parseInt(arguments.get(0));
		for (int index = 0; index < iterationsAmount; index++) {
			textToAppend.append(textInTag.replaceAll(indexArgumentKey, String.valueOf(index)));
		}
		parser.appendToBuffer(textToAppend);
	}

}