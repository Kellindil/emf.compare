/*
* generated by Xtext
*/
package org.eclipse.emf.compare.epatch.dsl.parser.antlr;

import org.antlr.runtime.ANTLRInputStream;
import org.eclipse.xtext.parser.antlr.ITokenDefProvider;
import org.eclipse.xtext.parser.IParseResult;
import org.eclipse.xtext.parser.ParseException;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;

import com.google.inject.Inject;

import org.eclipse.emf.compare.epatch.dsl.services.EpatchGrammarAccess;

public class EpatchParser extends org.eclipse.xtext.parser.antlr.AbstractAntlrParser {
	
	@Inject 
    protected ITokenDefProvider antlrTokenDefProvider;
	
	@Inject
	private EpatchGrammarAccess grammarAccess;
	
	@Override
	protected IParseResult parse(String ruleName, ANTLRInputStream in) {
		org.eclipse.emf.compare.epatch.dsl.parser.antlr.internal.InternalEpatchLexer lexer = new org.eclipse.emf.compare.epatch.dsl.parser.antlr.internal.InternalEpatchLexer(in);
		XtextTokenStream stream = new XtextTokenStream(lexer, antlrTokenDefProvider);
		stream.setInitialHiddenTokens("RULE_WS", "RULE_ML_COMMENT", "RULE_SL_COMMENT");
		org.eclipse.emf.compare.epatch.dsl.parser.antlr.internal.InternalEpatchParser parser = new org.eclipse.emf.compare.epatch.dsl.parser.antlr.internal.InternalEpatchParser(
				stream, getElementFactory(), grammarAccess);
		parser.setTokenTypeMap(antlrTokenDefProvider.getTokenDefMap());
		try {
			if(ruleName != null)
				return parser.parse(ruleName);
			return parser.parse();
		} catch (Exception re) {
			throw new ParseException(re.getMessage(),re);
		}
	}
	
	@Override 
	protected String getDefaultRuleName() {
		return "Epatch";
	}
	
	public EpatchGrammarAccess getGrammarAccess() {
		return this.grammarAccess;
	}
	
	public void setGrammarAccess(EpatchGrammarAccess grammarAccess) {
		this.grammarAccess = grammarAccess;
	}
}
