import React from 'react';
import { ViewConfig } from '@vaadin/hilla-file-router/types.js';
import { useSignal } from '@vaadin/hilla-react-signals';
import {
  Button,
  Grid,
  GridColumn,
  TextField
} from '@vaadin/react-components';
import { HuggingFaceService } from 'Frontend/generated/endpoints.js';

export const config: ViewConfig = {
  menu: { order: 12, icon: 'line-awesome/svg/search-solid.svg' },
  title: 'Search Models',
};

export default function SearchModelsView() {
  const searchQuery = useSignal('');
  const models = useSignal([]);

  const handleSearch = async () => {
    const apiToken = await HuggingFaceService.getApiToken();
    const response = await fetch(`https://huggingface.co/api/models?search=${searchQuery.value}`, {
      headers: {
        'Authorization': `Bearer ${apiToken}`
      }
    });
    models.value = await response.json(); // Assuming the response is an array of models
  };

  const handleDownload = async (modelId: string) => {
    await fetch(`/api/huggingface/downloadModel?modelId=${modelId}`, {
      method: 'POST'
    });
  };

  return (
    <>
      <section className="flex p-m gap-m items-end">
        <TextField
          label="Search Query"
          value={searchQuery.value}
          onValueChanged={(e) => {
            searchQuery.value = e.detail.value;
          }}
        />
        <Button onClick={handleSearch}>
          Search Models
        </Button>
      </section>
      <Grid items={models.value} columnReorderingAllowed style={{ height: '75vh', width: '150%' }}>
        <GridColumn path="modelId" header="Model ID" resizable />
        <GridColumn path="description" header="Description" resizable />
        <GridColumn path="tags" header="Tags" resizable />
        <GridColumn header="Actions" resizable>
          {({ item }) => (
            <Button onClick={() => handleDownload(item.modelId)}>
              Download
            </Button>
          )}
        </GridColumn>
      </Grid>
    </>
  );
}
