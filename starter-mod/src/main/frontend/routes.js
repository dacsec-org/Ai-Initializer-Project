import { jsx as _jsx } from "react/jsx-runtime";
import { RouterConfigurationBuilder } from '@vaadin/hilla-file-router/runtime.js';
import Flow from 'Frontend/generated/flow/Flow';
import MainLayout from './views/@layout';
import ChatClientView from './views/chat-client';
import CloneModelView from './views/clone-model';
import ContentGalleryView from './views/content-gallery';
import DirFileView from './views/directories-files';
import EmbeddingSettingsView from './views/embedding-settings';
import LoadUnloadView from './views/load-unload';
import DestroyModelView from './views/model-destroy';
import MetricsView from './views/metrics';
import MergeModelView from './views/model-merge';
import ModelSettingsView from './views/model-settings';
import SearchModelsView from './views/search-models';
import ManageServersView from './views/servers';
import SnapshotsView from './views/snapshots';
import SystemSettingsView from './views/system-settings';
import HelloWorldView from './views/hello-world';
export const { router, routes } = new RouterConfigurationBuilder()
    .withReactRoutes([
    {
        element: _jsx(MainLayout, {}),
        handle: { title: 'Home' },
        children: [
            { path: '/chat-client', element: _jsx(ChatClientView, {}), handle: { title: 'Chat' } },
            { path: '/clone-model', element: _jsx(CloneModelView, {}), handle: { title: 'Clone' } },
            { path: '/content-gallery', element: _jsx(ContentGalleryView, { items: [] }), handle: { title: 'Content Gallery' } },
            { path: '/directories-files', element: _jsx(DirFileView, {}), handle: { title: 'Directories ~ Files' } },
            { path: '/embedding-settings', element: _jsx(EmbeddingSettingsView, {}), handle: { title: 'Embedding' } },
            { path: '/load-unload', element: _jsx(LoadUnloadView, {}), handle: { title: 'Load ~ Unload' } },
            { path: '/hello-world', element: _jsx(HelloWorldView, {}), handle: { title: 'Hello World' } },
            { path: '/metrics', element: _jsx(MetricsView, {}), handle: { title: 'Metrics' } },
            { path: '/model-destroy', element: _jsx(DestroyModelView, {}), handle: { title: 'Delete ~ Model' } },
            { path: '/model-merge', element: _jsx(MergeModelView, {}), handle: { title: 'Merge Model' } },
            { path: '/model-settings', element: _jsx(ModelSettingsView, {}), handle: { title: 'Model Settings' } },
            { path: '/search-models', element: _jsx(SearchModelsView, {}), handle: { title: 'Search Models' } },
            { path: '/servers', element: _jsx(ManageServersView, {}), handle: { title: 'Servers' } },
            { path: '/snapshots', element: _jsx(SnapshotsView, {}), handle: { title: 'Snapshots' } },
            { path: '/system-settings', element: _jsx(SystemSettingsView, {}), handle: { title: 'System Settings' } },
        ],
    },
])
    .withFallback(Flow)
    // .protect()
    .build();
//# sourceMappingURL=routes.js.map
