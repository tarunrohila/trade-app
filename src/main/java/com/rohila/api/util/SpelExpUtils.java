package com.rohila.api.util;

import org.springframework.expression.EvaluationException;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.ParserContext;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.ArrayList;
import java.util.List;

/**
 * Class file to utility method.
 *
 * @author Tarun Rohila
 */
public class SpelExpUtils {

    /**
     * Variable declaration for parser
     */
    private static final ExpressionParser parser = new SpelExpressionParser();
    /**
     * Variable declaration for parserContext
     */
    private static final ParserContext parserContext = new TemplateParserContext("${", "}");

    /**
     * Private constructor declaration
     */
    private SpelExpUtils() {
    }

    /**
     * Method to evaluate expression
     *
     * @param expression  - expression
     * @param contextArgs - contextArgs
     * @return expression
     */
    public static String evalExp(String expression, Object... contextArgs) {
        if (expression == null) {
            return expression;
        }
        try {
            contextArgs = (contextArgs == null || contextArgs.length <= 0 ? new Object[10] : contextArgs);
            Expression exp = parser.parseExpression(expression, parserContext);
            StandardEvaluationContext context = new StandardEvaluationContext(new Args(contextArgs));
            return exp.getValue(context, String.class);
        } catch (EvaluationException e) {
            return expression;
        }
    }

    /**
     * Static args class
     */
    private static class Args {

        /**
         * Variable declaration
         */
        public List<Object> arg = new ArrayList<>();

        /**
         * Constructor declaration
         *
         * @param contextArgs - contextArgs
         */
        private Args(Object[] contextArgs) {
            if (contextArgs != null && contextArgs.length > 0) {
                for (int i = 0; i < contextArgs.length; i++) {
                    arg.add(contextArgs[i]);
                }
            }
        }
    }
}
