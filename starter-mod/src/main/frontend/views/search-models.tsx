import React, { useState, useEffect } from 'react';
import { ViewConfig } from '@vaadin/hilla-file-router/types.js';
import { Button, Grid, GridColumn, TextField, Notification } from '@vaadin/react-components';
import { DataBaseService, DownloadersService } from 'Frontend/generated/endpoints';
import { TextFieldValueChangedEvent } from '@vaadin/text-field';
import { DataTypes } from 'Frontend/enums/DataTypes';
import { DlAction } from 'Frontend/enums/DlAction';

export const config: ViewConfig = {
  menu: { order: 12, icon: 'line-awesome/svg/search-solid.svg' }, title: 'Search Models' };

/**
 * <h1>{@link SearchModelsView}</h1>
 * @constructor
 */
const SearchModelsView: React.FC = () => {
  const [searchQuery, setSearchQuery] = useState('');
  const [models, setModels] = useState<any[]>([]);
  const [allModels, setAllModels] = useState<any[]>([]);
  const [offset, setOffset] = useState(0);
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    const fetchData = async () => {
      try {
        // @ts-ignore
        await DataBaseService.performDatabaseAction(DataTypes.H_2);
        Notification.show('Data fetched successfully');
      } catch (error: any) {
        Notification.show('Error fetching data:', error.message);
      }
    };

    fetchData().catch(console.error);
  }, []);

  const loadMoreData = async () => {
    if (loading) return;
    setLoading(true);
    try {
      const newModels = allModels.slice(offset, offset + 100);
      setModels((prevModels) => [...prevModels, ...newModels]);
      setOffset(offset + 100);
    } catch (error: any) {
      Notification.show('Error loading more models:', error.message);
    } finally {
      setLoading(false);
    }
  };

  const handleSearch = () => {
    const filteredModels = allModels.filter((model: any) =>
      model.modelId.toLowerCase().includes(searchQuery.toLowerCase())
    );
    setModels(filteredModels.slice(0, 100));
    setOffset(100);
  };

  const handleInputChange = (e: TextFieldValueChangedEvent) => {
    setSearchQuery(e.detail.value);
  };

  const handleScroll = (e: any) => {
    const bottom = e.target.scrollHeight - e.target.scrollTop === e.target.clientHeight;
    if (bottom) {
      loadMoreData().catch(console.error);
    }
  };

  const handleDownload = async (modelId: string) => {
    try {
      await DownloadersService.download(DlAction.DOWNLOAD_LLM_MODEL, "", "", {});
      Notification.show('Model downloaded successfully' + modelId);
    } catch (error: any) {
      Notification.show('Error downloading model:', error.message);
    }
  };

  const handleRefresh = async () => {
    try {
      await DownloadersService.download(DlAction.DOWNLOAD_LLM_JSON, "", "", {});
      Notification.show('Downloaded new LLM list to the database successfully');
    } catch (error: any) {
      Notification.show('Error refreshing data:', error.message);
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
        <Button onClick={handleSearch} theme="small">🔍</Button>
        <Button onClick={handleRefresh} theme="small">🔄</Button>
      </section>
      <Grid items={models} columnReorderingAllowed style={{ height: '100vh', width: '100%' }} onScroll={handleScroll}>
        <GridColumn path="modelId" header="Model ID" resizable />
        <GridColumn path="likes" header="Likes" resizable />
        <GridColumn path="trendingScore" header="Trending Score" resizable />
        <GridColumn path="downloads" header="Downloads" resizable />
        <GridColumn path="pipelineTag" header="Pipeline Tag" resizable />
        <GridColumn path="libraryName" header="Library Name" resizable />
        <GridColumn path="createdAt" header="Created At" resizable />
        <GridColumn header="Actions" resizable>
          {({ item }) => (
            <Button onClick={() => handleDownload(item.modelId)} theme="small">
              Download
            </Button>
          )}
        </GridColumn>
      </Grid>
    </>
  );
};

export default SearchModelsView;
