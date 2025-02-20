import { jsx as _jsx } from "react/jsx-runtime";
import { createBrowserRouter } from 'react-router-dom';
import ChatClientView from './pages/chat-client';
import CloneModelView from './pages/clone-model';
// import ContentGalleryView from './pages/content-gallery';
import DirFileView from './pages/directories-files';
// import EmbeddingSettingsView from './pages/embedding-settings';
import Grid from './pages/grid';
// import HelloWorldView from './pages/hello-world';
import DestroyModelView from './pages/model-destroy';
import MergeModelView from './pages/model-merge';
import ModelSettingsView from './pages/model-settings';
import SearchModelsView from './pages/search-models';
import ManageServersView from './pages/servers';
import SnapshotsView from './pages/snapshots';
import SystemSettingsView from './pages/system-settings';
export const router = createBrowserRouter([
    {
        path: '/chat-client',
        element: _jsx(ChatClientView, {}),
    },
    {
        path: '/clone-model',
        element: _jsx(CloneModelView, {}),
    },
    // {
    //   path: '/content-gallery',
    //   element: <ContentGalleryView items={[]} />,
    // },
    {
        path: '/directories-files',
        element: _jsx(DirFileView, {})
    },
    // {
    //   path: '/embed-settings',
    //   element: <EmbeddingSettingsView />
    // },
    {
        path: '/grid',
        element: _jsx(Grid, { columns: 0, gap: '' })
    },
    // {
    //   path: '/hello-world',
    //   element: <HelloWorldView />,
    // },
    {
        path: '/model-destroy',
        element: _jsx(DestroyModelView, {}),
    },
    {
        path: '/model-merge',
        element: _jsx(MergeModelView, {}),
    },
    {
        path: '/model-settings',
        element: _jsx(ModelSettingsView, {}),
    },
    {
        path: '/search-models',
        element: _jsx(SearchModelsView, { searchQuery: '', onModelsFetched: function () {
                throw new Error('Function not implemented.');
            }, onLoading: function () {
                throw new Error('Function not implemented.');
            } }),
    },
    {
        path: '/servers',
        element: _jsx(ManageServersView, {}),
    },
    {
        path: '/snapshots',
        element: _jsx(SnapshotsView, {}),
    },
    {
        path: '/system-settings',
        element: _jsx(SystemSettingsView, {})
    }
]);
//# sourceMappingURL=routes.js.map