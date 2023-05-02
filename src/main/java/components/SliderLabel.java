package components;

import javafx.scene.control.Label;
import javafx.scene.Node;

public class SliderLabel extends Label {

    private static final String caption = "Slider value is ";

    /* *************************************************************************
     *                                                                         *
     * Constructors                                                            *
     *                                                                         *
     **************************************************************************/

    /**
     * Creates a captioned slider label with empty slider value
     */
    public SliderLabel() {
        super(caption);
    }

    /**
     * Creates captioned slider label with supplied text.
     * @param text null text is treated as the empty string
     */
    public SliderLabel(String text) {
        super(caption + " " + text);
    }

    /**
     * Creates a captioned slider label with the supplied text and graphic.
     * @param text null text is treated as the empty string
     * @param graphic a null graphic is acceptable
     */
    public SliderLabel(String text, Node graphic) {
        super(caption + " " + text, graphic);
    }

    /* *************************************************************************
     *                                                                         *
     * Methods                                                                 *
     *                                                                         *
     **************************************************************************/

    public void updateText(String value) {
        setText(caption + value);
    }
}
