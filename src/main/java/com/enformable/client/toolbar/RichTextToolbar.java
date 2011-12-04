/*
 * This software is published under the Apchae 2.0 licenses.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * Author: Erik Scholtz 
 * Web: http://blog.elitecoderz.net
 */

package com.enformable.client.toolbar;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsArrayString;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.client.ui.RichTextArea.Formatter;

import java.util.LinkedHashMap;
import java.util.Map;

public class RichTextToolbar extends Composite {
// ------------------------------ FIELDS ------------------------------

    //Color and Fontlists - First Value (key) is the Name to display, Second Value (value) is the HTML-Definition
    final static Map<String, String> GUI_COLORLIST = new LinkedHashMap<String, String>() {{
        put("White", "#FFFFFF");
        put("Black", "#000000");
        put("Red", "red");
        put("Green", "green");
        put("Yellow", "yellow");
        put("Blue", "blue");
    }};
    final static Map<String, String> GUI_FONTLIST = new LinkedHashMap<String, String>() {{
        put("Times New Roman", "Times New Roman");
        put("Arial", "Arial");
        put("Courier New", "Courier New");
        put("Georgia", "Georgia");
        put("Trebuchet", "Trebuchet");
        put("Verdana", "Verdana");
    }};

    static final ToolbarBundle TOOLBAR_BUNDLE = GWT.create(ToolbarBundle.class);
    static final ImageResource ICONS = TOOLBAR_BUNDLE.icons();
    static ToolbarConstants constants = GWT.create(ToolbarConstants.class);
    /**
     * Local CONSTANTS *
     */
    //ImageMap and CSS related
//	 static final String HTTP_STATIC_ICONS_GIF = "http://blog.elitecoderz.net/wp-includes/js/tinymce/themes/advanced/img/icons.gif";
    static final String CSS_ROOT_NAME = "RichTextToolbar";

    //HTML Related (styles without closing Tag)
    static final String HTML_STYLE_HLINE = constants.hrStyleWidth100Height2px();

    HorizontalPanel topPanel;
    HorizontalPanel bottomPanel;

    //The RichTextArea this Toolbar referes to and the Interfaces to access the RichTextArea
    RichTextArea styleText;
    Formatter styleTextFormatter;

    //We use an internal class of the ClickHandler and the KeyUpHandler to be  to others with these events
    EventHandler evHandler;

    //The Buttons of the Menubar
    ToggleButton bold,
            italic,
            underline,
            stroke,
            subscript,
            superscript,
            texthtml;
    PushButton alignleft,
            alignmiddle,
            alignright,
            orderlist,
            unorderlist,
            indentleft,
            indentright,
            generatelink,
            breaklink,
            insertline,
            insertimage,
            removeformatting;

    ListBox fontlist;
    ListBox colorlist;

// --------------------------- CONSTRUCTORS ---------------------------

    /**
     * Constructor of the Toolbar
     *
     * @param richtext
     */
    public RichTextToolbar(RichTextArea richtext) {
        //Initialize the main-panel
        VerticalPanel outer = new VerticalPanel();

        //Initialize the two inner panels
        topPanel = new HorizontalPanel();
        bottomPanel = new HorizontalPanel();
        topPanel.setStyleName(CSS_ROOT_NAME);
        bottomPanel.setStyleName(CSS_ROOT_NAME);

        //Save the reference to the RichText area we refer to and get the interfaces to the stylings

        styleText = richtext;
        styleTextFormatter = styleText.getFormatter();

        //Set some graphical options, so this toolbar looks how we like it.
        topPanel.setHorizontalAlignment(HorizontalPanel.ALIGN_LEFT);
        bottomPanel.setHorizontalAlignment(HorizontalPanel.ALIGN_LEFT);

        //Add the two inner panels to the main panel
        outer.add(topPanel);
        outer.add(bottomPanel);

        //Some graphical stuff to the main panel and the initialisation of the new widget
        outer.setWidth("100%");
        outer.setStyleName(CSS_ROOT_NAME);
        initWidget(outer);

        //
        evHandler = new EventHandler();

        //Add KeyUp and Click-Handler to the RichText, so that we can actualize the toolbar if neccessary
        styleText.addKeyUpHandler(evHandler);
        styleText.addClickHandler(evHandler);

        //Now lets fill the new toolbar with life
        buildTools();
    }

    /**
     * Initialize the options on the toolbar *
     */
    void buildTools() {
        ToolbarBundle tb = TOOLBAR_BUNDLE;
        //Init the TOP Panel forst
        topPanel.add(bold = createToggleButton(ICONS, 0, 0, 20, 20, constants.bold()));
        topPanel.add(italic = createToggleButton(ICONS, 0, 60, 20, 20, constants.italic()));
        topPanel.add(underline = createToggleButton(ICONS, 0, 140, 20, 20, constants.underline()));
        topPanel.add(stroke = createToggleButton(ICONS, 0, 120, 20, 20, constants.stroke()));
        topPanel.add(new HTML(constants.nbsp()));
        topPanel.add(subscript = createToggleButton(ICONS, 0, 600, 20, 20, constants.subscript()));
        topPanel.add(superscript = createToggleButton(ICONS, 0, 620, 20, 20, constants.superscript()));
        topPanel.add(new HTML(constants.nbsp()));
        topPanel.add(alignleft = createPushButton(ICONS, 0, 460, 20, 20, constants.alignLeft()));
        topPanel.add(alignmiddle = createPushButton(ICONS, 0, 420, 20, 20, constants.alignCenter()));
        topPanel.add(alignright = createPushButton(ICONS, 0, 480, 20, 20, constants.alignRight()));
        topPanel.add(new HTML(constants.nbsp()));
        topPanel.add(orderlist = createPushButton(ICONS, 0, 80, 20, 20, constants.orderedList()));
        topPanel.add(unorderlist = createPushButton(ICONS, 0, 20, 20, 20, constants.unorderedList()));
        topPanel.add(indentright = createPushButton(ICONS, 0, 400, 20, 20, constants.identRight()));
        topPanel.add(indentleft = createPushButton(ICONS, 0, 540, 20, 20, constants.identLeft()));
        topPanel.add(new HTML(constants.nbsp()));
        topPanel.add(generatelink = createPushButton(ICONS, 0, 500, 20, 20, constants.generateLink()));
        topPanel.add(breaklink = createPushButton(ICONS, 0, 640, 20, 20, constants.breakLink()));
        topPanel.add(new HTML(constants.nbsp()));
        topPanel.add(insertline = createPushButton(ICONS, 0, 360, 20, 20, constants.insertHorizontalLine()));
        topPanel.add(insertimage = createPushButton(ICONS, 0, 380, 20, 20, constants.insertImage()));
        topPanel.add(new HTML(constants.nbsp()));
        topPanel.add(removeformatting = createPushButton(ICONS, 20, 460, 20, 20, constants.removeFormatting()));
        topPanel.add(new HTML(constants.nbsp()));
        topPanel.add(texthtml = createToggleButton(ICONS, 0, 260, 20, 20, constants.switchViewHTMLSource()));

        //Init the BOTTOM Panel
        bottomPanel.add(fontlist = createFontList());
        bottomPanel.add(new HTML(constants.nbsp()));
        bottomPanel.add(colorlist = createColorList());
    }

    /**
     * Method to create a Push button for the toolbar
     *
     * @param icons
     * @param top
     * @param left
     * @param width
     * @param height
     * @param tip
     * @return
     */
    PushButton createPushButton(ImageResource icons, Integer top, Integer left, Integer width, Integer height, String tip) {
        Image extract = new Image(icons.getURL(), left, top, width, height);
        PushButton tb = new PushButton(extract);
        tb.setHeight(height + "px");
        tb.setWidth(width + "px");
        tb.addClickHandler(evHandler);
        if (tip != null) {
            tb.setTitle(tip);
        }
        return tb;
    }

    /**
     * Method to create a Toggle button for the toolbar
     *
     * @param icons
     * @param top
     * @param left
     * @param width
     * @param height
     * @param tip
     * @return
     */
    ToggleButton createToggleButton(ImageResource icons, Integer top, Integer left, Integer width, Integer height, String tip) {
        Image extract = new Image(icons.getURL(), left, top, width, height);
        ToggleButton tb = new ToggleButton(extract);
        tb.setHeight(height + "px");
        tb.setWidth(width + "px");
        tb.addClickHandler(evHandler);
        if (tip != null) {
            tb.setTitle(tip);
        }
        return tb;
    }

    /**
     * Method to create the fontlist for the toolbar
     *
     * @return
     */
    ListBox createFontList() {
        ListBox mylistBox = new ListBox();
        mylistBox.addChangeHandler(evHandler);
        mylistBox.setVisibleItemCount(1);

        mylistBox.addItem(constants.fonts());
        for (String name : GUI_FONTLIST.keySet()) {
            mylistBox.addItem(name, GUI_FONTLIST.get(name));
        }

        return mylistBox;
    }

    /**
     * Method to create the colorlist for the toolbar
     *
     * @return*
     */
    ListBox createColorList() {
        ListBox mylistBox = new ListBox();
        mylistBox.addChangeHandler(evHandler);
        mylistBox.setVisibleItemCount(1);

        mylistBox.addItem(constants.colors());
        for (String name : GUI_COLORLIST.keySet()) {
            mylistBox.addItem(name, GUI_COLORLIST.get(name));
        }

        return mylistBox;
    }

// -------------------------- OTHER METHODS --------------------------

    /**
     * Method called to toggle the style in HTML-Mode
     *
     * @param startTag
     * @param stopTag
     */
    void changeHtmlStyle(String startTag, String stopTag) {
        JsArrayString tx = getSelection(styleText.getElement());
        String txbuffer = styleText.getText();
        Integer startpos = Integer.parseInt(tx.get(1));
        String selectedText = tx.get(0);
        styleText.setText(txbuffer.substring(0, startpos) + startTag + selectedText + stopTag + txbuffer.substring(startpos + selectedText.length()));
    }

    /**
     * Native JavaScript that returns the selected text and position of the start
     *
     * @param elem
     * @return
     */
    static native JsArrayString getSelection(Element elem) /*-{
        var txt = "";
        var pos = 0;
        var range;
        var parentElement;
        var container;

        if (elem.contentWindow.getSelection) {
            txt = elem.contentWindow.getSelection();
            pos = elem.contentWindow.getSelection().getRangeAt(0).startOffset;
        } else if (elem.contentWindow.document.getSelection) {
            txt = elem.contentWindow.document.getSelection();
            pos = elem.contentWindow.document.getSelection().getRangeAt(0).startOffset;
        } else if (elem.contentWindow.document.selection) {
            range = elem.contentWindow.document.selection.createRange();
            txt = range.text;
            parentElement = range.parentElement();
            container = range.duplicate();
            container.moveToElementText(parentElement);
            container.setEndPoint('EndToEnd', range);
            pos = container.text.length - range.text.length;
        }
        return ["" + txt,"" + pos];
    }-*/;

    /**
     * method to set the toggle buttons and disable/enable buttons which do not work in html-mode *
     */
    void updateStatus() {
        if (styleTextFormatter != null) {
            bold.setDown(styleTextFormatter.isBold());
            italic.setDown(styleTextFormatter.isItalic());
            underline.setDown(styleTextFormatter.isUnderlined());
            subscript.setDown(styleTextFormatter.isSubscript());
            superscript.setDown(styleTextFormatter.isSuperscript());
            stroke.setDown(styleTextFormatter.isStrikethrough());
        }

        if (isHTMLMode()) {
            removeformatting.setEnabled(false);
            indentleft.setEnabled(false);
            breaklink.setEnabled(false);
        } else {
            removeformatting.setEnabled(true);
            indentleft.setEnabled(true);
            breaklink.setEnabled(true);
        }
    }

    /**
     * method with a more understandable name to get if HTML mode is on or not
     *
     * @return
     */
    Boolean isHTMLMode() {
        return texthtml.isDown();
    }

// -------------------------- INNER CLASSES --------------------------

    /**
     * Click Handler of the Toolbar *
     */
    class EventHandler implements ClickHandler, KeyUpHandler, ChangeHandler {
        public void onClick(ClickEvent event) {
            if (event.getSource().equals(bold)) {
                if (isHTMLMode()) {
                    changeHtmlStyle(constants.spanStyleFontWeightBold(), constants.closeSpan());
                } else {
                    styleTextFormatter.toggleBold();
                }
            } else if (event.getSource().equals(italic)) {
                if (isHTMLMode()) {
                    changeHtmlStyle(constants.spanStyleFontWeightItalic(), constants.closeSpan());
                } else {
                    styleTextFormatter.toggleItalic();
                }
            } else if (event.getSource().equals(underline)) {
                if (isHTMLMode()) {
                    changeHtmlStyle(constants.spanStyleFontWeightUnderline(), constants.closeSpan());
                } else {
                    styleTextFormatter.toggleUnderline();
                }
            } else if (event.getSource().equals(stroke)) {
                if (isHTMLMode()) {
                    changeHtmlStyle(constants.spanStyleFontWeightLineThrough(), constants.closeSpan());
                } else {
                    styleTextFormatter.toggleStrikethrough();
                }
            } else if (event.getSource().equals(subscript)) {
                if (isHTMLMode()) {
                    changeHtmlStyle(constants.sub(), constants.sub1());
                } else {
                    styleTextFormatter.toggleSubscript();
                }
            } else if (event.getSource().equals(superscript)) {
                if (isHTMLMode()) {
                    changeHtmlStyle(constants.sup(), constants.sup1());
                } else {
                    styleTextFormatter.toggleSuperscript();
                }
            } else if (event.getSource().equals(alignleft)) {
                if (isHTMLMode()) {
                    changeHtmlStyle(constants.divStyleTextAlignLeft(), constants.div());
                } else {
                    styleTextFormatter.setJustification(RichTextArea.Justification.LEFT);
                }
            } else if (event.getSource().equals(alignmiddle)) {
                if (isHTMLMode()) {
                    changeHtmlStyle(constants.divStyleTextAlignCenter(), constants.div());
                } else {
                    styleTextFormatter.setJustification(RichTextArea.Justification.CENTER);
                }
            } else if (event.getSource().equals(alignright)) {
                if (isHTMLMode()) {
                    changeHtmlStyle(constants.divStyleTextAlignRight(), constants.div());
                } else {
                    styleTextFormatter.setJustification(RichTextArea.Justification.RIGHT);
                }
            } else if (event.getSource().equals(orderlist)) {
                if (isHTMLMode()) {
                    changeHtmlStyle(constants.olLi(), constants.olLi1());
                } else {
                    styleTextFormatter.insertOrderedList();
                }
            } else if (event.getSource().equals(unorderlist)) {
                if (isHTMLMode()) {
                    changeHtmlStyle(constants.ulLi(), constants.ulLi1());
                } else {
                    styleTextFormatter.insertUnorderedList();
                }
            } else if (event.getSource().equals(indentright)) {
                if (isHTMLMode()) {
                    changeHtmlStyle(constants.divStyleMarginLeft40px(), constants.div());
                } else {
                    styleTextFormatter.rightIndent();
                }
            } else if (event.getSource().equals(indentleft)) {
                if (isHTMLMode()) {
                    //TODO nothing can be done here at the moment
                } else {
                    styleTextFormatter.leftIndent();
                }
            } else if (event.getSource().equals(generatelink)) {
                String url = Window.prompt(constants.enterALinkURL(), "http://");
                if (url != null) {
                    if (isHTMLMode()) {
                        changeHtmlStyle(constants.aHref0(url), "</a>");
                    } else {
                        styleTextFormatter.createLink(url);
                    }
                }
            } else if (event.getSource().equals(breaklink)) {
                if (isHTMLMode()) {
                    //TODO nothing can be done here at the moment
                } else {
                    styleTextFormatter.removeLink();
                }
            } else if (event.getSource().equals(insertimage)) {
                String url = Window.prompt(constants.enterAnImageURL(), "http://");
                if (url != null) {
                    if (isHTMLMode()) {
                        changeHtmlStyle(constants.imgSrc0(url), "");
                    } else {
                        styleTextFormatter.insertImage(url);
                    }
                }
            } else if (event.getSource().equals(insertline)) {
                if (isHTMLMode()) {
                    changeHtmlStyle(HTML_STYLE_HLINE, "");
                } else {
                    styleTextFormatter.insertHorizontalRule();
                }
            } else if (event.getSource().equals(removeformatting)) {
                if (isHTMLMode()) {
                    //TODO nothing can be done here at the moment
                } else {
                    styleTextFormatter.removeFormat();
                }
            } else if (event.getSource().equals(texthtml)) {
                if (texthtml.isDown()) {
                    styleText.setText(styleText.getHTML());
                } else {
                    styleText.setHTML(styleText.getText());
                }
            } else if (event.getSource().equals(styleText)) {
                //Change invoked by the richtextArea
            }
            updateStatus();
        }

        public void onKeyUp(KeyUpEvent event) {
            updateStatus();
        }

        public void onChange(ChangeEvent event) {
            if (event.getSource().equals(fontlist)) {
                if (isHTMLMode()) {
                    changeHtmlStyle(constants.spanStyleFontFamily0(fontlist.getValue(fontlist.getSelectedIndex())), constants.closeSpan());
                } else {
                    styleTextFormatter.setFontName(fontlist.getValue(fontlist.getSelectedIndex()));
                }
            } else if (event.getSource().equals(colorlist)) {
                if (isHTMLMode()) {
                    changeHtmlStyle(constants.spanStyleColor0(colorlist.getValue(colorlist.getSelectedIndex())), constants.closeSpan());
                } else {
                    styleTextFormatter.setForeColor(colorlist.getValue(colorlist.getSelectedIndex()));
                }
            }
        }
    }

    /**
     * Interface to represent the messages contained in resource bundle:
     * C:/Users/jim/work/enformablemain/src/main/resources/com/enformable/client/ToolbarConstants.properties'.
     */
    interface ToolbarConstants extends com.google.gwt.i18n.client.Messages {

        /**
         * Translated "<a href=''{0}''>".
         *
         * @return translated "<a href=''{0}''>"
         */
        @DefaultMessage("<a href=''{0}''>")
        @Key("aHref0")
        String aHref0(String arg0);

        /**
         * Translated "Align Center".
         *
         * @return translated "Align Center"
         */
        @DefaultMessage("Align Center")
        @Key("alignCenter")
        String alignCenter();

        /**
         * Translated "Align Left".
         *
         * @return translated "Align Left"
         */
        @DefaultMessage("Align Left")
        @Key("alignLeft")
        String alignLeft();

        /**
         * Translated "Align Right".
         *
         * @return translated "Align Right"
         */
        @DefaultMessage("Align Right")
        @Key("alignRight")
        String alignRight();

        /**
         * Translated "Bold".
         *
         * @return translated "Bold"
         */
        @DefaultMessage("Bold")
        @Key("bold")
        String bold();

        /**
         * Translated "Break Link".
         *
         * @return translated "Break Link"
         */
        @DefaultMessage("Break Link")
        @Key("breakLink")
        String breakLink();

        /**
         * Translated "<closeSpan>".
         *
         * @return translated "<closeSpan>"
         */
        @DefaultMessage("<closeSpan>")
        @Key("closeSpan")
        String closeSpan();

        /**
         * Translated "Colors".
         *
         * @return translated "Colors"
         */
        @DefaultMessage("Colors")
        @Key("colors")
        String colors();

        /**
         * Translated "</div>".
         *
         * @return translated "</div>"
         */
        @DefaultMessage("</div>")
        @Key("div")
        String div();

        /**
         * Translated "<div style='margin-left: 40px;'>".
         *
         * @return translated "<div style='margin-left: 40px;'>"
         */
        @DefaultMessage("<div style='margin-left: 40px;'>")
        @Key("divStyleMarginLeft40px")
        String divStyleMarginLeft40px();

        /**
         * Translated "<div style='text-align: center;'>".
         *
         * @return translated "<div style='text-align: center;'>"
         */
        @DefaultMessage("<div style='text-align: center;'>")
        @Key("divStyleTextAlignCenter")
        String divStyleTextAlignCenter();

        /**
         * Translated "<div style='text-align: left;'>".
         *
         * @return translated "<div style='text-align: left;'>"
         */
        @DefaultMessage("<div style='text-align: left;'>")
        @Key("divStyleTextAlignLeft")
        String divStyleTextAlignLeft();

        /**
         * Translated "<div style='text-align: right;'>".
         *
         * @return translated "<div style='text-align: right;'>"
         */
        @DefaultMessage("<div style='text-align: right;'>")
        @Key("divStyleTextAlignRight")
        String divStyleTextAlignRight();

        /**
         * Translated "Enter a link URL:".
         *
         * @return translated "Enter a link URL:"
         */
        @DefaultMessage("Enter a link URL:")
        @Key("enterALinkURL")
        String enterALinkURL();

        /**
         * Translated "Enter an image URL:".
         *
         * @return translated "Enter an image URL:"
         */
        @DefaultMessage("Enter an image URL:")
        @Key("enterAnImageURL")
        String enterAnImageURL();

        /**
         * Translated "Fonts".
         *
         * @return translated "Fonts"
         */
        @DefaultMessage("Fonts")
        @Key("fonts")
        String fonts();

        /**
         * Translated "Generate Link".
         *
         * @return translated "Generate Link"
         */
        @DefaultMessage("Generate Link")
        @Key("generateLink")
        String generateLink();

        /**
         * Translated "<hr style='width: 100%; height: 2px;'>".
         *
         * @return translated "<hr style='width: 100%; height: 2px;'>"
         */
        @DefaultMessage("<hr style='width: 100%; height: 2px;'>")
        @Key("hrStyleWidth100Height2px")
        String hrStyleWidth100Height2px();

        /**
         * Translated "Ident Left".
         *
         * @return translated "Ident Left"
         */
        @DefaultMessage("Ident Left")
        @Key("identLeft")
        String identLeft();

        /**
         * Translated "Ident Right".
         *
         * @return translated "Ident Right"
         */
        @DefaultMessage("Ident Right")
        @Key("identRight")
        String identRight();

        /**
         * Translated "<img src=''{0}''>".
         *
         * @return translated "<img src=''{0}''>"
         */
        @DefaultMessage("<img src=''{0}''>")
        @Key("imgSrc0")
        String imgSrc0(String arg0);

        /**
         * Translated "Insert Horizontal Line".
         *
         * @return translated "Insert Horizontal Line"
         */
        @DefaultMessage("Insert Horizontal Line")
        @Key("insertHorizontalLine")
        String insertHorizontalLine();

        /**
         * Translated "Insert Image".
         *
         * @return translated "Insert Image"
         */
        @DefaultMessage("Insert Image")
        @Key("insertImage")
        String insertImage();

        /**
         * Translated "Italic".
         *
         * @return translated "Italic"
         */
        @DefaultMessage("Italic")
        @Key("italic")
        String italic();

        /**
         * Translated "&nbsp;".
         *
         * @return translated "&nbsp;"
         */
        @DefaultMessage("&nbsp;")
        @Key("nbsp")
        String nbsp();

        /**
         * Translated "<ol><li>".
         *
         * @return translated "<ol><li>"
         */
        @DefaultMessage("<ol><li>")
        @Key("olLi")
        String olLi();

        /**
         * Translated "</ol></li>".
         *
         * @return translated "</ol></li>"
         */
        @DefaultMessage("</ol></li>")
        @Key("olLi1")
        String olLi1();

        /**
         * Translated "Ordered List".
         *
         * @return translated "Ordered List"
         */
        @DefaultMessage("Ordered List")
        @Key("orderedList")
        String orderedList();

        /**
         * Translated "Remove Formatting".
         *
         * @return translated "Remove Formatting"
         */
        @DefaultMessage("Remove Formatting")
        @Key("removeFormatting")
        String removeFormatting();

        /**
         * Translated "<closeSpan style=''color: {0};''>".
         *
         * @return translated "<closeSpan style=''color: {0};''>"
         */
        @DefaultMessage("<closeSpan style=''color: {0};''>")
        @Key("spanStyleColor0")
        String spanStyleColor0(String arg0);

        /**
         * Translated "<closeSpan style=''font-family: {0};''>".
         *
         * @return translated "<closeSpan style=''font-family: {0};''>"
         */
        @DefaultMessage("<closeSpan style=''font-family: {0};''>")
        @Key("spanStyleFontFamily0")
        String spanStyleFontFamily0(String arg0);

        /**
         * Translated "<closeSpan style='font-weight: bold;'>".
         *
         * @return translated "<closeSpan style='font-weight: bold;'>"
         */
        @DefaultMessage("<closeSpan style='font-weight: bold;'>")
        @Key("spanStyleFontWeightBold")
        String spanStyleFontWeightBold();

        /**
         * Translated "<closeSpan style='font-weight: italic;'>".
         *
         * @return translated "<closeSpan style='font-weight: italic;'>"
         */
        @DefaultMessage("<closeSpan style='font-weight: italic;'>")
        @Key("spanStyleFontWeightItalic")
        String spanStyleFontWeightItalic();

        /**
         * Translated "<closeSpan style='font-weight: line-through;'>".
         *
         * @return translated "<closeSpan style='font-weight: line-through;'>"
         */
        @DefaultMessage("<closeSpan style='font-weight: line-through;'>")
        @Key("spanStyleFontWeightLineThrough")
        String spanStyleFontWeightLineThrough();

        /**
         * Translated "<closeSpan style='font-weight: underline;'>".
         *
         * @return translated "<closeSpan style='font-weight: underline;'>"
         */
        @DefaultMessage("<closeSpan style='font-weight: underline;'>")
        @Key("spanStyleFontWeightUnderline")
        String spanStyleFontWeightUnderline();

        /**
         * Translated "Stroke".
         *
         * @return translated "Stroke"
         */
        @DefaultMessage("Stroke")
        @Key("stroke")
        String stroke();

        /**
         * Translated "<sub>".
         *
         * @return translated "<sub>"
         */
        @DefaultMessage("<sub>")
        @Key("sub")
        String sub();

        /**
         * Translated "</sub>".
         *
         * @return translated "</sub>"
         */
        @DefaultMessage("</sub>")
        @Key("sub1")
        String sub1();

        /**
         * Translated "Subscript".
         *
         * @return translated "Subscript"
         */
        @DefaultMessage("Subscript")
        @Key("subscript")
        String subscript();

        /**
         * Translated "<sup>".
         *
         * @return translated "<sup>"
         */
        @DefaultMessage("<sup>")
        @Key("sup")
        String sup();

        /**
         * Translated "</sup>".
         *
         * @return translated "</sup>"
         */
        @DefaultMessage("</sup>")
        @Key("sup1")
        String sup1();

        /**
         * Translated "Superscript".
         *
         * @return translated "Superscript"
         */
        @DefaultMessage("Superscript")
        @Key("superscript")
        String superscript();

        /**
         * Translated "Switch View HTML/Source".
         *
         * @return translated "Switch View HTML/Source"
         */
        @DefaultMessage("Switch View HTML/Source")
        @Key("switchViewHTMLSource")
        String switchViewHTMLSource();

        /**
         * Translated "<ul><li>".
         *
         * @return translated "<ul><li>"
         */
        @DefaultMessage("<ul><li>")
        @Key("ulLi")
        String ulLi();

        /**
         * Translated "</ul></li>".
         *
         * @return translated "</ul></li>"
         */
        @DefaultMessage("</ul></li>")
        @Key("ulLi1")
        String ulLi1();

        /**
         * Translated "Underline".
         *
         * @return translated "Underline"
         */
        @DefaultMessage("Underline")
        @Key("underline")
        String underline();

        /**
         * Translated "Unordered List".
         *
         * @return translated "Unordered List"
         */
        @DefaultMessage("Unordered List")
        @Key("unorderedList")
        String unorderedList();
    }
}

