/**
 * Copyright (c) 2010-2020 Contributors to the openHAB project
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package org.openhab.binding.heos.internal.handler;

import org.eclipse.smarthome.core.types.RefreshType;
import org.openhab.binding.heos.handler.HeosBridgeHandler;
import org.openhab.binding.heos.internal.api.HeosFacade;

/**
 * The {@link HeosChannelHandlerControl} handles the control commands
 * coming from the implementing thing
 *
 * @author Johannes Einig - Initial contribution
 *
 */
public class HeosChannelHandlerControl extends HeosChannelHandler {

    public HeosChannelHandlerControl(HeosBridgeHandler bridge, HeosFacade api) {
        super(bridge, api);
    }

    @Override
    protected void handleCommandPlayer() {
        handleCommand();
    }

    @Override
    protected void handleCommandGroup() {
        handleCommand();
    }

    @Override
    protected void handleCommandBridge() {
        // No such channel within bridge
    }

    private void handleCommand() {
        if (command instanceof RefreshType) {
            api.getHeosPlayState(id);
            return;
        }
        switch (command.toString()) {
            case "PLAY":
                api.play(id);
                break;
            case "PAUSE":
                api.pause(id);
                break;
            case "NEXT":
                api.next(id);
                break;
            case "PREVIOUS":
                api.previous(id);
                break;
            case "ON":
                api.play(id);
                break;
            case "OFF":
                api.pause(id);
                break;
        }
    }
}
