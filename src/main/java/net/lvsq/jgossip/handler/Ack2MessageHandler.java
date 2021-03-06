// Copyright (c) 2017 The jgossip Authors. All rights reserved.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package net.lvsq.jgossip.handler;

import io.vertx.core.json.JsonObject;
import net.lvsq.jgossip.core.GossipManager;
import net.lvsq.jgossip.model.Ack2Message;
import net.lvsq.jgossip.model.GossipMember;
import net.lvsq.jgossip.model.HeartbeatState;

import java.util.Map;

/**
 * @author lvsq
 */
public class Ack2MessageHandler implements MessageHandler {
    @Override
    public void handle(String cluster, String data, String from) {
        JsonObject dj = new JsonObject(data);
        Ack2Message ack2Message = dj.mapTo(Ack2Message.class);

        Map<GossipMember, HeartbeatState> deltaEndpoints = ack2Message.getEndpoints();
        GossipManager.getInstance().apply2LocalState(deltaEndpoints);
    }
}
