import { jsx as _jsx, Fragment as _Fragment, jsxs as _jsxs } from "react/jsx-runtime";
import '@vaadin/icons';
import { Icon } from '@vaadin/react-components/Icon.js';
import { MenuBar } from '@vaadin/react-components/MenuBar.js';
function createItem(iconName, text, isChild = false) {
    const iconStyle = {
        width: isChild ? 'var(--lumo-icon-size-s)' : '',
        height: isChild ? 'var(--lumo-icon-size-s)' : '',
        marginRight: isChild ? 'var(--lumo-space-s)' : '',
    };
    let ariaLabel = '';
    if (iconName === 'copy') {
        ariaLabel = 'duplicate';
    }
    return (_jsxs(_Fragment, { children: [_jsx(Icon, { icon: `vaadin:${iconName}`, style: iconStyle, "aria-label": ariaLabel }), text] }));
}
const MainMenubar = () => {
    const items = [
        { component: createItem('eye', 'View') },
        { component: createItem('edit', 'Edit') },
        {
            component: createItem('share', 'Share'),
            children: [
                {
                    component: createItem('share', 'On social media', true),
                    children: [
                        { component: createItem('facebook', 'Facebook', true) },
                        { component: createItem('twitter', 'Twitter', true) },
                        { component: createItem('instagram', 'Instagram', true) },
                    ],
                },
                { component: createItem('envelope', 'By email', true) },
                { component: createItem('link', 'Get link', true) },
            ],
        },
        {
            component: createItem('folder', 'Move'),
            children: [
                { component: createItem('folder-open', 'To folder', true) },
                { component: createItem('trash', 'To trash', true) },
            ],
        },
        { component: createItem('copy', 'Duplicate') },
    ];
    return _jsx(MenuBar, { theme: "icon", items: items });
};
export default MainMenubar;
//# sourceMappingURL=main-menubar.js.map