/*
*  Copyright (c) 2017, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
*
*  WSO2 Inc. licenses this file to you under the Apache License,
*  Version 2.0 (the "License"); you may not use this file except
*  in compliance with the License.
*  You may obtain a copy of the License at
*
*    http://www.apache.org/licenses/LICENSE-2.0
*
*  Unless required by applicable law or agreed to in writing,
*  software distributed under the License is distributed on an
*  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
*  KIND, either express or implied.  See the License for the
*  specific language governing permissions and limitations
*  under the License.
*/

package org.ballerinalang.composer.service.workspace.langserver.util.completion.resolvers;

import org.antlr.v4.runtime.TokenStream;
import org.ballerinalang.composer.service.workspace.langserver.SymbolInfo;
import org.ballerinalang.composer.service.workspace.langserver.dto.CompletionItem;
import org.ballerinalang.composer.service.workspace.suggetions.SuggestionsFilterDataModel;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Completion Item Resolver for the Package name context.
 */
class PackageNameContextResolver extends AbstractItemResolver {
    @Override
    public ArrayList<CompletionItem> resolveItems(SuggestionsFilterDataModel dataModel, ArrayList<SymbolInfo> symbols,
                                                  HashMap<Class, AbstractItemResolver> resolvers) {
        ArrayList<CompletionItem> completionItems = new ArrayList<>();

        TokenStream tokenStream = dataModel.getTokenStream();
        int currentTokenIndex = dataModel.getTokenIndex();
        int tokenIterator = currentTokenIndex - 1;
        boolean proceed = true;

        while (proceed) {
            String tokenStr = tokenStream.get(tokenIterator).getText();
            if (tokenStr.equals(ItemResolverConstants.IMPORT) || tokenStr.equals(ItemResolverConstants.PACKAGE)) {
                proceed = false;
            } else {
                tokenIterator--;
            }
        }

        return completionItems;
    }
}
