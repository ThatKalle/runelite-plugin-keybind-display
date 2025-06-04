package com.keybinddisplay;

import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.widgets.Widget;
import net.runelite.api.widgets.WidgetInfo;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;

import java.awt.*;
import java.util.Map;
import javax.inject.Singleton;

@Slf4j
@Singleton
public class KeybindDisplayOverlay extends Overlay
{
    private final Client client;
    private final KeybindDisplayConfig config;

    private static final Map<WidgetInfo, String> TAB_HOTKEYS = Map.of(
            WidgetInfo.FIXED_VIEWPORT_INVENTORY_TAB, "F1",
            WidgetInfo.FIXED_VIEWPORT_MAGIC_TAB, "F2",
            WidgetInfo.FIXED_VIEWPORT_PRAYER_TAB, "F3"
    );

    @Inject
    public KeybindDisplayOverlay(Client client, KeybindDisplayConfig config)
    {
        this.client = client;
        this.config = config;
        setPosition(OverlayPosition.DYNAMIC);
        setLayer(OverlayLayer.ABOVE_WIDGETS);
    }

    @Override
    public Dimension render(Graphics2D graphics)
    {
        graphics.setFont(new Font("Arial", Font.BOLD, 16));
        graphics.setColor(Color.YELLOW); // You can make this configurable later

        for (var entry : TAB_HOTKEYS.entrySet())
        {
            Widget widget = client.getWidget(entry.getKey());
            if (widget != null && !widget.isHidden())
            {
                Rectangle bounds = widget.getBounds();
                int x = bounds.x + 5; // Offset can be made configurable
                int y = bounds.y + 15;

                graphics.drawString(entry.getValue(), x, y);
            }
        }

        return null;
    }
}
