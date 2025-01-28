import React, { useState } from 'react';
import { ViewConfig } from '@vaadin/hilla-file-router/types.js';
import {
  Button,
  Grid,
  GridColumn,
  TextField,
  Notification
} from '@vaadin/react-components';
import { DownloadersService } from 'Frontend/generated/endpoints';
import { TextFieldValueChangedEvent } from '@vaadin/text-field';

export const config: ViewConfig = {
  menu: { order: 12, icon: 'line-awesome/svg/search-solid.svg' },
  title: 'Search Models'
};

const SearchModelsView: React.FC = () => {
  const [searchQuery, setSearchQuery] = useState('');
  const [models, setModels] = useState<any[]>([]);

  const handleSearch = async () => {
    const apiToken = await DownloadersService.download('api_token', '', '');
    const response = await fetch(`https://huggingface.co/api/models?search=${searchQuery}`, {
      headers: {
        'Authorization': `Bearer ${apiToken}`
      }
    });
    const models = await response.json();
    setModels(models);
    Notification.show('Models searched successfully');
  };

  const handleDownload = async (modelId: string) => {
    await DownloadersService.download('download', `https://huggingface.co/api/models/${modelId}/download`, '');
    Notification.show('Model downloaded successfully' + modelId);
  };

  const handleInputChange = (e: TextFieldValueChangedEvent) => {
    setSearchQuery(e.detail.value);
  };

  return (
    <>
      <section className="flex p-m gap-m items-end">
        <TextField
          label="Search Query"
          value={searchQuery}
          onValueChanged={handleInputChange}
        />
        <Button onClick={handleSearch}>
          Search Models
        </Button>
      </section>
      <Grid items={models} columnReorderingAllowed
            style={{ height: '75vh', width: '150%' }}>
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
};

export default SearchModelsView;
