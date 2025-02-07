import { jsx as _jsx, jsxs as _jsxs } from "react/jsx-runtime";
import { useState, useEffect } from 'react';
import { Button, Grid, GridColumn, Notification } from '@vaadin/react-components';
import { SearchModels } from '../search/SearchModels';
import { DownloadAction } from '../../enums/DownloadAction';
export const config = {
    menu: { order: 12, icon: 'line-awesome/svg/search-solid.svg', title: 'Search Models' }
};
const SearchModelsView = ({ searchQuery, onModelsFetched, onLoading }) => {
    const [models, setModels] = useState([]);
    useEffect(() => {
        if (searchQuery) {
            onLoading(true);
            SearchModels.getModels(DownloadAction.SEARCH, searchQuery)
                .then(response => response.json())
                .then(jsonResponse => {
                setModels(jsonResponse);
                onModelsFetched(jsonResponse);
                Notification.show('Models fetched successfully');
            })
                .finally(() => {
                onLoading(false);
            });
        }
    }, [searchQuery, onLoading, onModelsFetched]);
    const handleDownload = async (id) => {
        await SearchModels.getModels(DownloadAction.DOWNLOAD_LLM_MODEL, id)
            .then(() => {
            Notification.show('Model downloaded successfully');
        });
    };
    return (_jsxs(Grid, { items: models, columnReorderingAllowed: true, style: { height: '100vh', width: '100%' }, children: [_jsx(GridColumn, { header: "Download", renderer: ({ item }) => (_jsx(Button, { onClick: () => handleDownload(item.id), theme: "icon", "aria-label": "Download", children: "\u2B07\uFE0F" })) }), _jsx(GridColumn, { path: "id", header: "ID", resizable: true }), _jsx(GridColumn, { path: "modelId", header: "Model ID", resizable: true }), _jsx(GridColumn, { path: "likes", header: "Likes", resizable: true }), _jsx(GridColumn, { path: "trendingScore", header: "Trending Score", resizable: true }), _jsx(GridColumn, { path: "downloads", header: "Downloads", resizable: true }), _jsx(GridColumn, { path: "pipelineTag", header: "Pipeline Tag", resizable: true }), _jsx(GridColumn, { path: "libraryName", header: "Library Name", resizable: true }), _jsx(GridColumn, { path: "createdAt", header: "Created At", resizable: true })] }));
};
export default SearchModelsView;
//# sourceMappingURL=search-models.js.map