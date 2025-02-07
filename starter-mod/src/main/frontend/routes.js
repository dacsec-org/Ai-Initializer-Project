import { jsx as _jsx } from "react/jsx-runtime";
import { RouterConfigurationBuilder } from '@vaadin/hilla-file-router/runtime.js';
// @ts-ignore
import Flow from 'Frontend/generated/flow/Flow';
import MainLayout, { config as mainLayoutConfig } from './views/@layout';
import ChatClientView from './views/chat/chat-client';
import CloneModelView from './views/models/clone-model';
import ContentGalleryView from './views/gallery/content-gallery';
import DirFileView from './views/dir/directories-files';
import EmbeddingSettingsView from './views/embedding-settings';
import LoadUnloadView from './views/load/load-unload';
import DestroyModelView from './views/models/model-destroy';
import MetricsView from './views/metric/metrics';
import MergeModelView from './views/models/model-merge';
import ModelSettingsView from './views/models/model-settings';
import SearchModelsView from './views/search/search-models';
import ManageServersView from './views/servers';
import SnapshotsView from './views/snaps/snapshots';
import SystemSettingsView from './views/settings/system-settings';
import HelloWorldView from './views/hello/hello-world';
export const { router, routes } = new RouterConfigurationBuilder()
    .withReactRoutes([
    {
        path: '',
        element: _jsx(MainLayout, {}),
        handle: { title: mainLayoutConfig.title },
    },
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
    { path: '/search-models', element: _jsx(SearchModelsView, { searchQuery: '', onModelsFetched: function (models) {
                throw new Error('Function not implemented.');
            }, onLoading: function (loading) {
                throw new Error('Function not implemented.');
            } }), handle: { title: 'Search Models' } },
    { path: '/servers', element: _jsx(ManageServersView, {}), handle: { title: 'Servers' } },
    { path: '/snapshots', element: _jsx(SnapshotsView, {}), handle: { title: 'Snapshots' } },
    { path: '/system-settings', element: _jsx(SystemSettingsView, {}), handle: { title: 'System Settings' } },
])
    .withFallback(Flow)
    .build();
//# sourceMappingURL=routes.js.map