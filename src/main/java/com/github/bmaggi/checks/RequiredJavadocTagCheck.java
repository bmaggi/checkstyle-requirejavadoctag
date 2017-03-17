////////////////////////////////////////////////////////////////////////////////
// checkstyle-requirejavadoctag : Checkstyle rule for javadoc.
// Copyright (C) 2001-2015 the original author or authors.
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License, or (at your option) any later version.
//
// This library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
// Lesser General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public
// License along with this library; if not, write to the Free Software
// Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
////////////////////////////////////////////////////////////////////////////////
package com.github.bmaggi.checks;

import com.puppycrawl.tools.checkstyle.api.DetailNode;
import com.puppycrawl.tools.checkstyle.api.JavadocTokenTypes;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;
import com.puppycrawl.tools.checkstyle.checks.javadoc.AbstractJavadocCheck;
import com.puppycrawl.tools.checkstyle.utils.JavadocUtils;

/**
 * @author Benoit MAGGI
 */
public final class RequiredJavadocTagCheck extends AbstractJavadocCheck {

    /* A key is pointing to the warning message text in "messages.properties file.*/
    public static final String MSG_KEY_JAVADOCTAG_MISSING = "javadoctag.missing";

    public String customtag;

    public void setCustomtag(String customtag) {
        this.customtag = customtag;
    }


    @Override
    public int[] getDefaultJavadocTokens() {
        return new int[]{
                JavadocTokenTypes.JAVADOC,
        };
    }

    @Override
    public int[] getAcceptableTokens() {
        return new int[]{TokenTypes.BLOCK_COMMENT_BEGIN};
    }

    @Override
    public int[] getRequiredTokens() {
        return getAcceptableTokens();
    }

    @Override
    public void visitJavadocToken(DetailNode ast) {
        boolean found = false;
        for (DetailNode node : ast.getChildren()) {
            if (node.getType() == JavadocTokenTypes.JAVADOC_TAG) {
                final String tagText = JavadocUtils.getFirstChild(node).getText();
                if (tagText.contains(customtag)) {
                    found = true;
                }

            }
        }

        if (!found) {
            log(ast.getLineNumber(), MSG_KEY_JAVADOCTAG_MISSING);
        }

    }


}
