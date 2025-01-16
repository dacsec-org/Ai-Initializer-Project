import React, { Component } from 'react';
import { ViewConfig } from '@vaadin/hilla-file-router/types.js';
import { Button, Grid, GridColumn, TextField } from '@vaadin/react-components';
import { HuggingFaceService } from 'Frontend/generated/endpoints.js';

export const config: ViewConfig = {
  menu: { order: 9, icon: 'line-awesome/svg/search-solid.svg' },
  title: 'Search Models',
};

interface SearchModelsViewState {
  searchQuery: string;
  models: any[];
}

class SearchModelsView extends Component<{}, SearchModelsViewState> {
  constructor(props: {}) {
    super(props);
    this.state = {
      searchQuery: '',
      models: []
    };
  }

  handleSearch = async () => {
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

  handleDownload = async (modelId: string) => {
    await fetch(`/api/huggingface/downloadModel?modelId=${modelId}`, {
      method: 'POST'
    });
  };

  handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    this.setState({ searchQuery: e.target.value });
  };

  render() {
    const { searchQuery, models } = this.state;

    return (
      <>
        <section className="flex p-m gap-m items-end">
          <TextField
            label="Search Query"
            value={searchQuery}
            onValueChanged={(e) => this.handleInputChange(e)}
          />
          <Button onClick={this.handleSearch}>
            Search Models
          </Button>
        </section>
        <Grid items={models} columnReorderingAllowed style={{ height: '75vh', width: '150%' }}>
          <GridColumn path="modelId" header="Model ID" resizable />
          <GridColumn path="description" header="Description" resizable />
          <GridColumn path="tags" header="Tags" resizable />
          <GridColumn header="Actions" resizable>
            {({ item }) => (
              <Button onClick={() => this.handleDownload(item.modelId)}>
                Download
              </Button>
            )}
          </GridColumn>
        </Grid>
      </>
    );
  }
}

export default SearchModelsView;
