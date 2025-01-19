import { jsx as _jsx, jsxs as _jsxs, Fragment as _Fragment } from "react/jsx-runtime";
import React, { Component } from 'react';
import { Button, Grid, GridColumn, TextField } from '@vaadin/react-components';
import { HuggingFaceService } from 'Frontend/generated/endpoints.js';
export const config = {
    menu: { order: 10, icon: 'line-awesome/svg/search-solid.svg' },
    title: 'Search Models',
};
class SearchModelsView extends Component {
    constructor(props) {
        super(props);
        this.handleSearch = async () => {
            const { searchQuery } = this.state;
            const apiToken = await HuggingFaceService.getApiToken();
            const response = await fetch(`https://huggingface.co/api/models?search=${searchQuery}`, {
                headers: {
                    'Authorization': `Bearer ${apiToken}`
                }
            });
            const models = await response.json();
            this.setState({ models });
        };
        this.handleDownload = async (modelId) => {
            await fetch(`/api/huggingface/downloadModel?modelId=${modelId}`, {
                method: 'POST'
            });
        };
        this.handleInputChange = (e) => {
            this.setState({ searchQuery: e.target.value });
        };
        this.state = {
            searchQuery: '',
            models: []
        };
    }
    render() {
        const { searchQuery, models } = this.state;
        return (_jsxs(_Fragment, { children: [_jsxs("section", { className: "flex p-m gap-m items-end", children: [_jsx(TextField, { label: "Search Query", value: searchQuery, onValueChanged: (e) => this.handleInputChange(e) }), _jsx(Button, { onClick: this.handleSearch, children: "Search Models" })] }), _jsxs(Grid, { items: models, columnReorderingAllowed: true, style: { height: '75vh', width: '150%' }, children: [_jsx(GridColumn, { path: "modelId", header: "Model ID", resizable: true }), _jsx(GridColumn, { path: "description", header: "Description", resizable: true }), _jsx(GridColumn, { path: "tags", header: "Tags", resizable: true }), _jsx(GridColumn, { header: "Actions", resizable: true, children: ({ item }) => (_jsx(Button, { onClick: () => this.handleDownload(item.modelId), children: "Download" })) })] })] }));
    }
}
export default SearchModelsView;
//# sourceMappingURL=search-models.js.map