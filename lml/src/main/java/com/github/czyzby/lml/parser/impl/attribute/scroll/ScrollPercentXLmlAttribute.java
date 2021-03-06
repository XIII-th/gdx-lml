package com.github.czyzby.lml.parser.impl.attribute.scroll;

import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.github.czyzby.lml.parser.LmlParser;
import com.github.czyzby.lml.parser.action.ActorConsumer;
import com.github.czyzby.lml.parser.tag.LmlAttribute;
import com.github.czyzby.lml.parser.tag.LmlTag;
import com.github.czyzby.lml.util.LmlUtilities;

/** See {@link ScrollPane#setScrollPercentX(float)}. Mapped to "scrollPercentX", "percentX".
 *
 * @author MJ */
public class ScrollPercentXLmlAttribute implements LmlAttribute<ScrollPane> {
    @Override
    public Class<ScrollPane> getHandledType() {
        return ScrollPane.class;
    }

    @Override
    public void process(final LmlParser parser, final LmlTag tag, final ScrollPane actor,
            final String rawAttributeData) {
        LmlUtilities.getLmlUserObject(actor).addOnCloseAction(new ActorConsumer<Object, Object>() {
            @Override
            public Object consume(final Object widget) {
                actor.layout(); // Needed to calculate scroll pane size.
                actor.setScrollPercentX(parser.parseFloat(rawAttributeData, actor));
                return null;
            }
        });
    }
}
