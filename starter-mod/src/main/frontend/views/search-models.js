import { jsx as _jsx, jsxs as _jsxs } from "react/jsx-runtime";
import React, { Component } from 'react';
import { Button, Grid, GridColumn, TextField, Notification } from '@vaadin/react-components';
import { DownloadersService } from 'Frontend/generated/endpoints';

export const config = {
  menu: { order: 12, icon: 'line-awesome/svg/search-solid.svg' },
  title: 'Search Models'
};

class SearchModelsView extends Component {
  constructor(props) {
    super(props);
    this.state = {
      searchQuery: '',
      models: []
    };
  }

  handleSearch = async () => {
    const { searchQuery } = this.state;
    const apiToken = await DownloadersService.download('api_token', '', '');
    const response = await fetch(`https://huggingface.co/api/models?search=${searchQuery}`, {
      headers: {
        'Authorization': `Bearer ${apiToken}`
      }
    });
    const models = await response.json();
    this.setState({ models });
    Notification.show('Models searched successfully');
  };

  handleDownload = async (modelId) => {
    await DownloadersService.download('download', `https://huggingface.co/api/models/${modelId}/download`, '');
    Notification.show('Model downloaded successfully');
  };

  handleInputChange = (e) => {
    this.setState({ searchQuery: e.detail.value });
  };

  render() {
    const { searchQuery, models } = this.state;

    return (
      _jsxs(React.Fragment, {
        children: [
          _jsxs("section", {
            className: "flex p-m gap-m items-end",
            children: [
              _jsx(TextField, {
                label: "Search Query",
                value: searchQuery,
                onValueChanged: (e) => this.handleInputChange(e)
              }),
              _jsx(Button, {
                onClick: this.handleSearch,
                children: "Search Models"
              })
            ]
          }),
          _jsxs(Grid, {
            items: models,
            columnReorderingAllowed: true,
            style: { height: '75vh', width: '150%' },
            children: [
              _jsx(GridColumn, { path: "modelId", header: "Model ID", resizable: true }),
              _jsx(GridColumn, { path: "description", header: "Description", resizable: true }),
              _jsx(GridColumn, { path: "tags", header: "Tags", resizable: true }),
              _jsx(GridColumn, {
                header: "Actions",
                resizable: true,
                children: ({ item }) => _jsx(Button, {
                  onClick: () => this.handleDownload(item.modelId),
                  children: "Download"
                })
              })
            ]
          })
        ]
      })
    );
  }
}

export default SearchModelsView;
//# sourceMappingURL=search-models.js
