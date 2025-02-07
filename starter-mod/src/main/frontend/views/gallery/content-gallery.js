import { jsx as _jsx } from "react/jsx-runtime";
import { VerticalLayout, HorizontalLayout } from '@vaadin/react-components';
export const config = {
    menu: { order: 3, icon: 'line-awesome/svg/images-solid.svg' }, title: 'Gallery'
};
const ContentGalleryView = ({ items }) => {
    return (_jsx(VerticalLayout, { children: items.map((item, index) => (_jsx(HorizontalLayout, { style: { marginBottom: '10px' }, children: item.type === 'image' ? (_jsx("img", { src: item.src, alt: item.alt || 'Image', style: { width: '100%' } })) : (_jsx("video", { src: item.src, controls: true, style: { width: '100%' } })) }, index))) }));
};
const mediaItems = [
    { type: 'image', src: 'path/to/pictures.jpg', alt: 'Pictures' },
    { type: 'video', src: 'path/to/videos.mp4', alt: 'Videos' },
    { type: 'image', src: 'path/to/trash.jpg', alt: 'Trash' },
    { type: 'image', src: 'path/to/projects.jpg', alt: 'Projects' },
];
const App = () => {
    return _jsx(ContentGalleryView, { items: mediaItems });
};
export default ContentGalleryView;
//# sourceMappingURL=content-gallery.js.map