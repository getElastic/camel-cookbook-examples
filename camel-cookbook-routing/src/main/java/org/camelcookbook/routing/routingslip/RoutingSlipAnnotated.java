/*
 * Copyright (C) Scott Cranton and Jakub Korab
 * https://github.com/CamelCookbook
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.camelcookbook.routing.routingslip;

import org.apache.camel.Consume;
import org.apache.camel.Headers;
import org.apache.camel.RoutingSlip;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RoutingSlipAnnotated {

    @Consume(uri = "direct:routingSlipAnnotated")
    @RoutingSlip(delimiter = ",")
    public List<String> routeMe(String body, @Headers Map<String, Object> headers) {
        ArrayList<String> results = new ArrayList<String>();

        Object slip = headers.get("myRoutingSlipHeader");
        if (slip != null) {
            String[] uris = slip.toString().split(",");
            for (String uri : uris) {
                results.add(uri);
            }
        }

        results.add("mock:oneMore");

        return results;
    }
}
