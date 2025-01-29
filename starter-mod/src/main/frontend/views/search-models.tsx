import React, { useState, useEffect } from 'react';
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
  const [offset, setOffset] = useState(0);
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    const downloadJsonFile = async () => {
      try {
        await DownloadersService.download('DOWNLOAD', '', '');
        Notification.show('LLM JSON file downloaded successfully');
      } catch (error: any) {
        Notification.show('Error downloading LLM JSON file: ' + error.message);
      }
    };

    downloadJsonFile().catch(console.error);
  }, []);

  const handleSearch = async () => {
    setOffset(0);
    setModels([]);
    await loadMoreData(0).catch(console.error);
  };

  const loadMoreData = async (offset: number) => {
    if (loading) return;
    setLoading(true);
    try {
      const response = await fetch('/downloaders-mod/llm.json');
      if (!response.ok) {
        Notification.show(`HTTP error! status: ${response.status}`);
      }
      const allModels = await response.json();
      const filteredModels = allModels.filter((model: any) =>
        model.modelId.toLowerCase().includes(searchQuery.toLowerCase())
      );
      const newModels = filteredModels.slice(offset, offset + 100);
      setModels((prevModels) => [...prevModels, ...newModels]);
      setOffset(offset + 100);
      Notification.show('Models loaded successfully');
    } catch (error: any) {
      Notification.show('Error loading models: ' + error.message);
    } finally {
      setLoading(false);
    }
  };

  const handleDownload = async (modelId: string) => {
    try {
      await DownloadersService.download('DOWNLOAD', modelId, '');
      Notification.show('Model downloaded successfully: ' + modelId);
    } catch (error: any) {
      Notification.show('Error downloading model: ' + error.message);
    }
  };

  const handleInputChange = (e: TextFieldValueChangedEvent) => {
    setSearchQuery(e.detail.value);
  };

  const handleScroll = (e: any) => {
    const bottom = e.target.scrollHeight - e.target.scrollTop === e.target.clientHeight;
    if (bottom) {
      loadMoreData(offset).catch(console.error);
    }
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
      <Grid items={models} columnReorderingAllowed style={{ height: '75vh', width: '150%' }} onScroll={handleScroll}>
        <GridColumn path="modelId" header="Model ID" resizable />
        <GridColumn path="likes" header="Likes" resizable />
        <GridColumn path="trendingScore" header="Trending Score" resizable />
        <GridColumn path="downloads" header="Downloads" resizable />
        <GridColumn path="pipelineTag" header="Pipeline Tag" resizable />
        <GridColumn path="libraryName" header="Library Name" resizable />
        <GridColumn path="createdAt" header="Created At" resizable />
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
