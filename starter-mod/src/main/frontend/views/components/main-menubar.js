import { jsx as _jsx } from "react/jsx-runtime";
import React, { Component } from 'react';
import { MenuBar } from '@vaadin/react-components/MenuBar.js';
/**
 * {@link MainMenubar}
 * <p>
 *   This is the main menubar component that renders a menu bar with multiple items.
 * </p>
 */
class MainMenubar extends Component {
    render() {
        const items = [
            { text: 'View' },
            { text: 'Edit' },
            {
                text: 'Share',
                children: [
                    {
                        text: 'On social media',
                        children: [{ text: 'Facebook' }, { text: 'Twitter' }, { text: 'Instagram' }],
                    },
                    { text: 'By email' },
                    { text: 'Get link' },
                ],
            },
            {
                text: 'Move',
                children: [{ text: 'To folder' }, { text: 'To trash' }],
            },
            { text: 'Duplicate' },
        ];
        return _jsx(MenuBar, { theme: "end-aligned", items: items });
    }
}
export default MainMenubar;
//# sourceMappingURL=main-menubar.js.map